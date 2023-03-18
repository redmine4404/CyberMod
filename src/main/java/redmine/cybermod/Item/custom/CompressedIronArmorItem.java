package redmine.cybermod.Item.custom;

import com.google.common.collect.ImmutableMap;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import redmine.cybermod.Item.ItemRegister;
import redmine.cybermod.effect.ModEffect;

import java.util.Map;
import java.util.Objects;

public class CompressedIronArmorItem extends ArmorItem {
    public CompressedIronArmorItem(IArmorMaterial material, EquipmentSlotType slot, Properties settings) {
        super(material, slot, settings);
    }

    @Override
    public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
        if(!world.isRemote()) {
            player.addPotionEffect(new EffectInstance(ModEffect.Heaviness.get(), 20, getAmplifierWithArmor(player)));
        }
        super.onArmorTick(stack, world, player);
    }

    public int getAmplifierWithArmor(PlayerEntity player){

        int i = 0;
        NonNullList<ItemStack> itemStacks =  player.inventory.armorInventory;

        if(itemStacks.get(0).getItem() == ItemRegister.CompressedIronBoot.get()){
            i++;
        }

        if(itemStacks.get(1).getItem() == ItemRegister.CompressedIronLegging.get()){
            i++;
        }

        if(itemStacks.get(2).getItem() == ItemRegister.CompressedIronChestplate.get()){
            i++;
        }

        if(itemStacks.get(3).getItem() == ItemRegister.CompressedIronHelmet.get()){
            i++;
        }
       // System.out.println(i);

        return i - 1;
    }

}
