package com.r4mble.dementia.items;

import com.r4mble.dementia.DementiaMod;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, DementiaMod.MOD_ID);

    public static final RegistryObject<Item> ANTI_DEMENTIA_PILL = ITEMS.register("anti_dementia_pill",
            () -> new Item(new Item.Properties().food(ModFoods.ANTI_DEMENTIA_PILL)));
}
