package net.dctime.learnmodding.screen;

import net.dctime.learnmodding.LearnModdingMod;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModMenuTypes
{
    public static final DeferredRegister<MenuType<?>> MENUS_TYPES =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, LearnModdingMod.MODID);

    public static final RegistryObject<MenuType<GemInfuserMenu>> GEM_INFUSER_MENU =
            MENUS_TYPES.register("gem_infusing_station_menu",
                    () -> IForgeMenuType.create(GemInfuserMenu::new));

    public static void register(IEventBus eventBus)
    {
        MENUS_TYPES.register(eventBus);
    }
}
