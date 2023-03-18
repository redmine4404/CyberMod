package redmine.cybermod.Item;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.stats.Stats;
import net.minecraft.world.World;
import redmine.cybermod.utils.CyberTab;

public class CheeseItem extends Item {
    public CheeseItem() {
        super(new Properties().group(CyberTab.CyberTab).maxStackSize(8).food(new Food.Builder().fastToEat().hunger(5).saturation(2.0F).build()));
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
        if(entityLiving instanceof PlayerEntity) {
                System.out.println("test");
                EntityRenderer renderer;
                ServerPlayerEntity serverPlayerEntity = ((PlayerEntity) entityLiving).getServer().getPlayerList().getPlayerByUUID(((PlayerEntity) entityLiving).getUniqueID());



        }
        return this.isFood() ? entityLiving.onFoodEaten(worldIn, stack) : stack;
    }
}
