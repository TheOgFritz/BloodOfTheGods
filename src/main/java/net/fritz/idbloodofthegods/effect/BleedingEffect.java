package net.fritz.idbloodofthegods.effect;

import net.fritz.idbloodofthegods.entity.Boss;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class BleedingEffect extends MobEffect {

    private static final float DAMAGE_PERCENT_PER_SECOND = 0.15f;
    private static final float BOSS_DAMAGE_PERCENT_PER_SECOND = 0.05f;

    private static final ResourceKey<DamageType> BLEEDING_DAMAGE_TYPE = ResourceKey.create(
            Registries.DAMAGE_TYPE, ResourceLocation.fromNamespaceAndPath("idbloodofthegods", "bleeding"));

    public BleedingEffect() {
        super(MobEffectCategory.HARMFUL, 0x8B0000);
    }

    @Override
    public boolean applyEffectTick(LivingEntity entity, int amplifier) {
        if (entity.level() instanceof ServerLevel serverLevel) {
            Registry<DamageType> damageTypes = serverLevel.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE);
            Holder<DamageType> bleeding = damageTypes.getHolderOrThrow(BLEEDING_DAMAGE_TYPE);
            float percent = entity instanceof Boss ? BOSS_DAMAGE_PERCENT_PER_SECOND : DAMAGE_PERCENT_PER_SECOND;
            entity.hurt(new DamageSource(bleeding), entity.getMaxHealth() * percent);
        }
        return true;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return duration % 20 == 0;
    }
}
