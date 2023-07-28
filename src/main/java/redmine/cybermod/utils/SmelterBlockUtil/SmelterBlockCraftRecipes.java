package redmine.cybermod.utils.SmelterBlockUtil;

import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import redmine.cybermod.Item.ItemRegister;
import redmine.cybermod.Item.upgrade.Upgrader;

import javax.annotation.Nullable;
import java.util.ArrayList;

public class SmelterBlockCraftRecipes {
    public static ArrayList<SmelterBlockCraftRecipe> smelterBlockCraftRecipes = new ArrayList<SmelterBlockCraftRecipe>();

    @Nullable
    public static  SmelterBlockCraftRecipe getrecipeByResult(ItemStack item){
        for(SmelterBlockCraftRecipe smelterBlockCraftRecipe : smelterBlockCraftRecipes){
            if(smelterBlockCraftRecipe.getItemResult(null) == item){
                return smelterBlockCraftRecipe;
            }
        }
        return null;
    }

    @Nullable
    public static SmelterBlockCraftRecipe getRecipeByItem(Item item1, Item item2, Item item3){
        for(SmelterBlockCraftRecipe smelterBlockCraftRecipe : smelterBlockCraftRecipes){
            if(smelterBlockCraftRecipe.getItemNeed().get(0) == item1 && smelterBlockCraftRecipe.getItemNeed().get(1) == item2 && smelterBlockCraftRecipe.getItemNeed().get(2) == item3){
                return smelterBlockCraftRecipe;
            }
        }
        return null;
    }

    @Nullable
    public static SmelterBlockCraftRecipe getRecipeById(int id){
        for (SmelterBlockCraftRecipe recipe : smelterBlockCraftRecipes){
            if(recipe.id == id){
                return recipe;
            }
        }
        return null;
    }

    public static void init(){
        smelterBlockCraftRecipes.removeAll(smelterBlockCraftRecipes);
        smelterBlockCraftRecipes.add(new SmelterBlockCraftRecipe((itemstack, simulation) -> new ItemStack(Items.WOODEN_PICKAXE), Item.byBlock(Blocks.DIRT), Item.byBlock(Blocks.DIRT), Item.byBlock(Blocks.DIRT), 200,50, 1));
        smelterBlockCraftRecipes.add(new SmelterBlockCraftRecipe((itemstack, simulation) -> {
            int level = 0;

            System.out.println("Name : " + itemstack.getDisplayName().getContents() + " " + Upgrader.getEmptySlot(itemstack));

            if(Upgrader.getEmptySlot(itemstack) == 0){
                return null;
            }

            if(Upgrader.getUpgrade(itemstack, Upgrader.getUpgradeSlot(itemstack, 1)).length != 0){
                level = Upgrader.getUpgrade(itemstack, Upgrader.getUpgradeSlot(itemstack, 1))[1];
            }
            if(!simulation) itemstack = Upgrader.addUpgrade(itemstack, 1, level + 1);

            return itemstack;
        }, Items.DIAMOND, ItemRegister.RedminitePickaxe.get(), Items.DIAMOND, 100,20, 2));
    }

}

