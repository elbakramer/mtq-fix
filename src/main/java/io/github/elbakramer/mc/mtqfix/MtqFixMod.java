package io.github.elbakramer.mc.mtqfix;

import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.github.elbakramer.mc.mtqfix.util.MtqFixModConfigManager;

public class MtqFixMod implements ModInitializer {

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LogManager.getLogger("MtqFix");

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		MtqFixModConfigManager.register();
		LOGGER.info("[MtqFix] Mod Initialized.");
	}

}
