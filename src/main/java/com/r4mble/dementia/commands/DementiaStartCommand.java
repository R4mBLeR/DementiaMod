package com.r4mble.dementia.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.r4mble.dementia.DementiaMod;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.ChatType;
import net.minecraft.network.chat.OutgoingChatMessage;
import net.minecraft.network.chat.PlayerChatMessage;
import net.minecraft.world.entity.player.Player;


public class DementiaStartCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("dementia").then(Commands.literal("start").executes(DementiaStartCommand::execute)));
    }


    private static int execute(CommandContext<CommandSourceStack> command) {
        if (command.getSource().getEntity() instanceof Player) {
            Player player = command.getSource().getPlayer();
            if (DementiaMod.dementia == null) {
                DementiaMod.currentServer = command.getSource().getServer();
                DementiaMod.dementia = new DementiaThread();
                DementiaMod.dementia.start();
                PlayerChatMessage chatMessage = PlayerChatMessage.unsigned(player.getUUID(), "Dementia is started!");
                command.getSource().getPlayer().sendChatMessage(new OutgoingChatMessage.Player(chatMessage), false, ChatType.bind(ChatType.CHAT, player));
            } else {
                PlayerChatMessage chatMessage = PlayerChatMessage.unsigned(player.getUUID(), "Dementia is already started.");
                command.getSource().getPlayer().sendChatMessage(new OutgoingChatMessage.Player(chatMessage), false, ChatType.bind(ChatType.CHAT, player));
            }
        }
        return Command.SINGLE_SUCCESS;
    }
}

