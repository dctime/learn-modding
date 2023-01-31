package net.dctime.learnmodding.item;

import net.dctime.learnmodding.LearnModdingMod;
import net.dctime.learnmodding.block.ModBlocks;
import net.dctime.learnmodding.fluid.ModFluids;
import net.dctime.learnmodding.item.custom.Ball8Item;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
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
    public static final RegistryObject<Item> BALL_8_ITEM = ITEMS.register("eight_ball", () -> new Ball8Item(new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> BLUEBERRY_SEED = ITEMS.register("blueberry_seeds",
            () -> new ItemNameBlockItem(ModBlocks.BLUEBERRY_CROP_BLOCK.get(), new Item.Properties()));

    public static final RegistryObject<Item> BLUEBERRY = ITEMS.register("blueberry",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationMod(2.0f).build())));

    public static final RegistryObject<Item> SOAP_WATER_BUCKET = ITEMS.register("soap_water_bucket",
            () -> new BucketItem(ModFluids.SOURCE_SOAP_WATER, new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));

    public static final RegistryObject<SwordItem> LEARN_SWORD = ITEMS.register("learn_sword",
            () -> new SwordItem(Tiers.DIAMOND, 10, 5f, new Item.Properties().stacksTo(1)));

    public static void registerItemsInModItemClass(IEventBus eventBus)
    {
        ITEMS.register(eventBus);
    }
}
