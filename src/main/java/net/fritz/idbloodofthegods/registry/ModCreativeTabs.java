package net.fritz.idbloodofthegods.registry;

import net.fritz.idbloodofthegods.BloodOfTheGods;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, BloodOfTheGods.MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> BLOOD_OF_THE_GODS_TAB =
            CREATIVE_MODE_TABS.register("blood_of_the_gods_tab", () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.idbloodofthegods"))
                    .icon(() -> new ItemStack(ModItems.BLOOD.get()))
                    .displayItems((parameters, output) -> {
                        output.accept(ModItems.BLOOD.get());
                        output.accept(ModBlocks.BLOOD_BENCH_ITEM.get());
                        output.accept(ModItems.DEIMOS_SPAWN_EGG.get());
                    })
                    .build());
}
