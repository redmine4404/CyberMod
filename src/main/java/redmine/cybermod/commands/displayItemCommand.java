package redmine.cybermod.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.command.arguments.ItemArgument;
import net.minecraft.command.arguments.ItemInput;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.fml.network.PacketDistributor;
import org.apache.logging.log4j.core.jmx.Server;
import redmine.cybermod.network.DisplayItem;
import redmine.cybermod.network.SimplChannel;

import java.awt.*;
import java.util.function.Supplier;

public class displayItemCommand {
    public displayItemCommand(CommandDispatcher<CommandSource> dispatcher){
        dispatcher.register(Commands.literal("displayItem").then(Commands.argument("player", EntityArgument.player()).then(Commands.argument("Item", ItemArgument.item()).executes((Command) -> {
            return displayItem(Command.getSource(), EntityArgument.getPlayer(Command, "player"), ItemArgument.getItem(Command, "Item"));
        }))));
    }

    public int displayItem(CommandSource source, ServerPlayerEntity playerEntity, ItemInput item) throws CommandSyntaxException{
            SimplChannel.INSTANCE.send(PacketDistributor.PLAYER.with(() -> playerEntity), new DisplayItem(new ItemStack(item.getItem())));
        return 1;
    }

}
