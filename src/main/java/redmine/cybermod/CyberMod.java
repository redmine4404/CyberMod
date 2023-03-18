package redmine.cybermod;

import net.minecraft.block.Block;
import net.minecraft.item.ItemTier;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.EventBus;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.fml.network.FMLNetworkConstants;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import redmine.cybermod.Item.ItemRegister;
import redmine.cybermod.Item.upgrade.Upgrader;
import redmine.cybermod.effect.ModEffect;
import redmine.cybermod.event.OverlayHandler;
import redmine.cybermod.network.SimplChannel;
import redmine.cybermod.utils.ModKeyBinding;
import redmine.cybermod.utils.Reference;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Reference.MOD_ID)
public class CyberMod {

    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    public CyberMod() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        // Register the enqueueIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        // Register the doClientStuff method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
        ItemRegister.ITEM_DEFERRED_REGISTER.register(eventBus);
        ModEffect.register(eventBus);

        if (FMLEnvironment.dist == Dist.CLIENT) {
            ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.DISPLAYTEST, () -> Pair.of(() -> FMLNetworkConstants.IGNORESERVERONLY, (a, b) -> true));
            FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
       //     ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Configs.CLIENT_SPEC);
        }
    }

    private void setup(final FMLCommonSetupEvent event) {
        // some preinit code
        Upgrader.setup();
        SimplChannel.setupNetwork();
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        // do something that can only be done on the client
        MinecraftForge.EVENT_BUS.register(new OverlayHandler());

        IEventBus bus = MinecraftForge.EVENT_BUS;

        ModKeyBinding.register();
        bus.addListener(ModKeyBinding::onKeyPress);
    }

    private void enqueueIMC(final InterModEnqueueEvent event) {
        // some example code to dispatch IMC to another mod

    }

    private void processIMC(final InterModProcessEvent event) {
        // some example code to receive and process InterModComms from other mods

    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        // do something when the server starts
    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
            // register a new block here
        }
    }
}
