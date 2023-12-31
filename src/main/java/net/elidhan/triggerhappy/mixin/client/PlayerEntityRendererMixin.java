package net.elidhan.triggerhappy.mixin.client;

import net.elidhan.triggerhappy.TriggerHappyClient;
import net.elidhan.triggerhappy.item.GunItem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Environment(EnvType.CLIENT)
@Mixin(PlayerEntityRenderer.class)
public class PlayerEntityRendererMixin
{
    @Inject(method = "getArmPose", at = @At("HEAD"), cancellable = true)
    private static void gunPose(AbstractClientPlayerEntity player, Hand hand, CallbackInfoReturnable<BipedEntityModel.ArmPose> ci)
    {
        ItemStack itemStack = player.getStackInHand(hand);
        if (itemStack.getItem() instanceof GunItem gunItem) {
            ci.setReturnValue(gunItem.oneHanded ? TriggerHappyClient.ONE_HANDED_GUN:TriggerHappyClient.TWO_HANDED_GUN);
        }
    }
}
