package io.github.elbakramer.mc.mtqfix.util;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = "mtq-fix")
public class MtqFixModConfig implements ConfigData {

    @ConfigEntry.Category("vehicle")
    @ConfigEntry.Gui.Tooltip
    public boolean bypassVehicleMovedTooQuicklyTest = true;

    @ConfigEntry.Category("vehicle")
    @ConfigEntry.Gui.Tooltip
    public boolean bypassVehicleMovedWronglyTest = true;

    @ConfigEntry.Category("vehicle")
    @ConfigEntry.Gui.Tooltip(count = 2)
    public boolean bypassVehiclePositionRevertingLogic = false;

    @ConfigEntry.Category("vehicle")
    @ConfigEntry.Gui.Tooltip
    public boolean reproduceOnBypassVehicleMovedTooQuicklyTest = false;

    @ConfigEntry.Category("vehicle")
    @ConfigEntry.Gui.Tooltip
    public boolean reproduceOnBypassVehicleMovedWronglyTest = false;

    @ConfigEntry.Category("vehicle")
    @ConfigEntry.Gui.Tooltip
    public boolean logOnBypassVehicleMovedTooQuicklyTest = false;

    @ConfigEntry.Category("vehicle")
    @ConfigEntry.Gui.Tooltip
    public boolean logOnBypassVehicleMovedWronglyTest = false;

    @ConfigEntry.Category("vehicle")
    @ConfigEntry.Gui.Tooltip
    public boolean logOnBypassVehiclePositionRevertingLogic = false;

    @ConfigEntry.Category("player")
    @ConfigEntry.Gui.Tooltip
    public boolean bypassPlayerMovedTooQuicklyTest = true;

    @ConfigEntry.Category("player")
    @ConfigEntry.Gui.Tooltip
    public boolean bypassPlayerMovedWronglyTest = true;

    @ConfigEntry.Category("player")
    @ConfigEntry.Gui.Tooltip
    public boolean reproduceOnBypassPlayerMovedTooQuicklyTest = false;

    @ConfigEntry.Category("player")
    @ConfigEntry.Gui.Tooltip
    public boolean reproduceOnBypassPlayerMovedWronglyTest = false;

    @ConfigEntry.Category("player")
    @ConfigEntry.Gui.Tooltip
    public boolean logOnBypassPlayerMovedTooQuicklyTest = false;

    @ConfigEntry.Category("player")
    @ConfigEntry.Gui.Tooltip
    public boolean logOnBypassPlayerMovedWronglyTest = false;

}
