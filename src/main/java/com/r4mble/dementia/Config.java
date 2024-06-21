package com.r4mble.dementia;

import net.minecraftforge.common.ForgeConfigSpec;

public class Config {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;
    public static final ForgeConfigSpec.ConfigValue<Integer> MIN_COOLDOWN;
    public static final ForgeConfigSpec.ConfigValue<Integer> MAX_COOLDOWN;
    public static final ForgeConfigSpec.ConfigValue<Integer> MAX_COUNT;
    public static final ForgeConfigSpec.ConfigValue<Integer> TP_CHANCE;


    static {
        BUILDER.push("Dementia config");

        MIN_COOLDOWN = BUILDER.comment("Minimun cooldown before things go missing ").define("MIN_COOLDOWN", 0);
        MAX_COOLDOWN = BUILDER.comment("Maximum cooldown before things go missing").define("MAX_COOLDOWN", 30);
        MAX_COUNT = BUILDER.comment("Maximum items count to missing").define("MAX_COUNT", 3);
        TP_CHANCE = BUILDER.comment("Chance to teleport player in random point within one hundred blocks").define("TP_CHANCE", 90);
        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}