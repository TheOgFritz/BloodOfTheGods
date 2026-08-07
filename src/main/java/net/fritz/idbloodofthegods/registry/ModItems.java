package net.fritz.idbloodofthegods.registry;

import net.minecraft.world.item.Item;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS =
            DeferredRegister.createItems("idbloodofthegods");

    public static final DeferredItem<Item> BLOOD =
            ITEMS.registerSimpleItem("blood", new Item.Properties());

    public static final DeferredItem<DeferredSpawnEggItem> DEIMOS_SPAWN_EGG =
            ITEMS.register("deimos_spawn_egg", () ->
                    new DeferredSpawnEggItem(ModEntityTypes.DEIMOS::get, 0x1a0000, 0x8b0000, new Item.Properties()));
}