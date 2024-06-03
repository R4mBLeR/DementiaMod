package com.r4mble.dementia.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.r4mble.dementia.DementiaMod;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;


public class DementiaStartCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("dementia").then(Commands.literal("start").executes(DementiaStartCommand::execute)));
    }


    private static int execute(CommandContext<CommandSourceStack> command) {
        if (command.getSource().getEntity() instanceof Player) {
            if (DementiaMod.dementia == null) {
                DementiaMod.currentServer = command.getSource().getServer();
                DementiaMod.dementia = new DementiaThread();
                DementiaMod.dementia.start();
                command.getSource().getPlayer().sendSystemMessage(Component.literal("Dementia is started!"));
            } else {
                command.getSource().getPlayer().sendSystemMessage(Component.literal("Dementia is already started."));
            }
        }
        return Command.SINGLE_SUCCESS;
    }
}

