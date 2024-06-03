package com.r4mble.dementia.commands;

import com.r4mble.dementia.Config;
import com.r4mble.dementia.DementiaMod;
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
                player.setExperienceLevels(cooldownTime/1000); //DEBUG
                int itemsRem = 3;
                while (!player.getInventory().isEmpty() || itemsRem > 0) {
                    player.getInventory().items.get(random.nextInt(player.getInventory().items.size())).setCount(0);
                    itemsRem--;
                }

            }
        }
    }
}