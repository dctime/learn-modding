package net.dctime.learnmodding.item.custom;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentContents;
import net.minecraft.network.chat.Style;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

import java.lang.Math;

public class Ball8Item extends Item
{

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand)
    {
        if (!level.isClientSide())
        {
            player.displayClientMessage(Component.literal("You rolled: " + Integer.toString((int) (Math.random() * 10))), true);
            player.getCooldowns().addCooldown(player.getMainHandItem().getItem(), 24);
        }

        return super.use(level, player, hand);
    }

    public Ball8Item(Properties p_41383_)
    {
        super(p_41383_);
    }
}
