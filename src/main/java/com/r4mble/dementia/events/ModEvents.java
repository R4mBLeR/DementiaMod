package com.r4mble.dementia.events;

import com.r4mble.dementia.DementiaMod;
import com.r4mble.dementia.commands.DementiaStartCommand;
import com.r4mble.dementia.commands.DementiaStopCommand;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.command.ConfigCommand;

@Mod.EventBusSubscriber(modid = DementiaMod.MOD_ID)
public class ModEvents {

    @SubscribeEvent
    public static void onCommandsRegister(RegisterCommandsEvent event) {
        DementiaStartCommand.register(event.getDispatcher());
        DementiaStopCommand.register(event.getDispatcher());

        ConfigCommand.register(event.getDispatcher());
    }
}
