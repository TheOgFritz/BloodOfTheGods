package net.fritz.idbloodofthegods.registry;

import net.fritz.idbloodofthegods.advancement.FillBloodBenchTrigger;
import net.fritz.idbloodofthegods.advancement.ImbueSwordTrigger;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.registries.RegisterEvent;

public class ModCriteriaTriggers {

    public static final FillBloodBenchTrigger FILL_BLOOD_BENCH = new FillBloodBenchTrigger();
    public static final ImbueSwordTrigger IMBUE_SWORD = new ImbueSwordTrigger();

    private static final ResourceKey<? extends Registry<?>> TRIGGER_TYPE_KEY =
            ResourceKey.createRegistryKey(ResourceLocation.withDefaultNamespace("trigger_type"));

    public static void register(RegisterEvent event) {
        if (!event.getRegistryKey().equals(TRIGGER_TYPE_KEY)) return;
        CriteriaTriggers.register("idbloodofthegods:fill_blood_bench", FILL_BLOOD_BENCH);
        CriteriaTriggers.register("idbloodofthegods:imbue_sword", IMBUE_SWORD);
    }
}
