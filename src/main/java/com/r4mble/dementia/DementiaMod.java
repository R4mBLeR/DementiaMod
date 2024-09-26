package com.r4mble.dementia;

import com.r4mble.dementia.effects.ModEffects;
import com.r4mble.dementia.items.ModItems;
import com.r4mble.dementia.util.DementiaThread;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;


@Mod(DementiaMod.MOD_ID)
public class DementiaMod  {
    public static final String MOD_ID = "dementia";
    public static DementiaThread dementia = null;
    public static MinecraftServer currentServer = null;

    public DementiaMod(FMLJavaModLoadingContext context) {
        context.registerConfig(ModConfig.Type.CLIENT, Config.SPEC, "dementia.toml");
        IEventBus eventBus = context.getModEventBus();


        ModEffects.EFFECTS.register(eventBus);
        ModItems.ITEMS.register(eventBus);
    }
}
