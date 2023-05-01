package redmine.cybermod.utils;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModSoundEvent {
    public static final DeferredRegister<SoundEvent> SOUND_EVENT =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Reference.MOD_ID );

    public static final RegistryObject<SoundEvent> chause = registerSoundEvent("chause");

    private static RegistryObject<SoundEvent> registerSoundEvent (String name) {
        return SOUND_EVENT.register(name, () -> new SoundEvent(new ResourceLocation(Reference.MOD_ID, name)));
    }

    public static void register(IEventBus eventBus){
        SOUND_EVENT.register(eventBus);

    }

}