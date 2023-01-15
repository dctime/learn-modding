package net.dctime.learnmodding;

import com.mojang.logging.LogUtils;
import net.dctime.learnmodding.block.ModBlocks;
import net.dctime.learnmodding.item.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(LearnModdingMod.MODID)
public class LearnModdingMod
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "learnmodding";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
    // Create a Deferred Register to hold Blocks which will all be registered under the "examplemod" namespace
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    // Create a Deferred Register to hold Items which will all be registered under the "examplemod" namespace
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

    // Creates a new Block with the id "examplemod:example_block", combining the namespace and path
    public static final RegistryObject<Block> EXAMPLE_BLOCK = BLOCKS.register("example_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    // Creates a new BlockItem with the id "examplemod:example_block", combining the namespace and path
    public static final RegistryObject<Item> EXAMPLE_BLOCK_ITEM = ITEMS.register("example_block", () -> new BlockItem(EXAMPLE_BLOCK.get(), new Item.Properties()));

    public LearnModdingMod()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register the Deferred Register to the mod event bus so blocks get registered
        BLOCKS.register(modEventBus);
        // Register the Deferred Register to the mod event bus so items get registered
        ITEMS.register(modEventBus);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);
        modEventBus.addListener(this::addCustomCreative);

        // Register mod items and blocks
        ModItems.registerItemsInModItemClass(modEventBus);
        ModBlocks.registerBLocksInModBlocksClass(modEventBus);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        // Some common setup code
        LOGGER.info("HELLO FROM COMMON SETUP");
        LOGGER.info("DIRT BLOCK >> {}", ForgeRegistries.BLOCKS.getKey(Blocks.DIRT));
    }

    private void addCreative(CreativeModeTabEvent.BuildContents event)
    {
        if (event.getTab() == CreativeModeTabs.BUILDING_BLOCKS)
            event.accept(EXAMPLE_BLOCK_ITEM);

        if (event.getTab() == CreativeModeTabs.INGREDIENTS)
        {
            event.accept(ModItems.ZIRCON);
            event.accept(ModItems.RAW_ZIRCON);
        }
    }

    public void addCustomCreative(CreativeModeTabEvent.Register event) {
        event.registerCreativeModeTab(new ResourceLocation(MODID, "learn"), builder ->
                // Set name of tab to display
                builder.title(Component.translatable("item_group." + MODID + ".learn"))
                        // Set icon of creative tab
                        .icon(() -> new ItemStack(ModItems.ZIRCON.get()))
                        // Add default items to tab
                        .displayItems((enabledFlags, populator, hasPermissions) -> {
                            populator.accept(ModItems.ZIRCON.get());
                            populator.accept(ModItems.RAW_ZIRCON.get());
                            populator.accept(ModBlocks.ZIRCON_BLOCK_ITEM.get());
                            populator.accept(ModBlocks.ZIRCON_ORE_ITEM.get());
                            populator.accept(ModBlocks.DEEPSLATE_ZIRCON_ORE_ITEM.get());
                            populator.accept(ModBlocks.NETHERRACK_ZIRCON_ORE_ITEM.get());
                            populator.accept(ModBlocks.ENDSTONE_ZIRCON_ORE_ITEM.get());
                            populator.accept(ModItems.BALL_8_ITEM.get());
                            populator.accept(ModBlocks.JUMPY_BLOCK.get());
                        })
        );
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            // Some client setup code
            LOGGER.info("HELLO FROM CLIENT SETUP");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
        }
    }
}
