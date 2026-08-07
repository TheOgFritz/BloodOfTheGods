package net.fritz.idbloodofthegods.block;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class BloodBenchGeoModel extends GeoModel<BloodBenchBlockEntity> {

    private static final ResourceLocation MODEL_EMPTY = ResourceLocation.fromNamespaceAndPath("idbloodofthegods", "geo/block/blood_bench_empty.geo.json");
    private static final ResourceLocation MODEL_HALF  = ResourceLocation.fromNamespaceAndPath("idbloodofthegods", "geo/block/blood_bench_half.geo.json");
    private static final ResourceLocation MODEL_FULL  = ResourceLocation.fromNamespaceAndPath("idbloodofthegods", "geo/block/blood_bench_full.geo.json");
    private static final ResourceLocation ANIMATION   = ResourceLocation.fromNamespaceAndPath("idbloodofthegods", "animations/block/blood_bench.animation.json");
    private static final ResourceLocation TEXTURE     = ResourceLocation.fromNamespaceAndPath("idbloodofthegods", "textures/block/blood_bench.png");

    @Override
    public ResourceLocation getModelResource(BloodBenchBlockEntity animatable) {
        return switch (animatable.getBloodLevel()) {
            case 1 -> MODEL_HALF;
            case 2 -> MODEL_FULL;
            default -> MODEL_EMPTY;
        };
    }

    @Override
    public ResourceLocation getTextureResource(BloodBenchBlockEntity animatable) {
        return TEXTURE;
    }

    @Override
    public ResourceLocation getAnimationResource(BloodBenchBlockEntity animatable) {
        return ANIMATION;
    }
}
