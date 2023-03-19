package redmine.cybermod.network;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class DisplayItem {

    ItemStack itemStack;

    public DisplayItem(ItemStack itemStack){
        this.itemStack = itemStack;
    }

    public static void encode(DisplayItem packet, PacketBuffer packetBuffer) {
        packetBuffer.writeItemStack(packet.itemStack);
    }

    public static DisplayItem decode(PacketBuffer packetBuffer) {
        return new DisplayItem(packetBuffer.readItemStack());
    }

    public static void handle(DisplayItem packet, Supplier<NetworkEvent.Context> ctx) {
        Minecraft.getInstance().gameRenderer.displayItemActivation(packet.itemStack);
    }
}
