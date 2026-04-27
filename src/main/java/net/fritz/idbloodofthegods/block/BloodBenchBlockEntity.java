package net.fritz.idbloodofthegods.block;

import net.fritz.idbloodofthegods.registry.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.util.GeckoLibUtil;

public class BloodBenchBlockEntity extends BlockEntity implements GeoBlockEntity {

    private static final int FILL_DELAY_TICKS = 41; // 2.05 seconds

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private int ticksUntilFill = 0;

    public BloodBenchBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.BLOOD_BENCH_BLOCK_ENTITY.get(), pos, state);
    }

    public int getBloodLevel() {
        if (this.level == null) return 0;
        BlockState state = this.level.getBlockState(this.worldPosition);
        if (!state.hasProperty(BloodBench.BLOOD)) return 0;
        return state.getValue(BloodBench.BLOOD);
    }

    public boolean isFilling() {
        return ticksUntilFill > 0;
    }

    public void startFill() {
        this.ticksUntilFill = FILL_DELAY_TICKS;
        this.triggerAnim("controller", "blood_fill");
    }

    public static void serverTick(Level level, BlockPos pos, BlockState state, BloodBenchBlockEntity be) {
        if (be.ticksUntilFill <= 0) return;
        be.ticksUntilFill--;
        if (be.ticksUntilFill == 0) {
            level.setBlock(pos, state.setValue(BloodBench.BLOOD, 2), 3);
        }
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
