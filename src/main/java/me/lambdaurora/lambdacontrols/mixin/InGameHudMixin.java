/*
 * Copyright © 2020 LambdAurora <aurora42lambda@gmail.com>
 *
 * This file is part of LambdaControls.
 *
 * Licensed under the MIT license. For more information,
 * see the LICENSE file.
 */

package me.lambdaurora.lambdacontrols.mixin;

import me.lambdaurora.lambdacontrols.LambdaControls;
import me.lambdaurora.lambdacontrols.gui.LambdaControlsHud;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class InGameHudMixin
{
    private LambdaControlsHud lambdacontrols_hud;

    @Inject(method = "<init>", at = @At("RETURN"))
    public void on_new(MinecraftClient client, CallbackInfo ci)
    {
        this.lambdacontrols_hud = new LambdaControlsHud(client, LambdaControls.get());
    }

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderMountHealth()V"))
    public void on_render(CallbackInfo ci)
    {
        lambdacontrols_hud.render();
    }
}
