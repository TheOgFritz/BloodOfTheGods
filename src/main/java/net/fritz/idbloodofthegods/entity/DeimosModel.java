package net.fritz.idbloodofthegods.entity;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class DeimosModel extends GeoModel<DeimosBoss> {

    private static final ResourceLocation MODEL =
            ResourceLocation.fromNamespaceAndPath("idbloodofthegods", "geo/entity/deimos.geo.json");
    private static final ResourceLocation TEXTURE =
            ResourceLocation.fromNamespaceAndPath("idbloodofthegods", "textures/entity/deimos.png");
    private static final ResourceLocation ANIMATION =
            ResourceLocation.fromNamespaceAndPath("idbloodofthegods", "animations/entity/deimos.animation.json");

    @Override
    public ResourceLocation getModelResource(DeimosBoss animatable) {
        return MODEL;
    }

    @Override
    public ResourceLocation getTextureResource(DeimosBoss animatable) {
        return TEXTURE;
    }

    @Override
    public ResourceLocation getAnimationResource(DeimosBoss animatable) {
        return ANIMATION;
    }
}
