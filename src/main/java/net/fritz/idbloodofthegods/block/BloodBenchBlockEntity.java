package net.fritz.idbloodofthegods.block;

import net.fritz.idbloodofthegods.registry.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.util.GeckoLibUtil;

public class BloodBenchBlockEntity extends BlockEntity implements GeoBlockEntity {

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public BloodBenchBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.BLOOD_BENCH_BLOCK_ENTITY.get(), pos, state);
    }
    public int getBloodLevel() {
        if (this.level == null) return 0;
        BlockState state = this.level.getBlockState(this.worldPosition);
        if (!state.hasProperty(BloodBench.BLOOD)) return 0;
        return state.getValue(BloodBench.BLOOD);
    }
    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        AnimationController<BloodBenchBlockEntity> controller =
                new AnimationController<>(this, "controller", 0, state -> PlayState.STOP);
        controller.triggerableAnim("blood_fill", RawAnimation.begin().thenPlay("animation.blood_bench.idle"));
        controllers.add(controller);
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}