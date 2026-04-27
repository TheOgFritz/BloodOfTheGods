package net.fritz.idbloodofthegods.block;

import com.mojang.serialization.MapCodec;
import net.fritz.idbloodofthegods.registry.ModBlockEntities;
import net.fritz.idbloodofthegods.registry.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import static net.minecraft.core.component.DataComponents.ATTRIBUTE_MODIFIERS;
import static net.minecraft.core.component.DataComponents.CUSTOM_DATA;

public class BloodBench extends BaseEntityBlock {

    public static final IntegerProperty BLOOD = IntegerProperty.create("blood", 0, 2);
    public static final MapCodec<BloodBench> CODEC = simpleCodec(properties -> new BloodBench());

    private static final VoxelShape SHAPE = Shapes.or(
            Block.box(0, 0, 0, 16, 7, 16),   // base + legs
            Block.box(0, 7, 0, 1, 11, 16),   // left wall
            Block.box(15, 7, 0, 16, 11, 16), // right wall
            Block.box(1, 7, 15, 15, 11, 16), // back wall
            Block.box(1, 7, 0, 15, 11, 1)    // front wall
    );

    private static final ResourceLocation BLOOD_DAMAGE_ID =
            ResourceLocation.fromNamespaceAndPath("idbloodofthegods", "blood_damage_boost");
    private static final double DAMAGE_BOOST = 2.0;

    public BloodBench() {
        super(BlockBehaviour.Properties.of()
                .noOcclusion()
                .strength(2.0f));
        this.registerDefaultState(this.stateDefinition.any().setValue(BLOOD, 0));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<net.minecraft.world.level.block.Block, BlockState> builder) {
        builder.add(BLOOD);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new BloodBenchBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        if (level.isClientSide()) return null;
        return createTickerHelper(type, ModBlockEntities.BLOOD_BENCH_BLOCK_ENTITY.get(), BloodBenchBlockEntity::serverTick);
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        // Blood deposit
        if (stack.is(ModItems.BLOOD.get())) {
            if (state.getValue(BLOOD) >= 1) {
                return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
            }
            if (!level.isClientSide()) {
                BlockEntity be = level.getBlockEntity(pos);
                if (be instanceof BloodBenchBlockEntity bloodBench) {
                    if (bloodBench.isFilling()) {
                        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
                    }
                    stack.shrink(1);
                    bloodBench.startFill();
                }
            }
            return ItemInteractionResult.SUCCESS;
        }

        // Sword infusion — requires at least half a bench of blood
        if (stack.is(ItemTags.SWORDS) && state.getValue(BLOOD) >= 1) {
            CompoundTag tag = stack.getOrDefault(CUSTOM_DATA, CustomData.EMPTY).copyTag();
            if (tag.getBoolean("blood_infused")) {
                return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
            }
            if (!level.isClientSide()) {
                tag.putBoolean("blood_infused", true);
                stack.set(CUSTOM_DATA, CustomData.of(tag));

                ItemAttributeModifiers current = stack.getOrDefault(ATTRIBUTE_MODIFIERS, ItemAttributeModifiers.EMPTY);
                ItemAttributeModifiers.Builder builder = ItemAttributeModifiers.builder();
                for (ItemAttributeModifiers.Entry entry : current.modifiers()) {
                    builder.add(entry.attribute(), entry.modifier(), entry.slot());
                }
                builder.add(
                        Attributes.ATTACK_DAMAGE,
                        new AttributeModifier(BLOOD_DAMAGE_ID, DAMAGE_BOOST, AttributeModifier.Operation.ADD_VALUE),
                        EquipmentSlotGroup.MAINHAND
                );
                stack.set(ATTRIBUTE_MODIFIERS, builder.build());

                level.setBlock(pos, state.setValue(BLOOD, state.getValue(BLOOD) - 1), 3);
            }
            return ItemInteractionResult.SUCCESS;
        }

        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        return InteractionResult.PASS;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }
}
