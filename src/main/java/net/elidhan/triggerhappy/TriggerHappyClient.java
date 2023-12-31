package net.elidhan.triggerhappy;

import com.chocohead.mm.api.ClassTinkerers;
import net.elidhan.triggerhappy.client.render.projectile.BulletEntityRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.model.BipedEntityModel;

@Environment(EnvType.CLIENT)
public class TriggerHappyClient implements ClientModInitializer
{
    public static final BipedEntityModel.ArmPose ONE_HANDED_GUN = ClassTinkerers.getEnum(BipedEntityModel.ArmPose.class, "ONE_HANDED_GUN");
    public static final BipedEntityModel.ArmPose TWO_HANDED_GUN = ClassTinkerers.getEnum(BipedEntityModel.ArmPose.class, "TWO_HANDED_GUN");
    @Override
    public void onInitializeClient()
    {
        EntityRendererRegistry.register(TriggerHappy.BulletEntityType, context -> new BulletEntityRenderer<>(context, 0.5f, true));
    }
}
