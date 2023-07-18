package net.elidhan.triggerhappy.enchantment;

public class AmmoManagementEnchantment extends GunEnchantment
{
    protected AmmoManagementEnchantment(Rarity rarity)
    {
        super(3, rarity);
    }

    @Override
    public int getMinPower(int level) {
        return 15 + (level - 1) * 9;
    }

    @Override
    public int getMaxPower(int level) {
        return super.getMinPower(level) + 50;
    }
}
