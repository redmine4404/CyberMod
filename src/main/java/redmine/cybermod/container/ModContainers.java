package redmine.cybermod.container;

import net.minecraft.inventory.container.ContainerType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import redmine.cybermod.utils.Reference;

import java.awt.*;

public class ModContainers {

    public static DeferredRegister<ContainerType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, Reference.MOD_ID);

    public static final RegistryObject<ContainerType<SmelterBlockContainer>> SMELTER_BLOCK_CONTAINERS = CONTAINERS.register("smelter_block_containers", () -> IForgeContainerType.create(((windowId, inv, data) -> {
        BlockPos pos = data.readBlockPos();
        World world = inv.player.getCommandSenderWorld();
        return new SmelterBlockContainer(windowId, world, pos, inv, inv.player);
    })));

    public static void register(IEventBus eventBus){
        CONTAINERS.register(eventBus);
    }
}
