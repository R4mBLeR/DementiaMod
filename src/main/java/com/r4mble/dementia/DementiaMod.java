package com.r4mble.dementia;

import com.r4mble.dementia.effects.ModEffects;
import com.r4mble.dementia.items.ModItems;
import com.r4mble.dementia.util.DementiaThread;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.security.SecureRandom;
import java.util.HashMap;


@Mod(DementiaMod.MOD_ID)
public class DementiaMod {
    public static final String MOD_ID = "dementia";
    public static HashMap<ServerPlayer, Integer> dementiaPlayers;

    public DementiaMod() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.SPEC, "dementia.toml");
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModEffects.EFFECTS.register(eventBus);
        ModItems.ITEMS.register(eventBus);
    }
}
