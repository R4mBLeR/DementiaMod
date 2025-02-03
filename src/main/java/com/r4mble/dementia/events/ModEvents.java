package com.r4mble.dementia.events;

import com.r4mble.dementia.DementiaMod;
import com.r4mble.dementia.items.ModItems;
import com.r4mble.dementia.util.DementiaThread;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

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

    @SubscribeEvent
    public static void addCustomTrades(VillagerTradesEvent event) {
        if (event.getType() == VillagerProfession.CLERIC) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();

            // Level 3
            trades.get(3).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 5),
                    new ItemStack(ModItems.BLUE_ANTI_DEMENTIA_PILL.get(), 1),
                    5, 8, 0.02f));

            trades.get(4).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 10),
                    new ItemStack(ModItems.RED_ANTI_DEMENTIA_PILL.get(), 1),
                    5, 8, 0.02f));
        }


    }
}
