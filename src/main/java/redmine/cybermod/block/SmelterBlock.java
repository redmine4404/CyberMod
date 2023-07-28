package redmine.cybermod.block;

import com.google.common.graph.Network;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import redmine.cybermod.container.SmelterBlockContainer;
import redmine.cybermod.tileentity.ModTileEntities;
import redmine.cybermod.tileentity.SmelterBlockTile;

import javax.annotation.Nullable;

public class SmelterBlock extends Block {

    public static final DirectionProperty FACING = HorizontalBlock.FACING;

    public SmelterBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(BlockStateProperties.LIT, false));
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING).add(BlockStateProperties.LIT);
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }
    
    @Override
    public ActionResultType use(BlockState blockState, World world, BlockPos blockPos, PlayerEntity playerEntity, Hand hand, BlockRayTraceResult blockRayTraceResult) {

        if(!world.isClientSide){
            TileEntity tileEntity = world.getBlockEntity(blockPos);

            if(!playerEntity.isCrouching()) {
                if (tileEntity instanceof SmelterBlockTile) {
                    INamedContainerProvider containerProvider = createContainerProvider(world, blockPos);

                    NetworkHooks.openGui(((ServerPlayerEntity) playerEntity), containerProvider, tileEntity.getBlockPos());
                } else {
                    throw new IllegalStateException("Our Container Provider is missing!");
                }
            }
        }

        return ActionResultType.SUCCESS;
    }

    private INamedContainerProvider createContainerProvider(World world, BlockPos blockPos) {
        return new INamedContainerProvider() {
            @Override
            public ITextComponent getDisplayName() {
                return new TranslationTextComponent("screen.cybermod.smelter_block");
            }

            @Nullable
            @Override
            public Container createMenu(int i, PlayerInventory playerInventory, PlayerEntity playerEntity) {
                return new SmelterBlockContainer(i, world, blockPos, playerInventory, playerEntity);
            }
        };
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return ModTileEntities.SMELTER_BLOCK_TILE.get().create();
    }
}
