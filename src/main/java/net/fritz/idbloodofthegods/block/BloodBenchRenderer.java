package net.fritz.idbloodofthegods.block;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.model.DefaultedBlockGeoModel;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class BloodBenchRenderer extends GeoBlockRenderer<BloodBenchBlockEntity> {

    public BloodBenchRenderer() {
        super(new DefaultedBlockGeoModel<>(
                ResourceLocation.fromNamespaceAndPath("idbloodofthegods", "blood_bench")
        ));
    }

    @Override
    public RenderType getRenderType(BloodBenchBlockEntity animatable, ResourceLocation texture,
                                    MultiBufferSource bufferSource, float partialTick) {
        return RenderType.entityTranslucentCull(texture);
    }
}