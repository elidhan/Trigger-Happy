package net.elidhan.triggerhappy.item;

import net.elidhan.triggerhappy.enchantment.ModEnchantments;
import net.elidhan.triggerhappy.entity.BulletEntity;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.Random;
import java.util.function.Predicate;

public class GunItem extends RangedWeaponItem
{
    private final Random random;
    public final float damage;
    public final int cooldown;
    public final int shotCount;
    public final float spread;
    public final float damageMult;
    public final ToolMaterial material;
    public final SoundEvent sound;

    public GunItem(Settings settings, float damage, int cooldown, int shotCount, float spread, float damageMult, ToolMaterial material, SoundEvent sound)
    {
        super(settings);
        this.random = new Random();
        this.damage = damage;
        this.cooldown = cooldown;
        this.shotCount = shotCount;
        this.spread = spread;
        this.damageMult = damageMult;
        this.material = material;
        this.sound = sound;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand)
    {
        ItemStack gun = user.getStackInHand(hand);
        ItemStack ammo = user.getProjectileType(gun);

        if(canShoot(gun, ammo, user))
        {
            if(ammo.isEmpty() || !(ammo.getItem() instanceof BulletItem)) ammo = new ItemStack(ModItems.IRON_BULLET);

            BulletItem bulletItem = (BulletItem)(ammo.getItem() instanceof BulletItem ? ammo.getItem() : ModItems.IRON_BULLET);

            float extraDamage = this.damage;
            int totalCooldown = this.cooldown;
            float ammoPreserveChance = 0;
            int forceOfNatureLevel = 0;

            int j, k, l;

            if((j = EnchantmentHelper.getLevel(ModEnchantments.LETHAL, gun)) > 0)
            {
                extraDamage += 0.5f * j;
            }
            if(EnchantmentHelper.getLevel(ModEnchantments.QUICKDRAW, gun) > 0)
            {
                totalCooldown *= 0.75f;
            }
            if((k = EnchantmentHelper.getLevel(ModEnchantments.AMMO_MANAGEMENT, gun)) > 0)
            {
                ammoPreserveChance = 0.11f * k;
            }
            if((l = EnchantmentHelper.getLevel(ModEnchantments.FORCE_OF_NATURE, gun)) > 0)
            {
                forceOfNatureLevel = l;
            }

            if(!world.isClient())
            {
                for (int i = 0; i < shotCount; i++)
                {
                    BulletEntity bulletEntity = bulletItem.createBullet(world, ammo, extraDamage, this.damageMult, user);
                    bulletEntity.setVelocity(user, user.getPitch(), user.getYaw(), 0.0f, ((BulletItem)ammo.getItem()).getSpeed(), this.spread);
                    bulletEntity.setForceOfNature(forceOfNatureLevel);

                    world.spawnEntity(bulletEntity);
                }
                if(!user.isCreative())
                {
                    gun.damage(1, user, p -> p.sendToolBreakStatus(user.getActiveHand()));
                    if(!shouldPreserveAmmo(ammoPreserveChance))
                    {
                        ammo.decrement(1);
                        if (ammo.isEmpty()) {
                            user.getInventory().removeOne(ammo);
                        }
                    }
                }
                float pitch = 0.95f + world.getRandom().nextFloat() * (1.05f - 0.95f);
                world.playSound(null, user.getX(), user.getY(), user.getZ(), this.sound, SoundCategory.MASTER, 0.85f, pitch);
            }
            user.getItemCooldownManager().set(this, totalCooldown);
        }

        return TypedActionResult.fail(gun);
    }

    private boolean shouldPreserveAmmo(float chance)
    {
        return this.random.nextFloat(0.01f, 1) <= chance;
    }

    private boolean canShoot(ItemStack gun, ItemStack ammo, PlayerEntity user)
    {
        return !user.getItemCooldownManager().isCoolingDown(gun.getItem()) && (user.isCreative() || !ammo.isEmpty());
    }

    public static final Predicate<ItemStack> BULLETS = (stack) -> stack.getItem() instanceof BulletItem;

    @Override
    public Predicate<ItemStack> getProjectiles()
    {
        return BULLETS;
    }

    @Override
    public int getRange()
    {
        return 15;
    }

    @Override
    public boolean canRepair(ItemStack stack, ItemStack ingredient)
    {
        return this.material.getRepairIngredient().test(ingredient) || super.canRepair(stack, ingredient);
    }

    @Override
    public int getEnchantability()
    {
        return this.material.getEnchantability();
    }
}
