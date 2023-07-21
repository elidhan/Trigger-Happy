package net.elidhan.triggerhappy.item;

import net.elidhan.triggerhappy.entity.BulletEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BulletItem extends Item
{
    private final float damage;
    private final float speed;
    private final boolean explosive;
    public BulletItem(Settings settings, float damage, float speed, boolean explosive)
    {
        super(settings);
        this.damage = damage;
        this.speed = speed;
        this.explosive = explosive;
    }

    public BulletEntity createBullet(World world, ItemStack stack, float gunDamage, float damageMult, LivingEntity shooter)
    {
        BulletEntity bullet = new BulletEntity(shooter, world, (this.damage+gunDamage)*damageMult, this.explosive, stack);
        bullet.setItem(stack);
        return bullet;
    }

    public float getSpeed()
    {
        return this.speed;
    }
}
