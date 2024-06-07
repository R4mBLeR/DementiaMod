package com.r4mble.dementia.util;

import com.r4mble.dementia.Config;
import com.r4mble.dementia.DementiaMod;
import com.r4mble.dementia.effects.ModEffects;
import net.minecraft.server.level.ServerPlayer;

import java.util.Random;

public class DementiaThread extends Thread {
    @Override
    public void run() {
        while (true) {
            Random random = new Random();
            int cooldownTime = (random.nextInt(Config.MAX_COOLDOWN.get() - Config.MIN_COOLDOWN.get()) + Config.MIN_COOLDOWN.get()) * 1000;
            try {
                Thread.sleep(cooldownTime);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            for (ServerPlayer player : DementiaMod.currentServer.getPlayerList().getPlayers()) {
                //player.setExperienceLevels(cooldownTime / 1000); //DEBUG
                if (player.hasEffect(ModEffects.ANTI_DEMENTIA.getHolder().get())) {
                    continue;
                }
                for (int remaining = Config.MAX_COUNT.get(); remaining > 0; remaining--) {
                    if (player.getInventory().isEmpty()) {
                        break;
                    }
                    int index = random.nextInt(36);
                    while (player.getInventory().getItem(index).isEmpty()) {
                        index = random.nextInt(36);
                    }
                    int count = random.nextInt(player.getInventory().items.get(index).getCount());
                    player.getInventory().items.get(index).setCount(count);
                }

            }
        }
    }
}