package redmine.cybermod.Item.upgrade;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

public class TestUpgrade extends Upgrade{

    @Override
    public String getName() {
        return "Test upgrade";
    }

    @Override
    public int getId(){
        return 1;
    }

    @Override
    public void use(PlayerEntity player, ItemStack itemStack) {
        player.sendMessage(new StringTextComponent(TextFormatting.YELLOW + "Use"), null);
    }


}
