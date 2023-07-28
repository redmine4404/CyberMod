package redmine.cybermod.tileentity;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import redmine.cybermod.utils.SmelterBlockUtil.SmelterBlockCraftRecipe;
import redmine.cybermod.utils.SmelterBlockUtil.SmelterBlockCraftRecipes;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.UUID;

public class SmelterBlockTile extends TileEntity implements ITickableTileEntity
{

    private final ItemStackHandler itemStackHandler = createHandler();

    private LazyOptional<IItemHandler> handler = LazyOptional.of(() -> itemStackHandler);

    public int FUEL_SLOT = 0;
    public int TOOL_SLOT = 4;

    public int MAX_FUEL = 100;
    public int ADD_FUEL = 10;

    private int fuel;
    private int litTime;
    private int litDuration;
    private int recipeId;
    private boolean flag;
    public boolean canProcess;
    private ItemStack mainItem = ItemStack.EMPTY;
    public SmelterBlockTile(TileEntityType<?> tileEntityType) {
        super(tileEntityType);
    }

    public SmelterBlockTile(){
        this(ModTileEntities.SMELTER_BLOCK_TILE.get());
    }

    @Override
    public void load(BlockState blockState, CompoundNBT nbt) {
        this.fuel = nbt.getInt("fuel");
        this.litTime = nbt.getInt("litTime");
        this.litDuration = nbt.getInt("litDuration");
        this.recipeId = nbt.getInt("recipeId");
        this.flag = nbt.getBoolean("flags");
        this.canProcess = nbt.getBoolean("canProcess");
        this.mainItem = ItemStack.of(nbt.getCompound("mainItemCompound"));
        itemStackHandler.deserializeNBT(nbt.getCompound("inv"));

        super.load(blockState, nbt);
    }

