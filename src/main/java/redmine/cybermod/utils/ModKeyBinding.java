package redmine.cybermod.utils;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.lwjgl.glfw.GLFW;
import redmine.cybermod.network.SimplChannel;
import redmine.cybermod.network.TestPacket;

public class ModKeyBinding {

    public static final KeyBinding TEST_KEY = new KeyBinding("key." + Reference.MOD_ID + ".test_key", GLFW.GLFW_KEY_R, "key.categories.misc");

    public static void register(){
        ClientRegistry.registerKeyBinding(TEST_KEY);
    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void onKeyPress(InputEvent.KeyInputEvent e){
        if(TEST_KEY.isKeyDown()) {
            SimplChannel.INSTANCE.sendToServer(new TestPacket(String.valueOf(System.currentTimeMillis())));
        }
    }
}
