package redmine.cybermod.Item.upgrade;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public interface IUpgrade {

    public String getName();

    public int getId();

    public void use(PlayerEntity player, ItemStack itemStack);

}
