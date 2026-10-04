package com.wmy.landmine;

import com.wmy.landmine.item.ModItems;
import net.fabricmc.api.ModInitializer;

import net.minecraft.resources.Identifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LandmineMod implements ModInitializer {
	public static final String MOD_ID = "landmine";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {

		ModItems.register();// 调用了 ModItems 类中的 register 方法：注册物品
		LandmineMod.LOGGER.info("Running Landmine-Mod!");
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}