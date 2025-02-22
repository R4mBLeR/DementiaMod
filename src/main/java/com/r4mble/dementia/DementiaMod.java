package com.r4mble.dementia;

import com.r4mble.dementia.effects.ModEffects;
import com.r4mble.dementia.items.ModItems;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.util.HashMap;


@Mod(DementiaMod.MOD_ID)
public class DementiaMod {
    public static final String MOD_ID = "dementia";
    public static HashMap<ServerPlayer, Integer> dementiaPlayers;

    public DementiaMod() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.SPEC, "dementia.toml");
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        dementiaPlayers  = new HashMap<>();
        eventBus.addListener(this::addCreative);


        ModEffects.EFFECTS.register(eventBus);
        ModItems.ITEMS.register(eventBus);
    }

    public void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.FOOD_AND_DRINKS) {
            event.accept(ModItems.BLUE_ANTI_DEMENTIA_PILL);
            event.accept(ModItems.RED_ANTI_DEMENTIA_PILL);
        }
    }
}