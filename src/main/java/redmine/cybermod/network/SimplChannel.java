package redmine.cybermod.network;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import redmine.cybermod.utils.Reference;

import java.util.Optional;

public class SimplChannel {
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(Reference.MOD_ID, "main"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    public static void setupNetwork(){
        int index = 0;
        //INSTANCE.registerMessage(index++, TestPacket.class, TestPacket::encode, TestPacket::decode, TestPacket::handle, Optional.of(NetworkDirection.PLAY_TO_SERVER));
        INSTANCE.registerMessage(index++, DisplayItem.class, DisplayItem::encode, DisplayItem::decode, DisplayItem::handle, Optional.of(NetworkDirection.PLAY_TO_CLIENT));
        INSTANCE.registerMessage(index++, SpawnEmitterParticlePacket.class, SpawnEmitterParticlePacket::encode, SpawnEmitterParticlePacket::decode, SpawnEmitterParticlePacket::handle, Optional.of(NetworkDirection.PLAY_TO_CLIENT));
        INSTANCE.registerMessage(index++, SmelterBlockProcessPacket.class, SmelterBlockProcessPacket::encode , SmelterBlockProcessPacket::decode, SmelterBlockProcessPacket::handle, Optional.of(NetworkDirection.PLAY_TO_SERVER));

    }
}
