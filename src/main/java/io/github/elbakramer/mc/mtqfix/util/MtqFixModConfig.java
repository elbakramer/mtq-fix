package io.github.elbakramer.mc.mtqfix.util;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

@Config(name = "mtq-fix")
public class MtqFixModConfig implements ConfigData {

    public boolean bypassVehicleMovedTooQuicklyTest = true;
    public boolean bypassVehicleMovedWronglyTest = true;
    public boolean bypassRevertingPositionOnVehicleMovedWrongly = true;
    public boolean bypassPlayerMovedTooQuicklyTest = true;
    public boolean bypassPlayerMovedWronglyTest = true;

    public boolean logOnBypassVehicleMovedTooQuicklyTest = false;
    public boolean logOnBypassVehicleMovedWronglyTest = false;
    public boolean logOnBypassRevertingPositionOnVehicleMovedWrongly = false;
    public boolean logOnBypassPlayerMovedTooQuicklyTest = false;
    public boolean logOnBypassPlayerMovedWronglyTest = false;

}
