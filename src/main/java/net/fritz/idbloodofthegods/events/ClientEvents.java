package net.fritz.idbloodofthegods.events;

import net.fritz.idbloodofthegods.block.BloodBenchRenderer;
import net.fritz.idbloodofthegods.registry.ModBlockEntities;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@EventBusSubscriber(modid = "idbloodofthegods", value = Dist.CLIENT)
public class ClientEvents {

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(
                ModBlockEntities.BLOOD_BENCH_BLOCK_ENTITY.get(),
                ctx -> new BloodBenchRenderer()
        );
    }
}