package com.r4mble.dementia;

import com.r4mble.dementia.commands.DementiaThread;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;


@Mod(DementiaMod.MOD_ID)
public class DementiaMod {
    public static final String MOD_ID = "dementia";
    public static DementiaThread dementia = null;
    public static MinecraftServer currentServer = null;

    public DementiaMod() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.SPEC, "dementia-config.toml");
    }
}
