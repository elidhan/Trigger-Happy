package net.elidhan.triggerhappy.mixin.client;

import net.elidhan.triggerhappy.item.GunItem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin
{
    @Shadow
    private int itemUseCooldown;

    @Inject(method = "doItemUse", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/ActionResult;isAccepted()Z", ordinal = 3),locals = LocalCapture.CAPTURE_FAILHARD)
    public void doItemUse(CallbackInfo ci, Hand[] var1, int var2, int var3, Hand hand, ItemStack itemStack, ActionResult actionResult3)
    {
        if (itemStack.getItem() instanceof GunItem)
        {
            itemUseCooldown = 0;
        }
    }
}
