package com.r4mble.dementia.events;

import com.r4mble.dementia.DementiaMod;
import com.r4mble.dementia.util.DementiaThread;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = DementiaMod.MOD_ID)
public class ModEvents {

    @SubscribeEvent
    public static void onEntityJoinLevelEvent(EntityJoinLevelEvent event) {
        if (DementiaMod.dementia == null) {
            DementiaMod.currentServer = event.getLevel().getServer();
            DementiaMod.dementia = new DementiaThread();
            DementiaMod.dementia.start();
        }
    }
}
