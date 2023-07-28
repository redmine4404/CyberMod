package redmine.cybermod.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.BlockPosArgument;
import net.minecraft.world.World;
import redmine.cybermod.tileentity.SmelterBlockTile;
import redmine.cybermod.utils.SmelterBlockUtil.SmelterBlockCraftRecipes;

import java.util.logging.Level;

public class testCommand {
    public testCommand(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(Commands.literal("test").then(Commands.literal("smelterBlockProsses").then(Commands.literal("process").then(Commands.argument("blockPos", BlockPosArgument.blockPos()).executes((command) -> {
            return smelterBlock(command, 1);
        }))).then(Commands.literal("init").executes((command) -> {return smelterBlock(command, 2);}))));
    }

    public int smelterBlock(CommandContext<CommandSource> context, int i) throws CommandSyntaxException {
        if(i == 1){
        CommandSource source = context.getSource();
        World world = source.getLevel();

        try {
            SmelterBlockTile smelterBlockTile = (SmelterBlockTile) world.getBlockEntity(BlockPosArgument.getOrLoadBlockPos(context, "blockPos"));
            smelterBlockTile.burn();
            return 1;

        } catch (ClassCastException e){
            System.out.println(e.getMessage());
            return 0;
        } catch (Exception e) {
            System.out.println(e.getCause());
            return 0;
            }
        } if (i == 2) {
            SmelterBlockCraftRecipes.init();
            return 1;
        }
        return 0;
    }
}

