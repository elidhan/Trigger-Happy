package net.elidhan.triggerhappy.enchantment;

import net.elidhan.triggerhappy.item.GunItem;
import net.elidhan.triggerhappy.mixin.enchantment.EnchantmentTargetMixin;
import net.minecraft.item.Item;

@SuppressWarnings("unused")
public class GunEnchantmentTarget extends EnchantmentTargetMixin
{
    @Override
    public boolean isAcceptableItem(Item item)
    {
        return item instanceof GunItem;
    }
}
