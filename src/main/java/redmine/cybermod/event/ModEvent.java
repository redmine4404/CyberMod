package redmine.cybermod.event;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.CustomMainMenu;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.Hand;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.event.entity.living.PotionEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.server.command.ConfigCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import redmine.cybermod.CyberMod;
import net.minecraft.client.gui.screen.MainMenuScreen;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import redmine.cybermod.Item.ItemRegister;
import redmine.cybermod.Item.ModArmorTiers;
import redmine.cybermod.commands.addModifier;
import redmine.cybermod.commands.setModifier;
import redmine.cybermod.effect.ModEffect;
import redmine.cybermod.utils.ArmorEffectUtils;
import redmine.cybermod.utils.Reference;

import static net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType.ARMOR;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class ModEvent {

    private static final Logger LOGGER = LogManager.getLogger();

    @SubscribeEvent
    public static void OnOpenGuiEvent(GuiOpenEvent event) {
        if (event.getGui() != null) {
            if (event.getGui().getClass() == MainMenuScreen.class) {
                event.setGui(new CustomMainMenu(false));
            }
        }
    }

    @SubscribeEvent
    public static void onInteract (PlayerInteractEvent event){
        if(event.getItemStack().getItem() == ItemRegister.Hearth.get()){
            if (event.getHand() == Hand.MAIN_HAND){

                PlayerEntity player = event.getPlayer();
                ItemStack item = event.getItemStack();
                ModifiableAttributeInstance attribute = player.getAttribute(Attributes.MAX_HEALTH);

                if(attribute.getBaseValue() < 40){
                //LOGGER.info("the player : " + player.getName().getString() + " use a hearth to increment he max live, he live is now " );

                attribute.setBaseValue(attribute.getBaseValue() + 2);
                item.setCount(item.getCount() - 1);
                LOGGER.info("the player : " + player.getName().getString() + " use a hearth to increment he max live, he live is now " + attribute.getBaseValue());
                }
            }
        } else if(event.getItemStack().getItem() == ItemRegister.GodItem.get()){
            if (event.getHand() == Hand.MAIN_HAND){

                PlayerEntity player = event.getPlayer();
                ItemStack item = event.getItemStack();

                player.addPotionEffect(new EffectInstance(Effects.INSTANT_HEALTH, 5, 256));
                player.getFoodStats().setFoodLevel(40);
                player.getFoodStats().setFoodSaturationLevel(40);

                item.setCount(item.getCount() - 1);
                }
        } else if (event.getItemStack().getItem() == ItemRegister.RedminitePickaxe.get()) {

        }
    }

    @SubscribeEvent
    public static void onCommandRegister(RegisterCommandsEvent event) {
        new addModifier(event.getDispatcher());
        new setModifier(event.getDispatcher());
        ConfigCommand.register(event.getDispatcher());
    }

    @SubscribeEvent
    public static void onEquipArmor(LivingEquipmentChangeEvent event) {
        if(event.getFrom().getItem() == event.getTo().getItem()){
           return;
        }
            if(event.getEntityLiving() instanceof PlayerEntity) ArmorEffectUtils.update((PlayerEntity) event.getEntityLiving());
    }

    @SubscribeEvent
    public static void onEffectEnd(PotionEvent.PotionExpiryEvent event) {
        if(event.getPotionEffect().getPotion() == ModEffect.Heaviness.get()){
            if(event.getEntityLiving() instanceof PlayerEntity) ArmorEffectUtils.update((PlayerEntity) event.getEntityLiving());
        }
    }

  //  @SubscribeEvent
   /** public static void onRenderGameOverlay(RenderGameOverlayEvent event){
        if(event.getType() == RenderGameOverlayEvent.ElementType.ARMOR){
            MatrixStack mStack = event.getMatrixStack();

                if (pre(ARMOR, mStack)) return;
                mc.getProfiler().startSection("armor");

                RenderSystem.enableBlend();
                int left = width / 2 - 91;
                int top = height - left_height;

                int level = mc.player.getTotalArmorValue();
                for (int i = 1; level > 0 && i < 20; i += 2)
                {
                    if (i < level)
                    {
                        blit(mStack, left, top, 34, 9, 9, 9);
                    }
                    else if (i == level)
                    {
                        blit(mStack, left, top, 25, 9, 9, 9);
                    }
                    else if (i > level)
                    {
                        blit(mStack, left, top, 16, 9, 9, 9);
                    }
                    left += 8;
                }
                left_height += 10;

                RenderSystem.disableBlend();
                mc.getProfiler().endSection();
                post(ARMOR, mStack);
        }
    } **/

   @SubscribeEvent
   public void onRenderHUD(RenderGameOverlayEvent.Post event)
   {
       if (event.getType().equals(RenderGameOverlayEvent.ElementType.ALL))
       {
           //Ton code de rendu ici
       }
   }

}
