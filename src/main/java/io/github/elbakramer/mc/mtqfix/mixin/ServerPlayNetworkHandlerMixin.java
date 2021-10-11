package io.github.elbakramer.mc.mtqfix.mixin;

import net.minecraft.server.network.ServerPlayNetworkHandler;

import org.apache.logging.log4j.Logger;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Slice;

import me.shedaniel.autoconfig.AutoConfig;

import io.github.elbakramer.mc.mtqfix.MtqFixMod;
import io.github.elbakramer.mc.mtqfix.util.MtqFixModConfig;

@Mixin(ServerPlayNetworkHandler.class)
public class ServerPlayNetworkHandlerMixin {

	@ModifyVariable(method = "onVehicleMove", index = 26, at = @At(value = "STORE"), slice = @Slice(from = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;getVelocity()Lnet/minecraft/util/math/Vec3d;", ordinal = 0), to = @At(value = "INVOKE", target = "Lnet/minecraft/server/world/ServerWorld;isSpaceEmpty(Lnet/minecraft/entity/Entity;Lnet/minecraft/util/math/Box;)Z", ordinal = 0)))
	private double bypassVehicleMovedTooQuicklyTest(double aboutToMoveLengthSquared) {
		Logger LOGGER = MtqFixMod.LOGGER;
		MtqFixModConfig config = AutoConfig.getConfigHolder(MtqFixModConfig.class).getConfig();
		if (config.bypassVehicleMovedTooQuicklyTest) {
			double overridingValue = 0.0D;
			if (config.logOnBypassVehicleMovedTooQuicklyTest && aboutToMoveLengthSquared != overridingValue) {
				LOGGER.info("[MtqFix] Bypassing OnVehicleMovedToQuickly Test (overriding {} to {})",
						aboutToMoveLengthSquared, overridingValue);
			}
			return overridingValue;
		}
		return aboutToMoveLengthSquared;
	}

	@ModifyVariable(method = "onVehicleMove", index = 26, at = @At(value = "STORE"), slice = @Slice(from = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;move(Lnet/minecraft/entity/MovementType;Lnet/minecraft/util/math/Vec3d;)V", ordinal = 0), to = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;updatePositionAndAngles(DDDFF)V", ordinal = 0)))
	private double bypassVehicleMovedWronglyTest(double distanceFromTargetSquared) {
		Logger LOGGER = MtqFixMod.LOGGER;
		MtqFixModConfig config = AutoConfig.getConfigHolder(MtqFixModConfig.class).getConfig();
		if (config.bypassVehicleMovedWronglyTest) {
			double overridingValue = 0.0D;
			if (config.logOnBypassVehicleMovedWronglyTest && distanceFromTargetSquared != overridingValue) {
				LOGGER.info("[MtqFix] Bypassing OnVehicleMovedWrongly Test (overriding {} to {})",
						distanceFromTargetSquared, overridingValue);
			}
			return overridingValue;
		}
		return distanceFromTargetSquared;
	}

	@ModifyVariable(method = "onVehicleMove", index = 28, at = @At(value = "STORE"), slice = @Slice(from = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;getVelocity()Lnet/minecraft/util/math/Vec3d;", ordinal = 0), to = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;move(Lnet/minecraft/entity/MovementType;Lnet/minecraft/util/math/Vec3d;)V", ordinal = 0)))
	private boolean bypassRevertingPositionOnVehicleMovedWrongly(boolean startingBoundingSpaceNonEmpty) {
		Logger LOGGER = MtqFixMod.LOGGER;
		MtqFixModConfig config = AutoConfig.getConfigHolder(MtqFixModConfig.class).getConfig();
		if (config.bypassVehiclePositionRevertingLogic) {
			boolean overridingValue = false;
			if (config.logOnBypassVehiclePositionRevertingLogic && startingBoundingSpaceNonEmpty != overridingValue) {
				LOGGER.info("[MtqFix] Bypassing RevertingPositionOnVehicleMovedWrongly Logic (overriding {} to {})",
						startingBoundingSpaceNonEmpty, overridingValue);
			}
			return false;
		}
		return startingBoundingSpaceNonEmpty;
	}

	@ModifyVariable(method = "onPlayerMove", index = 27, at = @At(value = "STORE"), slice = @Slice(from = @At(value = "INVOKE", target = "Lnet/minecraft/server/network/ServerPlayerEntity;getVelocity()Lnet/minecraft/util/math/Vec3d;", ordinal = 0), to = @At(value = "INVOKE", target = "Lnet/minecraft/server/network/ServerPlayerEntity;isSleeping()Z", ordinal = 0)))
	private double bypassPlayerMovedTooQuicklyTest(double aboutToMoveLengthSquared) {
		Logger LOGGER = MtqFixMod.LOGGER;
		MtqFixModConfig config = AutoConfig.getConfigHolder(MtqFixModConfig.class).getConfig();
		if (config.bypassPlayerMovedTooQuicklyTest) {
			double overridingValue = 0.0D;
			if (config.logOnBypassPlayerMovedTooQuicklyTest && aboutToMoveLengthSquared != overridingValue) {
				LOGGER.info("[MtqFix] Bypassing OnPlayerMovedToQuickly Test (overriding {} to {})",
						aboutToMoveLengthSquared, overridingValue);
			}
			return overridingValue;
		}
		return aboutToMoveLengthSquared;
	}

	@ModifyVariable(method = "onPlayerMove", index = 27, at = @At(value = "STORE"), slice = @Slice(from = @At(value = "INVOKE", target = "Lnet/minecraft/server/network/ServerPlayerEntity;move(Lnet/minecraft/entity/MovementType;Lnet/minecraft/util/math/Vec3d;)V", ordinal = 0), to = @At(value = "INVOKE", target = "Lnet/minecraft/server/network/ServerPlayerEntity;updatePositionAndAngles(DDDFF)V", ordinal = 1)))
	private double bypassPlayerMovedWronglyTest(double distanceFromTargetSquared) {
		Logger LOGGER = MtqFixMod.LOGGER;
		MtqFixModConfig config = AutoConfig.getConfigHolder(MtqFixModConfig.class).getConfig();
		if (config.bypassPlayerMovedWronglyTest) {
			double overridingValue = 0.0D;
			if (config.logOnBypassPlayerMovedWronglyTest && distanceFromTargetSquared != overridingValue) {
				LOGGER.info("[MtqFix] Bypassing OnPlayerMovedWrongly Test (overriding {} to {})",
						distanceFromTargetSquared, overridingValue);
			}
			return overridingValue;
		}
		return distanceFromTargetSquared;
	}

}
