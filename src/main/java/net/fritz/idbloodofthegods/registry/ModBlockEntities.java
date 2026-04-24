package net.fritz.idbloodofthegods.registry;

import net.fritz.idbloodofthegods.block.BloodBenchBlockEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

@SuppressWarnings("DataFlowIssue")
public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, "idbloodofthegods");

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<BloodBenchBlockEntity>> BLOOD_BENCH_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("blood_bench", () ->
                    BlockEntityType.Builder.of(BloodBenchBlockEntity::new, ModBlocks.BLOOD_BENCH.get()).build(null));
}