package com.wmy.landmine.item;

import com.wmy.landmine.LandmineMod;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.CreativeModeTabs;

import java.util.function.Function;

public class ModItems {
    //物品注册区
    // 注册物品（第一步）
    public static Item LANDMINE;
    public static Item TANK_MINE;

    /*方法区*/
    private static Item registerItem(final String name, final Function<Item.Properties, Item> itemFactory) {
        return registerItem(name, itemFactory, new Item.Properties());
    }
    //注册方法
    private static Item registerItem(final String name, final Function<Item.Properties, Item> itemFactory, final Item.Properties properties) {
        ResourceKey<Item> key = ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(LandmineMod.MOD_ID, name));
        Item item = itemFactory.apply(properties.setId(key));
        if (item instanceof BlockItem blockItem) {
            blockItem.registerBlocks(Item.BY_BLOCK, item);
        }

        return Registry.register(BuiltInRegistries.ITEM, key, item);
    }
    //注册方法简化
    private static Item registerItem(final String name) {
        return registerItem(name, Item::new, new Item.Properties());
    }
    // 注册物品（第二步）注册入口
    public static void register() {
        LANDMINE = registerItem("landmine");
        TANK_MINE = registerItem("tank_mine");
        LandmineMod.LOGGER.info("Landmine Mod Items Registered Successfully!");
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.TOOLS_AND_UTILITIES)
                .register((fabricCreativeModeTabOutput -> {
                    fabricCreativeModeTabOutput.accept(LANDMINE);
                    fabricCreativeModeTabOutput.accept(TANK_MINE);
                    LandmineMod.LOGGER.info("Landmine Mod Items Added to Creative Mode Tab Successfully!");
                }));
    }
}