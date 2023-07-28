package redmine.cybermod.network;

import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.fml.network.NetworkEvent;
import redmine.cybermod.CyberMod;
import redmine.cybermod.tileentity.SmelterBlockTile;
import redmine.cybermod.utils.Reference;

import java.util.function.Supplier;

public class SmelterBlockProcessPacket {

    BlockPos pos;
    int WorldId;

    public SmelterBlockProcessPacket(BlockPos pos, int WorldId){
            this.pos = pos;
            this.WorldId = WorldId;
    }


    public static void encode(SmelterBlockProcessPacket packet, PacketBuffer packetBuffer){
        packetBuffer.writeBlockPos(packet.pos);
        packetBuffer.writeInt(packet.WorldId);
    }

    public static SmelterBlockProcessPacket decode(PacketBuffer packetBuffer){
        return new SmelterBlockProcessPacket(packetBuffer.readBlockPos(), packetBuffer.readInt());
    }
    public static void handle(SmelterBlockProcessPacket packet, Supplier<NetworkEvent.Context> ctx){
        try {
            SmelterBlockTile smelterBlockTile = (SmelterBlockTile) ctx.get().getSender().getLevel().getBlockEntity(packet.pos);
            smelterBlockTile.burn();
        } catch (ClassCastException e){
            ctx.get().getSender().connection.disconnect(new TranslationTextComponent(Reference.MOD_ID, "packet.SmelterBlockProcessPacket.invalid"));
        }
    }
}
