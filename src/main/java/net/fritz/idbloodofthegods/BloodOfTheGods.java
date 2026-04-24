package net.fritz.idbloodofthegods;

import net.fritz.idbloodofthegods.registry.ModBlockEntities;
import net.fritz.idbloodofthegods.registry.ModBlocks;
import org.slf4j.Logger;
import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.neoforge.common.NeoForge;

@Mod(BloodOfTheGods.MODID)
public class BloodOfTheGods {

    public static final String MODID = "idbloodofthegods";
    public static final Logger LOGGER = LogUtils.getLogger();

    public BloodOfTheGods(IEventBus modEventBus, ModContainer modContainer) {
        ModBlocks.BLOCKS.register(modEventBus);
        ModBlocks.ITEMS.register(modEventBus);
        ModBlockEntities.BLOCK_ENTITIES.register(modEventBus);

    

        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }
}