    @Override
    public CompoundNBT save (CompoundNBT nbt) {
        nbt.putInt("fuel", fuel);
        nbt.putInt("litTime", litTime);
        nbt.putInt("litDuration", litDuration);
        nbt.putInt("recipeId", recipeId);
        nbt.putBoolean("flags", flag);
        nbt.putBoolean("canProcess", canProcess);
        nbt.put("mainItemCompound", mainItem.save(new CompoundNBT()));
        nbt.put("inv", itemStackHandler.serializeNBT());
        return super.save(nbt);
    }
    @Nullable
    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        return new SUpdateTileEntityPacket(getBlockPos(), 1, getUpdateTag());
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        handleUpdateTag(level.getBlockState(pkt.getPos()), pkt.getTag());
    }


    @Override
    public CompoundNBT getUpdateTag() {
        CompoundNBT compoundNBT = super.getUpdateTag();
        compoundNBT.putInt("fuel", fuel);
        compoundNBT.putInt("litTime", litTime);
        compoundNBT.putInt("litDuration", litDuration);
        compoundNBT.putInt("recipeId", recipeId);
        compoundNBT.putBoolean("flags", flag);
        compoundNBT.putBoolean("canProcess", canProcess);
        compoundNBT.put("mainItemCompound", mainItem.save(new CompoundNBT()));
        compoundNBT.put("inv", itemStackHandler.serializeNBT());
        return compoundNBT;
    }

    @Override
    public void handleUpdateTag(BlockState state, CompoundNBT tag) {

        this.getTileData().putInt("fuel", fuel);
        this.getTileData().putInt("litTime", litTime);
        this.getTileData().putInt("litDuration", litDuration);
        this.getTileData().putInt("recipeId", recipeId);
        this.getTileData().putBoolean("flags", flag);
        this.getTileData().putBoolean("canProcess", canProcess);
        this.getTileData().put("mainItemCompound", mainItem.save(new CompoundNBT()));
        itemStackHandler.deserializeNBT(tag);

        super.handleUpdateTag(state, tag);
    }

    public void burn(){

        if(canProcess == false) return;

        canProcess = false;

        mainItem = itemStackHandler.getStackInSlot(2);

        Item item1 = itemStackHandler.getStackInSlot(1).getItem();
        Item item2 = itemStackHandler.getStackInSlot(2).getItem();
        Item item3 = itemStackHandler.getStackInSlot(3).getItem();

        SmelterBlockCraftRecipe smelterBlockCraftRecipe = SmelterBlockCraftRecipes.getRecipeByItem(item1, item2, item3);


        if(smelterBlockCraftRecipe != null && smelterBlockCraftRecipe.getFuelNeed() <= fuel) {
            flag = true;
            recipeId = smelterBlockCraftRecipe.getId();
            fuel -= smelterBlockCraftRecipe.getFuelNeed();
            litTime = 0;
            litDuration = smelterBlockCraftRecipe.getDurationTick();
            level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), Constants.BlockFlags.BLOCK_UPDATE);

            itemStackHandler.setStackInSlot(1, ItemStack.EMPTY);
            itemStackHandler.setStackInSlot(2, ItemStack.EMPTY);
            itemStackHandler.setStackInSlot(3, ItemStack.EMPTY);
        }

    }

    private ItemStackHandler createHandler() {
        return new ItemStackHandler(5) {
            @Override
            protected void onContentsChanged(int slot) {
                setChanged();
                checkResult();
                checkFuel(slot);
            }

            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {

                if(flag) return false;

                switch (slot) {
                    case 0:
                        return stack.getItem() == Items.LAVA_BUCKET;
                    case 1:
                        return true;
                    case 2:
                        return true;
                    case 3:
                        return true;
                    case 4:
                        return stack.getItem() instanceof ToolItem;
                    default:
                        return false;
                }
            }

            @Override
            public int getSlotLimit(int slot) {
                return 1;
            }

            @Nonnull
            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                if (!isItemValid(slot, stack)){
                    return stack;
                }
                return super.insertItem(slot, stack, simulate);
           }

            @Nonnull
            @Override
            public ItemStack extractItem(int slot, int amount, boolean simulate) {
                if(!flag)
                {
                    return super.extractItem(slot, amount, simulate);
                }
                setChanged();
                return ItemStack.EMPTY;
            }
        };
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if(cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return handler.cast();
        }

        return super.getCapability(cap, side);
    }

    public int getFuel(){
        return fuel;
    }



    public void checkFuel(int slot){
        ItemStack stack = this.itemStackHandler.getStackInSlot(slot);
        if(slot == FUEL_SLOT && stack.getItem() == Items.LAVA_BUCKET && fuel + ADD_FUEL <= MAX_FUEL){
            stack.shrink(1);
            this.itemStackHandler.setStackInSlot(FUEL_SLOT, new ItemStack(Items.BUCKET));
            fuel += 10;
            if(level != null){
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), Constants.BlockFlags.BLOCK_UPDATE);
            }
            setChanged();
        }
    }

    public void checkResult() {
        canProcess = false;
        if (itemStackHandler.getStackInSlot(1) != null && itemStackHandler.getStackInSlot(2) != null && itemStackHandler.getStackInSlot(3) != null) {
            SmelterBlockCraftRecipe recipe = SmelterBlockCraftRecipes.getRecipeByItem(itemStackHandler.getStackInSlot(1).getItem(), itemStackHandler.getStackInSlot(2).getItem(), itemStackHandler.getStackInSlot(3).getItem());
            if (recipe != null){
                System.out.println(recipe.isCratable(itemStackHandler.getStackInSlot(2), fuel));
                if (recipe.isCratable(itemStackHandler.getStackInSlot(2), fuel)) {
                    canProcess = true;
                }
            }
        }
        setChanged();
    }
    @Override
    public void tick() {
        if(flag){
            canProcess = false;
            litTime++;
            if(litTime >= litDuration){
                flag = false;
                SmelterBlockCraftRecipe recipe = SmelterBlockCraftRecipes.getRecipeById(recipeId);
                ItemStack result = recipe.getItemResult(mainItem);

                if(result != null) {
                    itemStackHandler.setStackInSlot(4, result);
                }
            }
        }
    }

    public boolean isFlag() {
        return flag;
    }

    public int getLitDuration() {
        return litDuration;
    }

    public int getLitTime() {
        return litTime;
    }
}
