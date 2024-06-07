package com.r4mble.dementia.items;


import com.r4mble.dementia.effects.ModEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;

public class ModFoods {
    public static final FoodProperties ANTI_DEMENTIA_PILL
            = new FoodProperties.Builder()
            .nutrition(1)
            .saturationMod(0f)
            .effect(() -> new MobEffectInstance(ModEffects.ANTI_DEMENTIA.get(), 6000), 1f).build();
}
