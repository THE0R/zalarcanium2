package net.rbujak.tutorialmod.item;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.rbujak.tutorialmod.TutorialMod;
import net.rbujak.tutorialmod.item.custom.EyeOfHelenaItem;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, TutorialMod.MOD_ID);

    public static final RegistryObject<Item> EYE_OF_HELENA = ITEMS.register("eyeofhelena",
            () -> new EyeOfHelenaItem(new Item.Properties()));
    public static final RegistryObject<Item> HELMET_OF_HELENA = ITEMS.register("helmetofhelena",
            () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
