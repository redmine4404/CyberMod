package redmine.cybermod.Item.custom;

import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.*;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import redmine.cybermod.Item.upgrade.Upgrade;
import redmine.cybermod.Item.upgrade.Upgrader;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;

public class RedminitePickaxe extends PickaxeItem {
    public RedminitePickaxe(IItemTier tiers, int attackDamage, float attackSpeed, Properties properties) {
        super(tiers, attackDamage, attackSpeed, properties);
    }

    @Override
    public void addInformation(ItemStack item, @Nullable World world, List<ITextComponent> text, ITooltipFlag p_77624_4_) {
        if (Upgrader.getUpgrade(item, 1)[0] != 0) {
            text.add(new StringTextComponent(TextFormatting.GRAY + "Modifier 1 : " + TextFormatting.WHITE + Upgrader.getNameOfModifier(Upgrader.getUpgrade(item, 1)[0]) + " " + new TranslationTextComponent("enchantment.level." + Upgrader.getUpgrade(item, 1)[1]).getString()));
        }
        if (Upgrader.getUpgrade(item, 2)[0] != 0) {
            text.add(new StringTextComponent(TextFormatting.GRAY + "Modifier 2 : " + TextFormatting.WHITE + Upgrader.getNameOfModifier(Upgrader.getUpgrade(item, 2)[0]) + " " + new TranslationTextComponent("enchantment.level." + Upgrader.getUpgrade(item, 2)[1]).getString()));
        }
        if (Upgrader.getUpgrade(item, 3)[0] != 0) {
            text.add(new StringTextComponent(TextFormatting.GRAY + "Modifier 3 : " + TextFormatting.WHITE + Upgrader.getNameOfModifier(Upgrader.getUpgrade(item, 3)[0]) + " " + new TranslationTextComponent("enchantment.level." + Upgrader.getUpgrade(item, 3)[1]).getString()));
        }
    }

    @Override
    @OnlyIn(Dist.DEDICATED_SERVER)
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        System.out.println("trucd");
        //if(!worldIn.isRemote){
        ItemStack stack = playerIn.getHeldItem(handIn);
        Upgrade upgradeList[] = Upgrader.getAllClassUpgrade(stack);

        for (Upgrade upgrade : upgradeList) {
            if (upgrade != null) {
                upgrade.use(playerIn, stack);
            }
        }
        // }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }


    @Override
    public float getDestroySpeed(ItemStack itemStack, BlockState blockState) {
        System.out.println(Upgrader.speedUpgrade.getEfficencyByLevel(Upgrader.getupgradeValue(itemStack, 2)));
        return super.getDestroySpeed(itemStack, blockState) + Upgrader.speedUpgrade.getEfficencyByLevel(Upgrader.getupgradeValue(itemStack, 2));
    }
}