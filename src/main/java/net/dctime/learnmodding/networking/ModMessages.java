package net.dctime.learnmodding.networking;

import net.dctime.learnmodding.LearnModdingMod;
import net.dctime.learnmodding.networking.packet.DrinkWaterC2SPacket;
import net.dctime.learnmodding.networking.packet.ExampleC2SPacket;
import net.dctime.learnmodding.networking.packet.ThirstDataSyncS2CPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

import java.lang.reflect.Constructor;

public class ModMessages
{
    private static SimpleChannel channel;


    public static void register()
    {
        channel = NetworkRegistry.ChannelBuilder
                .named(new ResourceLocation(LearnModdingMod.MODID, "messages"))
                .networkProtocolVersion(() -> "1.0")
                .clientAcceptedVersions(string -> true)
                .serverAcceptedVersions(string -> true)
                .simpleChannel();

        channel.messageBuilder(ExampleC2SPacket.class, 0, NetworkDirection.PLAY_TO_SERVER)
                .encoder((classType, encoder) -> classType.toByte(encoder))
                .decoder(ExampleC2SPacket::new)
                .consumerMainThread((classType, consumer) -> classType.handle(consumer))
                .add();

        channel.messageBuilder(DrinkWaterC2SPacket.class, 1, NetworkDirection.PLAY_TO_SERVER)
                .encoder(DrinkWaterC2SPacket::toBytes)
                .decoder(DrinkWaterC2SPacket::new)
                .consumerMainThread(DrinkWaterC2SPacket::handle)
                .add();

        channel.messageBuilder(ThirstDataSyncS2CPacket.class, 2, NetworkDirection.PLAY_TO_CLIENT)
                .encoder(ThirstDataSyncS2CPacket::toBytes)
                .decoder(ThirstDataSyncS2CPacket::new)
                .consumerMainThread(ThirstDataSyncS2CPacket::handle)
                .add();
    }

    public static <MSG> void sendToServer(MSG message)
    {
        channel.sendToServer(message);
    }

    public static <MSG> void sendToPlayer(MSG message, ServerPlayer serverPlayer)
    {
        channel.send(PacketDistributor.PLAYER.with(() -> serverPlayer), message);
    }
}
