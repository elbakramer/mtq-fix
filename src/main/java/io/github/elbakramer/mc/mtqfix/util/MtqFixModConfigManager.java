package io.github.elbakramer.mc.mtqfix.util;

import java.util.function.Supplier;

import net.minecraft.client.gui.screen.Screen;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;

public class MtqFixModConfigManager {

    public static void register() {
        AutoConfig.register(MtqFixModConfig.class, JanksonConfigSerializer::new);
    }

    public static MtqFixModConfig getConfig() {
        return AutoConfig.getConfigHolder(MtqFixModConfig.class).getConfig();
    }

    public static Supplier<Screen> getConfigScreen(Screen parent) {
        return AutoConfig.getConfigScreen(MtqFixModConfig.class, parent);
    }

}
