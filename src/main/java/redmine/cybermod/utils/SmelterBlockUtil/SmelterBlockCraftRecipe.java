package redmine.cybermod.utils.SmelterBlockUtil;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;

public class SmelterBlockCraftRecipe {
    private SmelterBlockRecipeInterface result;
    private Item item1, item2, item3;

    int durationTick;
    int fuelNeed;

    int id;

    public SmelterBlockCraftRecipe(SmelterBlockRecipeInterface result, Item item1, Item item2, Item item3, int durationTick, int fuelNeed, int id) {
        this.result = result;
        this.item1 = item1;
        this.item2 = item2;
        this.item3 = item3;
        this.durationTick = durationTick;
        this.fuelNeed = fuelNeed;
        this.id = id;
    }

    public ItemStack getItemResult(ItemStack itemStack) {
        return result.result(itemStack, false);
    }

    public ArrayList<Item> getItemNeed() {
        ArrayList<Item> list = new ArrayList<>();
        list.add(item1);
        list.add(item2);
        list.add(item3);
        return list;
    }

    public int getDurationTick() {
        return durationTick;
    }

    public int getFuelNeed() {
        return fuelNeed;
    }

    public int getId(){
        return id;
    }

    public boolean isCratable(ItemStack itemStack, int fuel){

        if(fuel < fuelNeed){
            return false;
        }

        return result.result(itemStack, true) != null;
    }
}
