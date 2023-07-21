package net.elidhan.triggerhappy.enchantment;

import net.elidhan.triggerhappy.TriggerHappy;
import net.elidhan.triggerhappy.item.GunItem;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;

public class GunEnchantment extends Enchantment
{
    protected GunEnchantment(Rarity rarity)
    {
        super(rarity, TriggerHappy.GUN, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
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
        return 5;
    }

    @Override
    public boolean isAcceptableItem(ItemStack stack)
    {
        return stack.getItem() instanceof GunItem;
    }
}
