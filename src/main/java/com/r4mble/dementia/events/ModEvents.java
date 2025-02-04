package com.r4mble.dementia.events;

import com.r4mble.dementia.Config;
import com.r4mble.dementia.DementiaMod;
import com.r4mble.dementia.effects.ModEffects;
import com.r4mble.dementia.items.ModItems;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Timer;

@Mod.EventBusSubscriber(modid = DementiaMod.MOD_ID)
public class ModEvents {

    @SubscribeEvent
    public static void addCustomTrades(VillagerTradesEvent event) {
        if (event.getType() == VillagerProfession.CLERIC) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();

            // Level 3
            trades.get(3).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 5),
                    new ItemStack(ModItems.BLUE_ANTI_DEMENTIA_PILL.get(), 1),
                    5, 8, 0.02f));

            trades.get(4).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 10),
                    new ItemStack(ModItems.RED_ANTI_DEMENTIA_PILL.get(), 1),
                    5, 8, 0.02f));
        }
    }


    @SubscribeEvent
    public static void onPlayerLoggedInEvent(PlayerEvent.PlayerLoggedInEvent event) {
        System.out.println("DEBUG1");
        if (event.getEntity() instanceof ServerPlayer player) {
            System.out.println("DEBUG2");
            Random random = new Random();
            int cooldownTime = random.nextInt(Config.MIN_COOLDOWN.get(), Config.MAX_COOLDOWN.get()) * 20;

            DementiaMod.dementiaPlayers.put(player,cooldownTime);
        }
    }

    @SubscribeEvent
    public static void onPlayerLoggedOutEvent(PlayerEvent.PlayerLoggedOutEvent event) {
        System.out.println("DEBUG3");
        if (event.getEntity() instanceof ServerPlayer player) {
            System.out.println("DEBUG4");
            DementiaMod.dementiaPlayers.remove(player);
        }
    }

    @SubscribeEvent
    public static void onTickEvent(TickEvent.ServerTickEvent event) {
        for (Map.Entry<ServerPlayer, Integer> entry : DementiaMod.dementiaPlayers.entrySet()) {
            //DEBUG
            System.out.println(entry.getValue()/20);
            entry.setValue(entry.getValue()-1);
            if (entry.getValue() == 0) {
                Random random = new Random();
                int cooldownTime = random.nextInt(Config.MIN_COOLDOWN.get(), Config.MAX_COOLDOWN.get()) * 20;
                entry.setValue(cooldownTime);
                ServerPlayer player = entry.getKey();
                if (player.hasEffect(ModEffects.ANTI_DEMENTIA.getHolder().get())) {
                    continue;
                }
                int dementiaEvent = random.nextInt(100);
                if (dementiaEvent <= Config.TP_CHANCE.get() + Config.HUNGER_CHANCE.get()) {
                    if (dementiaEvent <= Config.TP_CHANCE.get()) {
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
            player.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 20, 0));
            player.setPos(pos);
        }
    }
}