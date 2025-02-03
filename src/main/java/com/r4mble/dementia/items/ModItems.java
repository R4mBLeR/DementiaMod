package com.r4mble.dementia.items;

import com.r4mble.dementia.DementiaMod;
import com.r4mble.dementia.effects.ModEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, DementiaMod.MOD_ID);

    public static final RegistryObject<Item> BLUE_ANTI_DEMENTIA_PILL = ITEMS.register("blue_anti_dementia_pill",
            () -> new Item(new Item.Properties().food(
                    new FoodProperties.Builder()
                            .nutrition(1)
                            .saturationModifier(0)
                            .alwaysEdible()
                            .effect(new MobEffectInstance(ModEffects.ANTI_DEMENTIA.getHolder().get(), 6000), 1f)
                            .build())));

    public static final RegistryObject<Item> RED_ANTI_DEMENTIA_PILL = ITEMS.register("red_anti_dementia_pill",
            () -> new Item(new Item.Properties().food(
                    new FoodProperties.Builder()
                            .nutrition(1)
                            .saturationModifier(0)
                            .alwaysEdible()
                            .effect(new MobEffectInstance(ModEffects.ANTI_DEMENTIA.getHolder().get(), 12000), 1f)
                            .build())));
}
