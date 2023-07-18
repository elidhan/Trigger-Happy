package net.elidhan.triggerhappy;

import net.elidhan.triggerhappy.client.render.projectile.BulletEntityRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

@Environment(EnvType.CLIENT)
public class TriggerHappyClient implements ClientModInitializer
{
    @Override
    public void onInitializeClient()
    {
        EntityRendererRegistry.register(TriggerHappy.BulletEntityType, context -> new BulletEntityRenderer<>(context, 0.5f, true));
    }
}
