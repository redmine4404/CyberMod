package redmine.cybermod.utils;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import redmine.cybermod.Item.ItemRegister;

public class CyberTab {

    public static final ItemGroup CyberTab = new ItemGroup("CyberTab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ItemRegister.Redminite.get());

        }
    };

}
