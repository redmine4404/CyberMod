package redmine.cybermod.Item;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EmitterParticle;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.*;
import net.minecraft.network.play.server.SSpawnParticlePacket;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.potion.Potions;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.integrated.IntegratedServer;
import net.minecraft.server.management.PlayerList;
import net.minecraft.stats.Stats;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.PacketDistributor;
import net.minecraftforge.registries.ForgeRegistries;
import redmine.cybermod.network.DisplayItem;
import redmine.cybermod.network.SimplChannel;
import redmine.cybermod.network.SpawnEmitterParticlePacket;
import redmine.cybermod.particle.ChauseParticle;
import redmine.cybermod.particle.ModParticles;
import redmine.cybermod.utils.CyberTab;
import redmine.cybermod.utils.ModSoundEvent;

public class CheeseItem extends Item {
    public CheeseItem() {
        super(new Properties().tab(CyberTab.CyberTab).stacksTo(8).food(new Food.Builder().fast().nutrition(1).saturationMod(0.5F).meat().build()));
    }

    @Override
    public ItemStack finishUsingItem(ItemStack itemStack, World worldIn, LivingEntity entityLiving) {
        ItemStack stack = super.finishUsingItem(itemStack, worldIn, entityLiving);
        if(entityLiving instanceof ServerPlayerEntity) {
            ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity) entityLiving;

            if(worldIn.getRandom().nextInt(4) == 0){
            SimplChannel.INSTANCE.send(PacketDistributor.PLAYER.with(() -> serverPlayerEntity), new DisplayItem(new ItemStack(ItemRegister.chause.get())));
            SimplChannel.INSTANCE.send(PacketDistributor.PLAYER.with(() -> serverPlayerEntity), new SpawnEmitterParticlePacket(ModParticles.CHAUSE_PARTICLE.getId()));
            serverPlayerEntity.addEffect(new EffectInstance(Effects.REGENERATION, 200, 1));
            serverPlayerEntity.addEffect(new EffectInstance(Effects.SATURATION, 200, 2));
            serverPlayerEntity.addEffect(new EffectInstance(Effects.DAMAGE_BOOST, 600, 1));
            serverPlayerEntity.playNotifySound(ModSoundEvent.chause.get(), SoundCategory.MASTER, 100, 1F);
            serverPlayerEntity.getFoodData().eat(4, 2.0F);
            return stack;
            }

            if(worldIn.getRandom().nextInt(6) == 0){
                serverPlayerEntity.addEffect(new EffectInstance(Effects.CONFUSION, 200, 1));
                serverPlayerEntity.addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 200, 1));
            }
        }
        return stack;
    }
}
