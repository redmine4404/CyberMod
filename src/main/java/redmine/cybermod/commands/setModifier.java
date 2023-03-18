package redmine.cybermod.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.serializers.IntArgumentSerializer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.StringTextComponent;
import redmine.cybermod.Item.upgrade.Upgrader;

public class setModifier {
        public setModifier(CommandDispatcher<CommandSource> dispatcher){
            dispatcher.register(Commands.literal("setModififier").then(Commands.argument("placeOnList", IntegerArgumentType.integer()).then(Commands.argument("upgradeId", IntegerArgumentType.integer()).then(Commands.argument("level", IntegerArgumentType.integer()).executes((command) -> {
                return modifier(command);
            })))));
        }


        private int modifier(CommandContext<CommandSource> context) {

            CommandSource source = context.getSource();

            if(source.getEntity() instanceof PlayerEntity){
                PlayerEntity player = (PlayerEntity) source.getEntity();
                ItemStack itemStack = player.inventory.getCurrentItem();

                if(Upgrader.setUpgrade(itemStack, IntegerArgumentType.getInteger(context, "placeOnList"), IntegerArgumentType.getInteger(context, "upgradeId"), IntegerArgumentType.getInteger(context, "level") ) == 1){
                    player.sendMessage(new StringTextComponent("modifier rajouter"), null);
                    return 1;

                } else {
                   player.sendMessage(new StringTextComponent("imposible to set modifier"), null);
                    return 0;
                }

            } else {
                source.sendFeedback(new StringTextComponent("the command addModifier can be send only by a player!"), true);
                return 0;
            }
        }
}
