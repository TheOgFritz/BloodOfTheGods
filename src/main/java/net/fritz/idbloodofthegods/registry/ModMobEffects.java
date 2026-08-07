package net.fritz.idbloodofthegods.registry;

import net.fritz.idbloodofthegods.effect.BleedingEffect;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModMobEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(Registries.MOB_EFFECT, "idbloodofthegods");

    public static final DeferredHolder<MobEffect, BleedingEffect> BLEEDING =
            MOB_EFFECTS.register("bleeding", BleedingEffect::new);
}
