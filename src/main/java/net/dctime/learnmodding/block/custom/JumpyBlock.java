package net.dctime.learnmodding.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class JumpyBlock extends Block
{
    public JumpyBlock(Properties properties)
    {
        super(properties);
    }

    public void stepOn(Level level, BlockPos blockPos, BlockState blockState, Entity entity)
    {
        if (!level.isClientSide())
        {
            if (entity instanceof LivingEntity)
            {
                ((LivingEntity)entity).addEffect(new MobEffectInstance(MobEffects.JUMP, 24, 0, false, false, false));
            }
        }
    }

    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult)
    {
        if (!level.isClientSide() && interactionHand == InteractionHand.MAIN_HAND)
        {
            player.displayClientMessage(Component.literal("Block Clicked"), false);
        }

        return InteractionResult.PASS;
    }


}
