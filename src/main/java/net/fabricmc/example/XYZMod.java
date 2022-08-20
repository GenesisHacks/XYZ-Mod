package net.fabricmc.example;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.util.InputUtil;
import net.minecraft.client.option.KeyBinding;
import org.lwjgl.glfw.GLFW;

public class XYZMod implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("xyz");

	public static KeyBinding xkeyBinding;
	public static boolean on;
	public static int col1 = -13617512;
	public static int col2 = -6750208;

	@Override
	public void onInitialize() {
		xkeyBinding = new KeyBinding("Change Mode", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_Z, "XYZ");
		KeyBindingHelper.registerKeyBinding(xkeyBinding);
	}
}
