package net.fabricmc.example.mixin;

import net.minecraft.world.dimension.DimensionType;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.network.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.fabricmc.example.XYZMod;

@Mixin({InGameHud.class})
public class XYZMixin {
	TextRenderer Coords = (MinecraftClient.getInstance().textRenderer);
	ClientPlayerEntity currentPlayer;
	DimensionType currentDimension;
	int windowHeight = 0;
	int CurrentX = 0;
	int CurrentY = 0;
	int CurrentZ = 0;
	String newGeneralCoordinates = "X: ?, Y: ?, Z: ?";
	String newOverworld = "X: ?, Y: ?, Z: ?";
	String newNether = "X: ?, Y: ?, Z: ?";

	@Inject(at = {@At("HEAD")}, method = {"render"}, cancellable = true)
	public void onRender(MatrixStack matrices, float tickDelta, CallbackInfo info)throws InterruptedException {



		if (XYZMod.xkeyBinding.wasPressed()) {
			XYZMod.on = !XYZMod.on;
			XYZMod.col1 = XYZMod.on ? -13617512 : -1;
			XYZMod.col2 = XYZMod.on ?  -6750208 : -1;
		}



		currentPlayer = (MinecraftClient.getInstance()).player;
		currentDimension = currentPlayer.getEntityWorld().getDimension();
		windowHeight = MinecraftClient.getInstance().getWindow().getScaledHeight();
		CurrentX = currentPlayer.getBlockX();
		CurrentY = currentPlayer.getBlockY();
		CurrentZ = currentPlayer.getBlockZ();

		if (currentDimension.bedWorks()) {
			newOverworld = "Overworld: X:" + CurrentX + ", Y:" + CurrentY + ", Z:" + CurrentZ;
			newNether = "Nether: X:" + CurrentX / 8 + ", Y: " + CurrentY + ", Z: " + CurrentZ / 8;
			Coords.drawWithShadow(matrices, newOverworld, 7.0F, (windowHeight - 70), XYZMod.col1);
			Coords.drawWithShadow(matrices, newNether, 7.0F, (windowHeight - 60), XYZMod.col2);
		}
		else if (currentDimension.respawnAnchorWorks()) {
			newOverworld = "Overworld: X:" + CurrentX * 8 + ", Y:" + CurrentY + ", Z:" + CurrentZ * 8;
			newNether = "Nether: X:" + CurrentX + ", Y: " + CurrentY + ", Z: " + CurrentZ;
			Coords.drawWithShadow(matrices, newOverworld, 7.0F, (windowHeight - 60), XYZMod.col1);
			Coords.drawWithShadow(matrices, newNether, 7.0F, (windowHeight - 70), XYZMod.col2);
		}
		else {
			newGeneralCoordinates = "x: " + CurrentX + ", y: " + CurrentY + ", z: " + CurrentZ;
			Coords.drawWithShadow(matrices, newGeneralCoordinates, 7.0F, (windowHeight - 60), -1);
		}
	}
}
