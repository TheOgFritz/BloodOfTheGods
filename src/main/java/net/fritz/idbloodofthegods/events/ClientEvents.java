package net.fritz.idbloodofthegods.events;

import net.fritz.idbloodofthegods.block.BloodBenchRenderer;
import net.fritz.idbloodofthegods.registry.ModBlockEntities;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.Level;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.event.tick.LevelTickEvent;
import org.joml.Vector3f;

import static net.minecraft.core.component.DataComponents.CUSTOM_DATA;

@EventBusSubscriber(modid = "idbloodofthegods", value = Dist.CLIENT)
public class ClientEvents {

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(
                ModBlockEntities.BLOOD_BENCH_BLOCK_ENTITY.get(),
                ctx -> new BloodBenchRenderer()
        );
    }

    @SubscribeEvent
    public static void onLevelTick(LevelTickEvent.Post event) {
        Level level = (Level) event.getLevel();
        if (!level.isClientSide()) return;

        for (Player player : level.players()) {
            ItemStack stack = player.getMainHandItem();
            if (!stack.is(ItemTags.SWORDS)) continue;

            CompoundTag tag = stack.getOrDefault(CUSTOM_DATA, CustomData.EMPTY).copyTag();
            if (!tag.getBoolean("blood_infused")) continue;

            // Stagger particles across players so they don't all spawn the same tick
            if ((level.getGameTime() + player.getId()) % 10 != 0) continue;

            RandomSource rng = level.getRandom();
            double yawRad = Math.toRadians(player.getYRot());

            // Offset to the player's right-hand side
            double x = player.getX() + Math.cos(yawRad) * 0.4 + (rng.nextDouble() - 0.5) * 0.2;
            double y = player.getY() + 0.9 + (rng.nextDouble() - 0.5) * 0.2;
            double z = player.getZ() + Math.sin(yawRad) * 0.4 + (rng.nextDouble() - 0.5) * 0.2;

            level.addParticle(
                    new DustParticleOptions(new Vector3f(0.55f, 0.0f, 0.0f), 1.2f),
                    x, y, z,
                    0.0, -0.03, 0.0
            );
        }
    }
}
