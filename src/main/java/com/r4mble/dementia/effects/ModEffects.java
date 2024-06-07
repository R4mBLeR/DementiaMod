package com.r4mble.dementia.effects;

import com.r4mble.dementia.DementiaMod;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEffects {
    public static final DeferredRegister<MobEffect> EFFECTS
            = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, DementiaMod.MOD_ID);
    public static final RegistryObject<MobEffect> ANTI_DEMENTIA = EFFECTS.register("anti_dementia",
            () -> new AntiDementiaEffect(MobEffectCategory.BENEFICIAL, 3124687));
}
