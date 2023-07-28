package redmine.cybermod.Item.upgrade;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import java.util.UUID;

public class TestUpgrade implements IUpgrade {
     @Override
     public String getName() {
          return "Test Upgrade";
     }

    @Override
    public int getId(){
        return 1;
    }

     @Override
     public void use(PlayerEntity player, ItemStack itemStack) {
          player.sendMessage(new StringTextComponent("test"), UUID.randomUUID());
     }


}
