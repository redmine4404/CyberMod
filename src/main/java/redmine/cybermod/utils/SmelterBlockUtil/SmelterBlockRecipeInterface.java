package redmine.cybermod.utils.SmelterBlockUtil;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public interface SmelterBlockRecipeInterface {
    ItemStack result(ItemStack itemStack, boolean simulation);
}
