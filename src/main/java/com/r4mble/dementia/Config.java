package com.r4mble.dementia;

import net.minecraftforge.common.ForgeConfigSpec;

public class Config {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;
    public static final ForgeConfigSpec.ConfigValue<Integer> MIN_COOLDOWN;
    public static final ForgeConfigSpec.ConfigValue<Integer> MAX_COOLDOWN;
    public static final ForgeConfigSpec.ConfigValue<Integer> MAX_COUNT;


    static {
        BUILDER.push("Dementia config");

        MIN_COOLDOWN = BUILDER.comment("Minimun cooldown before things go missing ").define("MIN_COOLDOWN", 60);
        MAX_COOLDOWN = BUILDER.comment("Maximum cooldown before things go missing").define("MAX_COOLDOWN", 180);
        MAX_COUNT = BUILDER.comment("Maximum items count to missing").define("MAX_COUNT", 3);
        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}