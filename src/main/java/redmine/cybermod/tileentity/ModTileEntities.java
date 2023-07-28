package redmine.cybermod.tileentity;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import redmine.cybermod.CyberMod;
import redmine.cybermod.block.BlockRegister;
import redmine.cybermod.utils.Reference;

public class ModTileEntities {

    public static DeferredRegister<TileEntityType<?>> TILE_ENTITIES= DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, Reference.MOD_ID);

    public static RegistryObject<TileEntityType<SmelterBlockTile>> SMELTER_BLOCK_TILE = TILE_ENTITIES.register("smelter_block_tile", () -> TileEntityType.Builder.of(SmelterBlockTile::new, BlockRegister.SmelterBlock.get()).build(null));

    public static void register(IEventBus eventBus){
        TILE_ENTITIES.register(eventBus);
    }
}
