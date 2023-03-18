package redmine.cybermod.Item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.World;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import redmine.cybermod.Item.custom.CompressedIronArmorItem;
import redmine.cybermod.Item.custom.RedminitePickaxe;
import redmine.cybermod.Item.custom.RedminiteSword;
import redmine.cybermod.utils.ArmorEffectUtils;
import redmine.cybermod.utils.CyberTab;
import redmine.cybermod.utils.Reference;

public class ItemRegister {
    public static final DeferredRegister<Item> ITEM_DEFERRED_REGISTER = DeferredRegister.create(ForgeRegistries.ITEMS, Reference.MOD_ID);

    public static final RegistryObject<redmine.cybermod.Item.custom.RedminitePickaxe> RedminitePickaxe = ITEM_DEFERRED_REGISTER.register("redminite_pickaxe", () -> new RedminitePickaxe(ModItemTiers.Redminite, 1, -2.8F, new Item.Properties().group(CyberTab.CyberTab)));
    public static final RegistryObject<redmine.cybermod.Item.custom.RedminiteSword> RedminiteSword = ITEM_DEFERRED_REGISTER.register("redminite_sword", () -> new RedminiteSword(ModItemTiers.Redminite, 4, -2.6F, new Item.Properties().group(CyberTab.CyberTab)));
    public static final RegistryObject<Item> Redminite = ITEM_DEFERRED_REGISTER.register("redminite", () -> new Item(new Item.Properties().group(CyberTab.CyberTab)));


    public static final RegistryObject<Item> GodItem = ITEM_DEFERRED_REGISTER.register("god_item", () -> new Item(new Item.Properties().group(CyberTab.CyberTab)));
    public static final RegistryObject<Item> Hearth = ITEM_DEFERRED_REGISTER.register("hearth", () -> new Item(new Item.Properties().maxStackSize(1).group(CyberTab.CyberTab)));

    public static final RegistryObject<Item> HammerUpgrade = ITEM_DEFERRED_REGISTER.register("hammer_upgrade", () -> new Item(new Item.Properties().group(CyberTab.CyberTab).maxStackSize(1)));

    public static final RegistryObject<Item> CompressedIronIngot = ITEM_DEFERRED_REGISTER.register("compressed_iron_ingot", () -> new Item(new Item.Properties().group(CyberTab.CyberTab).maxStackSize(1)));

    public static final RegistryObject<Item> CompressedIronSword = ITEM_DEFERRED_REGISTER.register("compressed_iron_sword", () -> new SwordItem(ModItemTiers.CompressedIron, 5, -3.2F, new Item.Properties().group(CyberTab.CyberTab)));
    public static final RegistryObject<Item> CompressedIronShovel = ITEM_DEFERRED_REGISTER.register("compressed_iron_shovel", () -> new ShovelItem(ModItemTiers.CompressedIron, 2.5F, -3.4F, new Item.Properties().group(CyberTab.CyberTab)));
    public static final RegistryObject<Item> CompressedIronPickaxe = ITEM_DEFERRED_REGISTER.register("compressed_iron_pickaxe", () -> new PickaxeItem(ModItemTiers.CompressedIron, 6, -3.4F, new Item.Properties().group(CyberTab.CyberTab)));
    public static final RegistryObject<Item> CompressedIronAxe = ITEM_DEFERRED_REGISTER.register("compressed_iron_axe", () -> new AxeItem(ModItemTiers.CompressedIron, 5, -3.3F, new Item.Properties().group(CyberTab.CyberTab)));
    //public static final RegistryObject<Item> CompressedIronTest = ITEM_DEFERRED_REGISTER.register("compressed_iron_test", () -> new AxeItem(ModItemTiers.CompressedIron, 5, 1F, new Item.Properties().group(CyberTab.CyberTab)));

    public static final RegistryObject<Item> cheese = ITEM_DEFERRED_REGISTER.register("cheese", () -> new CheeseItem());

    public static final RegistryObject<Item> CompressedIronBoot = ITEM_DEFERRED_REGISTER.register("compressed_iron_boot",
            () -> new ArmorItem(ModArmorTiers.CompressedIron, EquipmentSlotType.FEET, new Item.Properties().group(CyberTab.CyberTab)));

    public static final RegistryObject<Item> CompressedIronLegging = ITEM_DEFERRED_REGISTER.register("compressed_iron_leggings",
            () -> new ArmorItem(ModArmorTiers.CompressedIron, EquipmentSlotType.LEGS, new Item.Properties().group(CyberTab.CyberTab)));

    public static final RegistryObject<Item> CompressedIronChestplate = ITEM_DEFERRED_REGISTER.register("compressed_iron_chestplate",
            () -> new ArmorItem(ModArmorTiers.CompressedIron, EquipmentSlotType.CHEST, new Item.Properties().group(CyberTab.CyberTab)));

    public static final RegistryObject<Item> CompressedIronHelmet = ITEM_DEFERRED_REGISTER.register("compressed_iron_helmet",
            () -> new ArmorItem(ModArmorTiers.CompressedIron, EquipmentSlotType.HEAD, new Item.Properties().group(CyberTab.CyberTab)));
}