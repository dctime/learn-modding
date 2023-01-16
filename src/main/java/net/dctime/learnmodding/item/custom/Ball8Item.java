package net.dctime.learnmodding.item.custom;

import net.dctime.learnmodding.LearnModdingMod;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentContents;
import net.minecraft.network.chat.Style;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.Minecart;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.lang.Math;
import java.util.List;

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

    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> componentList, TooltipFlag tooltipFlag)
    {
        if (Screen.hasShiftDown())
        {
            componentList.add(Component.translatable("tooltip." + LearnModdingMod.MODID + ".eight_ball_shift").withStyle(ChatFormatting.DARK_AQUA));
        }
        else
        {
            componentList.add(Component.translatable("tooltip." + LearnModdingMod.MODID + ".eight_ball_default").withStyle(ChatFormatting.GRAY));
        }

        super.appendHoverText(itemStack, level, componentList, tooltipFlag);
    }
}
