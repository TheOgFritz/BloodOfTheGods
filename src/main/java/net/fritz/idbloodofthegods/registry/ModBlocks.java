package net.fritz.idbloodofthegods.registry;

import net.fritz.idbloodofthegods.block.BloodBench;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks("idbloodofthegods");
    
    public static final DeferredRegister.Items ITEMS =
            DeferredRegister.createItems("idbloodofthegods");

    public static final DeferredBlock<BloodBench> BLOOD_BENCH =
            BLOCKS.register("blood_bench", BloodBench::new);

    public static final DeferredItem<BlockItem> BLOOD_BENCH_ITEM =
            ITEMS.registerSimpleBlockItem("blood_bench", BLOOD_BENCH);
}