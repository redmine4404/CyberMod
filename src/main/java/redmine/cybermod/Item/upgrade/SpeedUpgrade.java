package redmine.cybermod.Item.upgrade;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public class SpeedUpgrade implements IUpgrade{
    public String getName() {
        return "Speed upgrade";
    }

    public int getId(){
        return 2;
    }

    public void use(PlayerEntity player, ItemStack itemStack){

    }

    public int getEfficencyByLevel(int level){
        switch (level){
            case 1 : return 2;
            case 2 : return 5;
            case 3 : return 6;
            default : return 0;
        }
    }
}
