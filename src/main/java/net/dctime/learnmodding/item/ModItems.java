package net.dctime.learnmodding.item;

import net.dctime.learnmodding.LearnModdingMod;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems
{
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, LearnModdingMod.MODID);

    public static final RegistryObject<Item> ZIRCON = ITEMS.register("zircon", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_ZIRCON = ITEMS.register("raw_zircon", () -> new Item(new Item.Properties()));


    public static void registerItemsInModItemClass(IEventBus eventBus)
    {
        ITEMS.register(eventBus);
    }
}
