package net.dctime.learnmodding.networking.packet;

import net.dctime.learnmodding.thirst.PlayerThirstProvider;
import net.minecraft.client.sounds.WeighedSoundEvents;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraftforge.network.NetworkEvent;

import java.awt.*;
import java.util.function.Supplier;

public class DrinkWaterC2SPacket
{

    public DrinkWaterC2SPacket()
    {

    }

    public DrinkWaterC2SPacket(FriendlyByteBuf buf)
    {

    }

    public void toBytes(FriendlyByteBuf buf)
    {

    }

    public void handle(Supplier<NetworkEvent.Context> ctx)
    {
        ctx.get().enqueueWork(() ->
        {
            if (ctx.get().getSender().isInWater())
            {
                ctx.get().getSender().getCapability(PlayerThirstProvider.PLAYER_THIRST).ifPresent(playerThirst ->
                {
                    if (playerThirst.getThirst() > 0)
                    {
                        ctx.get().getSender().sendSystemMessage(Component.translatable("message.learnmodding.drinking_water_successful"), true);
                        ctx.get().getSender().getLevel().playSound(null, ctx.get().getSender().blockPosition(),
                                SoundEvents.GENERIC_DRINK, SoundSource.PLAYERS);
                        playerThirst.setThirst(playerThirst.getThirst()-1);
                    }
                    else
                    {
                        ctx.get().getSender().sendSystemMessage(Component.translatable("message.learnmodding.drinking_water_is_full"), true);
                        ctx.get().getSender().getLevel().playSound(null, ctx.get().getSender().blockPosition(), SoundEvents.PLAYER_BURP, SoundSource.PLAYERS);
                    }

                    ctx.get().getSender().sendSystemMessage(Component.literal("Thirst: " + playerThirst.getThirst()), false);
                });
            }
            else
            {
                ctx.get().getSender().sendSystemMessage(Component.translatable("message.learnmodding.drinking_water_not_in_water"), true);
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
