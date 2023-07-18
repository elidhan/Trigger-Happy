package net.elidhan.triggerhappy.entity;

import net.elidhan.triggerhappy.TriggerHappy;
import net.elidhan.triggerhappy.item.BulletItem;
import net.elidhan.triggerhappy.item.ModItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.FlyingItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Util;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;

public class BulletEntity extends PersistentProjectileEntity implements FlyingItemEntity
{
    private float damage;
    private int lifeTick;
    private boolean explosive;
    private static final TrackedData<ItemStack> ITEM = DataTracker.registerData(BulletEntity.class, TrackedDataHandlerRegistry.ITEM_STACK);
    private int forceOfNature;

    public BulletEntity(EntityType<? extends BulletEntity> entityType, World world)
    {
        super(entityType, world);
        this.pickupType = PickupPermission.DISALLOWED;
    }

    public BulletEntity(LivingEntity owner, World world, float damage, boolean explosive, ItemStack stack)
    {
        super(TriggerHappy.BulletEntityType, owner, world);

        this.lifeTick = 0;
        this.damage = damage;
        this.explosive = explosive;
        this.forceOfNature = 0;

        this.setNoGravity(true);
        this.setSilent(true);

        this.dataTracker.set(ITEM, stack);
    }

    @Override
    public void tick()
    {
        super.tick();

        if(this.lifeTick++ >= 20)
        {
            this.discard();
        }
    }

    @Override
    protected void onCollision(HitResult hitResult)
    {
        super.onCollision(hitResult);
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult)
    {
        if(entityHitResult.getEntity() instanceof LivingEntity entity)
        {
            entity.damage(this.getDamageSources().arrow(this, this.getOwner() != null?this.getOwner():this), this.damage);
            entity.timeUntilRegen = 0;
            if(this.explosive)
            {
                if (!this.world.isClient) {
                    this.world.createExplosion(this, this.getX(), this.getY(), this.getZ(), 3, false, World.ExplosionSourceType.NONE);
                }
            }
        }
        this.discard();
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult)
    {
        if(this.explosive)
        {
            if (!this.world.isClient) {
                this.world.createExplosion(this, this.getX(), this.getY(), this.getZ(), 3, false, World.ExplosionSourceType.NONE);
            }
        }
        this.discard();
    }

    protected ItemStack getItem() {
        return this.getDataTracker().get(ITEM);
    }

    @Override
    public ItemStack getStack() {
        ItemStack itemStack = this.getItem();
        return itemStack.isEmpty() ? new ItemStack(ModItems.IRON_BULLET, 1) : itemStack;
    }

    public void setItem(ItemStack item) {
        if (!(item.getItem() instanceof BulletItem) || item.hasNbt()) {
            this.getDataTracker().set(ITEM, Util.make(item.copy(), stack -> stack.setCount(1)));
        }
    }

    @Override
    public boolean shouldRender(double distance)
    {
        return this.getVelocity().length() <= 4;
    }

    @Override
    public boolean shouldRender(double cameraX, double cameraY, double cameraZ)
    {
        return this.getVelocity().length() <= 4;
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.getDataTracker().startTracking(ITEM, ItemStack.EMPTY);
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        ItemStack itemStack = this.getItem();
        if (!itemStack.isEmpty()) {
            nbt.put("Item", itemStack.writeNbt(new NbtCompound()));
        }
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        ItemStack itemStack = ItemStack.fromNbt(nbt.getCompound("Item"));
        this.setItem(itemStack);
    }

    @Override
    protected ItemStack asItemStack()
    {
        return getStack();
    }
}
