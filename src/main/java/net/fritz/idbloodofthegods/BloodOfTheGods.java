package net.fritz.idbloodofthegods;

import net.fritz.idbloodofthegods.entity.DeimosBoss;
import net.fritz.idbloodofthegods.registry.ModBlockEntities;
import net.fritz.idbloodofthegods.registry.ModBlocks;
import net.fritz.idbloodofthegods.registry.ModCreativeTabs;
import net.fritz.idbloodofthegods.registry.ModCriteriaTriggers;
import net.fritz.idbloodofthegods.registry.ModEntityTypes;
import net.fritz.idbloodofthegods.registry.ModItems;
import net.fritz.idbloodofthegods.registry.ModMobEffects;
import org.slf4j.Logger;
import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;

@Mod(BloodOfTheGods.MODID)
public class BloodOfTheGods {

    public static final String MODID = "idbloodofthegods";
    public static final Logger LOGGER = LogUtils.getLogger();

    public BloodOfTheGods(IEventBus modEventBus, ModContainer modContainer) {
        ModBlocks.BLOCKS.register(modEventBus);
        ModBlocks.ITEMS.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);
        ModCreativeTabs.CREATIVE_MODE_TABS.register(modEventBus);
        ModBlockEntities.BLOCK_ENTITIES.register(modEventBus);
        ModMobEffects.MOB_EFFECTS.register(modEventBus);
        ModEntityTypes.ENTITY_TYPES.register(modEventBus);
        modEventBus.addListener(ModCriteriaTriggers::register);
        modEventBus.addListener((EntityAttributeCreationEvent event) ->
                event.put(ModEntityTypes.DEIMOS.get(), DeimosBoss.createAttributes().build()));

        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }
}