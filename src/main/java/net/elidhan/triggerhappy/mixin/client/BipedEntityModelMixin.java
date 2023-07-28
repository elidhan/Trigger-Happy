package net.elidhan.triggerhappy.mixin.client;

import net.elidhan.triggerhappy.TriggerHappy;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BipedEntityModel.class)
public class BipedEntityModelMixin<T extends LivingEntity>
{
    @Final
    @Shadow
    public ModelPart rightArm;

    @Final
    @Shadow
    public ModelPart leftArm;

    @Final
    @Shadow
    public ModelPart head;

    @Shadow
    public BipedEntityModel.ArmPose rightArmPose;
    @Shadow
    public BipedEntityModel.ArmPose leftArmPose;

    @Inject(method = "positionRightArm", at = @At("HEAD"), cancellable = true)
    public void gunRightArm(T entity, CallbackInfo ci)
    {
        if(this.rightArmPose == TriggerHappy.ONE_HANDED_GUN)
        {
            this.rightArm.pitch = this.head.pitch - 1.5707964f;
            this.rightArm.yaw = this.head.yaw;
            this.leftArm.yaw = 0.0f;
            ci.cancel();
        }
        if(this.rightArmPose == TriggerHappy.TWO_HANDED_GUN)
        {
            this.rightArm.pitch = this.head.pitch - 1.5707964f;
            this.leftArm.pitch = this.head.pitch - 1.5707964f;
            this.rightArm.yaw = this.head.yaw;
            this.leftArm.yaw = this.head.yaw + 0.4f;
            ci.cancel();
        }
    }

    @Inject(method = "positionLeftArm", at = @At("HEAD"), cancellable = true)
    public void gunLeftArm(T entity, CallbackInfo ci)
    {
        if(this.leftArmPose == TriggerHappy.ONE_HANDED_GUN)
        {
            this.leftArm.pitch = this.head.pitch - 1.5707964f;
            this.leftArm.yaw = this.head.yaw;
            this.rightArm.yaw = 0.0f;
            ci.cancel();
        }
        if(this.leftArmPose == TriggerHappy.TWO_HANDED_GUN)
        {
            this.leftArm.pitch = this.head.pitch - 1.5707964f;
            this.rightArm.pitch = this.head.pitch - 1.5707964f;
            this.leftArm.yaw = this.head.yaw;
            this.rightArm.yaw = this.head.yaw - 0.4f;
            ci.cancel();
        }
    }
}
