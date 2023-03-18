package redmine.cybermod.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.*;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

import java.util.Map;
import java.util.UUID;

public class HeavinessEffect extends Effect {

    protected HeavinessEffect(EffectType typeIn, int liquidColorIn) {
        super(typeIn, liquidColorIn);
    }

    public void removeAttributesModifiersFromEntity(LivingEntity entityLivingBaseIn, AttributeModifierManager attributeMapIn, int amplifier) {
        AttributeModifier attributeModifier = new AttributeModifier(UUID.randomUUID(), Attributes.MOVEMENT_SPEED.getAttributeName(), amplifier, AttributeModifier.Operation.MULTIPLY_TOTAL);
        entityLivingBaseIn.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.1);
        entityLivingBaseIn.getAttribute(Attributes.ATTACK_SPEED).setBaseValue(5.0D);
    }

    public void applyAttributesModifiersToEntity(LivingEntity entityLivingBaseIn, AttributeModifierManager attributeMapIn, int amplifier) {
        AttributeModifier attributeModifier = new AttributeModifier(UUID.randomUUID(), Attributes.MOVEMENT_SPEED.getAttributeName(), amplifier, AttributeModifier.Operation.MULTIPLY_TOTAL);
        entityLivingBaseIn.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.1D - (0.0050 * amplifier));
        entityLivingBaseIn.getAttribute(Attributes.ATTACK_SPEED).setBaseValue(5.0D - (0.065 * amplifier));
    }
}
