package net.fritz.idbloodofthegods.registry;

import net.fritz.idbloodofthegods.entity.DeimosBoss;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModEntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(Registries.ENTITY_TYPE, "idbloodofthegods");

    public static final DeferredHolder<EntityType<?>, EntityType<DeimosBoss>> DEIMOS =
            ENTITY_TYPES.register("deimos", () -> EntityType.Builder.of(DeimosBoss::new, MobCategory.MONSTER)
                    .sized(0.9f, 2.6f)
                    .clientTrackingRange(16)
                    .build("deimos"));
}
