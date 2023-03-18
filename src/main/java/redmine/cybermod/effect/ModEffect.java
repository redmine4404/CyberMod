package redmine.cybermod.effect;

import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import redmine.cybermod.utils.Reference;

public class ModEffect {
    public static final DeferredRegister<Effect> MOD_EFFECT = DeferredRegister.create(ForgeRegistries.POTIONS, Reference.MOD_ID);

    public static final RegistryObject<Effect> Heaviness = MOD_EFFECT.register("heaviness", () -> new HeavinessEffect(EffectType.NEUTRAL, 14780758));

    public static void register(IEventBus eventBus){
        MOD_EFFECT.register(eventBus);
    }


}
