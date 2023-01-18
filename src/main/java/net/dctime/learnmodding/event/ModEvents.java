package net.dctime.learnmodding.event;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.dctime.learnmodding.LearnModdingMod;
import net.dctime.learnmodding.item.ModItems;
import net.dctime.learnmodding.villager.ModVillagers;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.common.BasicItemListing;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
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

}
