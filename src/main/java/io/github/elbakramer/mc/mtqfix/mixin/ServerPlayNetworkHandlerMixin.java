package io.github.elbakramer.mc.mtqfix.mixin;

import org.apache.logging.log4j.Logger;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Slice;

import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.GameMode;
import net.minecraft.world.GameRules;

import io.github.elbakramer.mc.mtqfix.MtqFixMod;
import io.github.elbakramer.mc.mtqfix.util.MtqFixModConfig;
import io.github.elbakramer.mc.mtqfix.util.MtqFixModConfigManager;

@Mixin(ServerPlayNetworkHandler.class)
public abstract class ServerPlayNetworkHandlerMixin {

	@Shadow
	private ServerPlayerEntity player;

	@Shadow
	private Entity topmostRiddenEntity;

	@Shadow
	private Vec3d requestedTeleportPos;

	@Shadow
	private int movePacketsCount;

	@Shadow
	private int lastTickMovePacketsCount;

	@Shadow
	protected abstract boolean isHost();

	@ModifyVariable(method = "onVehicleMove", index = 26, at = @At(value = "STORE"), slice = @Slice(from = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;getVelocity()Lnet/minecraft/util/math/Vec3d;", ordinal = 0), to = @At(value = "INVOKE", target = "Lnet/minecraft/server/world/ServerWorld;isSpaceEmpty(Lnet/minecraft/entity/Entity;Lnet/minecraft/util/math/Box;)Z", ordinal = 0)))
	private double bypassVehicleMovedTooQuicklyTest(double aboutToMoveLengthSquared) {
		Logger LOGGER = MtqFixMod.LOGGER;
		MtqFixModConfig config = MtqFixModConfigManager.getConfig();
		if (config.bypassVehicleMovedTooQuicklyTest) {
			double overridingValue = aboutToMoveLengthSquared;
			if (config.reproduceOnBypassVehicleMovedTooQuicklyTest) {
				Entity entity = this.player.getRootVehicle();
				if (entity != this.player && entity.getPrimaryPassenger() == this.player
						&& entity == this.topmostRiddenEntity) {
					double o = entity.getVelocity().lengthSquared();
					double p = aboutToMoveLengthSquared;
					if (p - o > 100.0D && !this.isHost()) {
						overridingValue = 0.0D;
					}
				}
			} else if (overridingValue > 100.D) {
				overridingValue = 0.0D;
			}
			if (config.logOnBypassVehicleMovedTooQuicklyTest && overridingValue != aboutToMoveLengthSquared) {
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
		MtqFixModConfig config = MtqFixModConfigManager.getConfig();
		if (config.bypassVehicleMovedWronglyTest) {
			double overridingValue = distanceFromTargetSquared;
			if (config.reproduceOnBypassPlayerMovedWronglyTest) {
				Entity entity = this.player.getRootVehicle();
				if (entity != this.player && entity.getPrimaryPassenger() == this.player
						&& entity == this.topmostRiddenEntity) {
					double p = distanceFromTargetSquared;
					if (p > 0.0625D) {
						overridingValue = 0.0D;
					}
				}
			} else if (overridingValue > 0.0625D) {
				overridingValue = 0.0D;
			}
			if (config.logOnBypassVehicleMovedWronglyTest && overridingValue != distanceFromTargetSquared) {
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
		MtqFixModConfig config = MtqFixModConfigManager.getConfig();
		if (config.bypassVehiclePositionRevertingLogic) {
			boolean overridingValue = false;
			if (config.logOnBypassVehiclePositionRevertingLogic && overridingValue != startingBoundingSpaceNonEmpty) {
				LOGGER.info("[MtqFix] Bypassing RevertingPositionOnVehicleMovedWrongly Logic (overriding {} to {})",
						startingBoundingSpaceNonEmpty, overridingValue);
			}
			return overridingValue;
		}
		return startingBoundingSpaceNonEmpty;
	}

	@ModifyVariable(method = "onPlayerMove", index = 27, at = @At(value = "STORE"), slice = @Slice(from = @At(value = "INVOKE", target = "Lnet/minecraft/server/network/ServerPlayerEntity;getVelocity()Lnet/minecraft/util/math/Vec3d;", ordinal = 0), to = @At(value = "INVOKE", target = "Lnet/minecraft/server/network/ServerPlayerEntity;isSleeping()Z", ordinal = 0)))
	private double bypassPlayerMovedTooQuicklyTest(double aboutToMoveLengthSquared) {
		Logger LOGGER = MtqFixMod.LOGGER;
		MtqFixModConfig config = MtqFixModConfigManager.getConfig();
		if (config.bypassPlayerMovedTooQuicklyTest) {
			double overridingValue = aboutToMoveLengthSquared;
			if (config.reproduceOnBypassPlayerMovedTooQuicklyTest) {
				if (!this.player.notInAnyWorld && this.requestedTeleportPos == null && !this.player.hasVehicle()) {
					double p = player.getVelocity().lengthSquared();
					double q = aboutToMoveLengthSquared;
					if (this.player.isSleeping()) {
						if (q > 1.0D) {
						}
					} else {
						int r = this.movePacketsCount - this.lastTickMovePacketsCount;
						if (r > 5) {
							r = 1;
						}
						if (!this.player.isInTeleportationState() && (!this.player.getServerWorld().getGameRules()
								.getBoolean(GameRules.DISABLE_ELYTRA_MOVEMENT_CHECK) || !this.player.isFallFlying())) {
							float s = this.player.isFallFlying() ? 300.0F : 100.0F;
							double t = (double) (s * (float) r);
							if (q - p > t && !this.isHost()) {
								overridingValue = 1.1D;
							}
						}
					}
				}
			} else if (overridingValue > 100.0D) {
				overridingValue = 1.1D;
			}
			if (config.logOnBypassPlayerMovedTooQuicklyTest && overridingValue != aboutToMoveLengthSquared) {
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
		MtqFixModConfig config = MtqFixModConfigManager.getConfig();
		if (config.bypassPlayerMovedWronglyTest) {
			double overridingValue = distanceFromTargetSquared;
			if (config.reproduceOnBypassPlayerMovedWronglyTest) {
				if (!this.player.notInAnyWorld && this.requestedTeleportPos == null && !this.player.hasVehicle()
						&& !this.player.isSleeping()) {
					double q = distanceFromTargetSquared;
					if (!this.player.isInTeleportationState() && q > 0.0625D && !this.player.isSleeping()
							&& !this.player.interactionManager.isCreative()
							&& this.player.interactionManager.getGameMode() != GameMode.SPECTATOR) {
						overridingValue = 0.0D;
					}
				}
			} else if (overridingValue > 0.0625D) {
				overridingValue = 0.0D;
			}
			if (config.logOnBypassPlayerMovedWronglyTest && overridingValue != distanceFromTargetSquared) {
				LOGGER.info("[MtqFix] Bypassing OnPlayerMovedWrongly Test (overriding {} to {})",
						distanceFromTargetSquared, overridingValue);
			}
			return overridingValue;
		}
		return distanceFromTargetSquared;
	}

}
