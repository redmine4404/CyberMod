package redmine.cybermod.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import redmine.cybermod.utils.CyberTab;
import redmine.cybermod.utils.Reference;

public class BlockRegister {
    public static final DeferredRegister<Block> BLOCK_DEFERRED_REGISTER = DeferredRegister.create(ForgeRegistries.BLOCKS, Reference.MOD_ID);
    public static final DeferredRegister<Item> BLOCK_ITEM_DEFERRED_REGISTER = DeferredRegister.create(ForgeRegistries.ITEMS, Reference.MOD_ID);

    public static final RegistryObject<Block> testBlock = BLOCK_DEFERRED_REGISTER.register("test_block", () -> new TestBlock(AbstractBlock.Properties.of(Material.STONE)));
    public static final RegistryObject<Item> testBlockItem = BLOCK_ITEM_DEFERRED_REGISTER.register("test_block", () -> new BlockItem(testBlock.get(), new Item.Properties().stacksTo(1).tab(CyberTab.CyberTab)));

    public static final RegistryObject<Block> SmelterBlock = BLOCK_DEFERRED_REGISTER.register("smelter_block", () -> new SmelterBlock(AbstractBlock.Properties.of(Material.STONE)));
    public static final RegistryObject<Item> SmelterBlockItem= BLOCK_ITEM_DEFERRED_REGISTER.register("smelter_block", () -> new BlockItem(SmelterBlock.get(), new Item.Properties().stacksTo(1).tab(CyberTab.CyberTab)));

}
