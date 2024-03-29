package redmine.cybermod.Item.upgrade;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.system.CallbackI;

import java.util.Arrays;

public class Upgrader {

    public static TestUpgrade testUpgrade;
    public static SpeedUpgrade speedUpgrade;
    public static void setup(){
        testUpgrade = new TestUpgrade();
        speedUpgrade = new SpeedUpgrade();
    }

    private static final Logger LOGGER = LogManager.getLogger();

    public static void checkNbt(ItemStack itemStack){

        if (itemStack.getTag() == null || !itemStack.getTag().contains("Upgrade1")){
            initUpgrade(itemStack);
        }

    }

    public static void initUpgrade(ItemStack itemStack){

        CompoundNBT nbt = new CompoundNBT();

        //la premère variable dans asList reprèsente l'id de l'upgrade, la deuxième variable reprèsente le niveau de l'upgrad.
        //quand l'id est égals a 0, l'upgrade est null
        nbt.putIntArray("Upgrade1", new int[]{0, 0});
        nbt.putIntArray("Upgrade2", new int[]{0, 0});
        nbt.putIntArray("Upgrade3", new int[]{0, 0});

        itemStack.setTag(nbt);

     //   System.out.println(nbt.getIntArray("Upgrade1").length);
    }

    public static void setLevelForUpgrade(ItemStack itemStack, int upgrade, int level){
        checkNbt(itemStack);
        CompoundNBT nbt = itemStack.getTag();

        //la premère variable dans asList reprèsente l'id de l'upgrade, la deuxième variable reprèsente le niveau de l'upgrad.
        //quand l'id est égals a 0, l'upgrade est null
        nbt.putIntArray("Upgrade" + upgrade, Arrays.asList(nbt.getIntArray("Uses")[0], level ));

        itemStack.setTag(new CompoundNBT());


    }

    public static int[] getUpgrade (ItemStack itemStack, int upgrade){
        checkNbt(itemStack);
        CompoundNBT nbt = itemStack.getTag();

        //la premère variable dans asList reprèsente l'id de l'upgrade, la deuxième variable reprèsente le niveau de l'upgrad.
        //quand l'id est égals a 0, l'upgrade est null

    //    System.out.println(nbt.getIntArray("Upgrade1").length);
        return nbt.getIntArray("Upgrade" + upgrade);
    }

    public static int getUpgradeSlot(ItemStack itemStack, int upgradeId){
        checkNbt(itemStack);
        CompoundNBT nbt = itemStack.getTag();

        for (int i = 1; i <= 3; i++){
            if(nbt.getIntArray("Upgrade" + i)[0] == upgradeId){
                return i;
            }
        }
        return 0;
    }
    //if getEmptySlot return 0, there is not free slot
    public static int getEmptySlot(ItemStack itemStack){
        checkNbt(itemStack);
        CompoundNBT nbt = itemStack.getTag();
        for(int i = 1; i <= 3; i++){
            if(nbt.getIntArray("Upgrade" + i)[0] == 0){
                System.out.println(nbt);
                return i;
            }
        }
        return 0;
    }

    public static int setUpgrade(ItemStack itemStack, int id, int upgrade, int level){
        checkNbt(itemStack);
        CompoundNBT tag = itemStack.getTag();


    tag.putIntArray("Upgrade" + id, Arrays.asList(upgrade, level));

    itemStack.setTag(tag);
    return 1;
    }

    //add upgrade, if the upgrade already exist but in different level, the new upgrade replace the ancient (only if the newx upgrade is more )
    public static ItemStack addUpgrade(ItemStack itemStack, int idUpgrade, int level){
        checkNbt(itemStack);

        for (int i = 0;  i <= 3 ;i++){
            if(getUpgrade(itemStack, i).length != 0 && getUpgrade(itemStack, i)[0] == idUpgrade){
                if(getUpgrade(itemStack, i)[1] < level){
                    setUpgrade(itemStack, i,idUpgrade, level);
                    return itemStack;
                }
            }
        }

        if (getEmptySlot(itemStack) != 0){
            setUpgrade(itemStack, getEmptySlot(itemStack), idUpgrade, level);
            return itemStack;
        }
        return itemStack;
    }

    public static String getNameOfModifier(int i){
        switch (i){

            case 0 :
            return "";

            case 1: {
                return testUpgrade.getName();
            }
            case 2 :
            return speedUpgrade.getName();

            default: return "null";
        }
    }

    public static IUpgrade getClassUpgrade(int id){
        switch (id){
            case 1 : return new TestUpgrade();
            case 2 : return new SpeedUpgrade();
            default: return null;
        }
    }

    public static IUpgrade[] getAllClassUpgrade(ItemStack itemStack)
    {
        IUpgrade[] upgradeClass = new IUpgrade[3];
        CompoundNBT nbt = itemStack.getTag();

        upgradeClass[0] = getClassUpgrade(nbt.getIntArray("Upgrade1")[0]);
        upgradeClass[1] = getClassUpgrade(nbt.getIntArray("Upgrade2")[0]);
        upgradeClass[2] = getClassUpgrade(nbt.getIntArray("Upgrade3")[0]);

        return upgradeClass;
    }

    public static int getupgradeValue(ItemStack itemStack, int upgradeId){
        checkNbt(itemStack);
        if(getUpgradeSlot(itemStack, upgradeId) != 0){
        return getUpgrade(itemStack, getUpgradeSlot(itemStack, upgradeId))[1];
        } else {
            return 0;
        }
    }
}