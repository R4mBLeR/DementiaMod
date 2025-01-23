package com.r4mble.dementia.items;

import com.r4mble.dementia.DementiaMod;
import com.r4mble.dementia.effects.ModEffects;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, DementiaMod.MOD_ID);

    public static final RegistryObject<Item> ANTI_DEMENTIA_PILL = ITEMS.register("anti_dementia_pill",
            () -> new Item(new Item.Properties().component(
                    DataComponents.CONSUMABLE,
                    Consumable.builder()
                            .hasConsumeParticles(false)
                            .onConsume(
                                    new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(ModEffects.ANTI_DEMENTIA.getHolder().get(), 6000, 0), 1F)
                            )
                            .build()
            ).setId(ResourceKey.create(Registries.ITEM, ResourceLocation.parse( DementiaMod.MOD_ID+":anti_dementia_pill")))));
}
