package com.r4mble.dementia.util;

import com.r4mble.dementia.Config;
import com.r4mble.dementia.DementiaMod;
import com.r4mble.dementia.effects.ModEffects;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import com.r4mble.dementia.util.TpTask;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.phys.Vec3;

import java.util.Random;
import java.util.Timer;

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
                int event = random.nextInt(100);
                if (event <= Config.TP_CHANCE.get() + Config.HUNGER_CHANCE.get()) {
                    if (event <= Config.TP_CHANCE.get()) {
                        Timer timer = new Timer();
                        TpTask tpTask = new TpTask(player);
                        timer.schedule(tpTask, random.nextInt(60) * 1000);
                    } else {
                        FoodData foodData = player.getFoodData();
                        foodData.setFoodLevel(1);
                        foodData.setSaturation(0);
                        player.addEffect(new MobEffectInstance(MobEffects.HUNGER, 60, 2));
                        player.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 100, 4));
                        player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 4));
                    }

                } else {
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
}

class TpTask extends java.util.TimerTask {

    private final Player player;
    private final Vec3 pos;

    public TpTask(Player player) {
        this.player = player;
        this.pos = player.position();

    }

    @Override
    public void run() {
        if (!player.hasEffect(ModEffects.ANTI_DEMENTIA.getHolder().get())) {
            player.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 20));
            player.setPos(pos);
        }
    }
}