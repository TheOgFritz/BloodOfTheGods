package net.fritz.idbloodofthegods.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class DeimosRenderer extends GeoEntityRenderer<DeimosBoss> {

    public DeimosRenderer(EntityRendererProvider.Context context) {
        super(context, new DeimosModel());
        this.shadowRadius = 0.7f;
    }
}
