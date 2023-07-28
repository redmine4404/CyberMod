package redmine.cybermod.network;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleType;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.Effect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.registries.ForgeRegistries;
import redmine.cybermod.particle.ModParticles;

import javax.annotation.Resource;
import java.util.function.Supplier;

public class SpawnEmitterParticlePacket {

    ResourceLocation resourceLocation;

    public SpawnEmitterParticlePacket(ResourceLocation resourceLocation){
        this.resourceLocation = resourceLocation;
    }

    public static void encode(SpawnEmitterParticlePacket packet, PacketBuffer packetBuffer) {
        packetBuffer.writeResourceLocation(packet.resourceLocation);
    }

    public static SpawnEmitterParticlePacket decode(PacketBuffer packetBuffer) {
        return new SpawnEmitterParticlePacket(packetBuffer.readResourceLocation());
    }

    public static void handle(SpawnEmitterParticlePacket packet, Supplier<NetworkEvent.Context> ctx) {
        handle2(packet, ctx);
    }

    @OnlyIn(Dist.CLIENT)
    public static void handle2(SpawnEmitterParticlePacket packet, Supplier<NetworkEvent.Context> ctx){
        Minecraft.getInstance().particleEngine.createTrackingEmitter(Minecraft.getInstance().player, (IParticleData) ForgeRegistries.PARTICLE_TYPES.getValue(packet.resourceLocation), 30);
    }

}
