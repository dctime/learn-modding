package net.dctime.learnmodding.event;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.dctime.learnmodding.LearnModdingMod;
import net.dctime.learnmodding.item.ModItems;
import net.dctime.learnmodding.networking.ModMessages;
import net.dctime.learnmodding.networking.packet.ThirstDataSyncS2CPacket;
import net.dctime.learnmodding.thirst.PlayerThirst;
import net.dctime.learnmodding.thirst.PlayerThirstProvider;
import net.dctime.learnmodding.villager.ModVillagers;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.common.BasicItemListing;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = LearnModdingMod.MODID)
public class ModEvents
{
    @SubscribeEvent
    public static void addCustomTrades(VillagerTradesEvent event)
    {
        if (event.getType() == ModVillagers.JUMPY_MASTER.get())
        {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            ItemStack stack = new ItemStack(ModItems.BALL_8_ITEM.get(), 1);

            trades.get(1).add(new BasicItemListing(5, stack, 10, 1));
        }
    }

    @SubscribeEvent
    public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
        if(event.getObject() instanceof Player) {
            if(!event.getObject().getCapability(PlayerThirstProvider.PLAYER_THIRST).isPresent()) {
                event.addCapability(new ResourceLocation(LearnModdingMod.MODID, "properties"), new PlayerThirstProvider());
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event) {
        if(event.isWasDeath()) {
            event.getOriginal().getCapability(PlayerThirstProvider.PLAYER_THIRST).ifPresent(oldStore -> {
                event.getOriginal().getCapability(PlayerThirstProvider.PLAYER_THIRST).ifPresent(newStore -> {
                    newStore.copyFrom(oldStore);
                });
            });
        }
    }
// no need becuase
//    @SubscribeEvent
//    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
//        event.register(PlayerThirst.class);
//    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event)
    {
        if (event.side.isServer())
        {
            //SERVER SIDE
            event.player.getCapability(PlayerThirstProvider.PLAYER_THIRST).ifPresent(playerThirst ->
            {
                if (Math.random() < 0.001f && playerThirst.getThirst() < 10)
                {
                    playerThirst.setThirst(playerThirst.getThirst()+1);
                    event.player.displayClientMessage(Component.literal("Thirst: " + playerThirst.getThirst()), false);
                    ModMessages.sendToPlayer(new ThirstDataSyncS2CPacket(playerThirst.getThirst()), (ServerPlayer) event.player);
                }
            });
        }
    }
    @SubscribeEvent
    public static void onPlayerJoinWorld(EntityJoinLevelEvent event)
    {
        if (!event.getLevel().isClientSide() && event.getEntity() instanceof ServerPlayer)
        {
            //SERVER SIDE
            event.getEntity().getCapability(PlayerThirstProvider.PLAYER_THIRST).ifPresent(playerThirst ->
            {
                ModMessages.sendToPlayer(new ThirstDataSyncS2CPacket(playerThirst.getThirst()), (ServerPlayer)event.getEntity());
            });

        }
    }


}
