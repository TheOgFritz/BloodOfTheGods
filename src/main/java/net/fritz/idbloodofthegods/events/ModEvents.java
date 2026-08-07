package net.fritz.idbloodofthegods.events;

import net.fritz.idbloodofthegods.registry.ModMobEffects;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;

import static net.minecraft.core.component.DataComponents.CUSTOM_DATA;

@EventBusSubscriber(modid = "idbloodofthegods")
public class ModEvents {

    private static final int BLEED_DURATION_TICKS = 60; // 3 seconds — long enough to land a second 1s tick before it needs refreshing

    @SubscribeEvent
    public static void onLivingDamage(LivingDamageEvent.Post event) {
        LivingEntity target = event.getEntity();
        if (target.level().isClientSide() || !target.isAlive()) return;

        DamageSource source = event.getSource();
        ItemStack weapon = source.getWeaponItem();
        if (weapon == null || weapon.isEmpty()) return;

        CompoundTag tag = weapon.getOrDefault(CUSTOM_DATA, CustomData.EMPTY).copyTag();
        if (!tag.getBoolean("blood_infused")) return;

        target.addEffect(new MobEffectInstance(ModMobEffects.BLEEDING, BLEED_DURATION_TICKS, 0));
    }
}
