package redmine.cybermod.network;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class TestPacket {
    public String heur;
    public TestPacket(String heur){
        this.heur = heur;
    }

    public static void encode(TestPacket testPacket, PacketBuffer packetBuffer) {
        packetBuffer.writeString(testPacket.heur);
    }

    public static TestPacket decode(PacketBuffer packetBuffer) {
        String heur = packetBuffer.readString();
        return new TestPacket(heur);
    }

    public static <MSG> void handle(TestPacket packet, Supplier<NetworkEvent.Context> ctx) {

        ServerPlayerEntity playerEntity = ctx.get().getSender();

        String heur = packet.heur;

        playerEntity.sendStatusMessage(new StringTextComponent(heur), true);

    }
}
