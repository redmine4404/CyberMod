package redmine.cybermod.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.StringTextComponent;
import redmine.cybermod.Item.upgrade.Upgrader;

import java.util.UUID;

public class addModifier {
        public addModifier(CommandDispatcher<CommandSource> dispatcher){
            dispatcher.register(Commands.literal("addModififier").then(Commands.argument("upgradeId", IntegerArgumentType.integer()).then(Commands.argument("level", IntegerArgumentType.integer()).executes((command) -> {
                return modifier(command);
            }))));
        }


        private int modifier(CommandContext<CommandSource> context) {
            CommandSource source = context.getSource();


            if(source.getEntity() instanceof PlayerEntity){
                PlayerEntity player = (PlayerEntity) source.getEntity();
                ItemStack itemStack = player.inventory.getSelected();
                player.sendMessage(new StringTextComponent("bonjour la france"), UUID.randomUUID());


                if(Upgrader.addUpgrade(itemStack, IntegerArgumentType.getInteger(context, "upgradeId"), IntegerArgumentType.getInteger(context, "level")) != null){
            //        player.sendMessage(new StringTextComponent("modifier rajouter"), null);
                    return 1;

                } else {
            //        player.sendMessage(new StringTextComponent("imposible to add modifier"), null);
                    return 0;
                }

            } else {
            //    source.sendFeedback(new StringTextComponent("the command addModifier can be send only by a player!"), true);
                return 0;
            }
        }
}
