package net.dctime.learnmodding.event;

import net.dctime.learnmodding.client.ThirstHubOverlay;
import net.dctime.learnmodding.networking.ModMessages;
import net.dctime.learnmodding.networking.packet.DrinkWaterC2SPacket;
import net.dctime.learnmodding.networking.packet.ExampleC2SPacket;
import net.dctime.learnmodding.util.KeyBinding;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


public class ClientEvents
{
    // When forge is running (game is running, and every mod is running)
    @Mod.EventBusSubscriber(value = Dist.CLIENT, modid = "learnmodding", bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class ClientForgeEvents
    {
        @SubscribeEvent
        public static void onKeyInput(InputEvent.Key event)
        {
            if (KeyBinding.DRINKING_KEY.consumeClick())
            {
                Minecraft.getInstance().player.displayClientMessage(Component.literal("O Clicked"), false);
                ModMessages.sendToServer(new DrinkWaterC2SPacket());
            }
        }
    }

    // When it's the mod's turn to load its things
    @Mod.EventBusSubscriber(value = Dist.CLIENT, modid = "learnmodding", bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onKeyRegister(RegisterKeyMappingsEvent event)
        {
            event.register(KeyBinding.DRINKING_KEY);
        }

        @SubscribeEvent
        public static void onGuiOverlayRegister(RegisterGuiOverlaysEvent event)
        {
            event.registerAboveAll("gui.hub.learnmodding.thirsthub", ThirstHubOverlay.THIRST_HUB);
        }

    }
}