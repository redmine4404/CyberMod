package redmine.cybermod.utils;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.NonNullList;
import redmine.cybermod.Item.ItemRegister;
import redmine.cybermod.Item.ModArmorTiers;
import redmine.cybermod.effect.ModEffect;

public class ArmorEffectUtils {
    public static void checkNBT(CompoundNBT nbt){
        if(!nbt.contains("update")){
       //     System.out.println("ertghj");
            nbt.putBoolean("update", true);
        }
    }
    public static boolean isValid(CompoundNBT nbt){
        checkNBT(nbt);
        return nbt.getBoolean("update");
    }

    public static void deValidArmor(NonNullList<ItemStack> itemStacks){
        for (ItemStack itemStack : itemStacks){
            if(itemStack != null && itemStack.getItem() instanceof ArmorItem){
                if(((ArmorItem) itemStack.getItem()).getMaterial() == ModArmorTiers.CompressedIron){
                    checkNBT(itemStack.getTag());
       //             System.out.println("devalid armor :" + itemStack);

                    setValidItem(itemStack, false);
                }
            }

            // System.out.println("devalid armor");
        }
    }

    public static void setValidItem(ItemStack itemStack, boolean valid){
        CompoundNBT nbt = itemStack.getTag();
        checkNBT(nbt);

     //   System.out.println(valid);
        nbt.putBoolean("update", valid);
    }

    public static void update(PlayerEntity player){
        if(player.hasEffect(ModEffect.Heaviness.get())) player.removeEffect(ModEffect.Heaviness.get());
        if(calculEffect(player.inventory.armor) == 0) return;

        EffectInstance effectInstance = new EffectInstance(ModEffect.Heaviness.get(), Integer.MAX_VALUE, calculEffect(player.inventory.armor) - 1);

        player.addEffect(effectInstance);
        //System.out.println("truc");
    }

    public static int calculEffect(NonNullList<ItemStack> itemStacks){
        int i = 0;

        for (ItemStack itemStack : itemStacks){
            if(itemStack.getItem() != Items.AIR && ((ArmorItem)itemStack.getItem()).getMaterial() == ModArmorTiers.CompressedIron) i++;
        }
    //    System.out.println(i);
        return i;
    }
}
