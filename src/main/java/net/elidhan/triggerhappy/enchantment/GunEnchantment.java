package net.elidhan.triggerhappy.enchantment;

import net.elidhan.triggerhappy.item.GunItem;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;

public class GunEnchantment extends Enchantment
{
    private final int maxLevel;

    protected GunEnchantment(int maxLevel, Rarity rarity)
    {
        super(rarity, EnchantmentTarget.WEAPON, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
        this.maxLevel = maxLevel;
    }

    @Override
    public int getMinPower(int level)
    {
        return 1 + (level - 1) * 11;
    }

    @Override
    public int getMaxPower(int level)
    {
        return this.getMinPower(level) + 20;
    }

    @Override
    public int getMaxLevel()
    {
        return maxLevel;
    }

    @Override
    public boolean isAcceptableItem(ItemStack stack)
    {
        return stack.getItem() instanceof GunItem;
    }
}
