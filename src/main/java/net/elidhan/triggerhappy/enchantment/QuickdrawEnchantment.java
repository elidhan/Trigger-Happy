package net.elidhan.triggerhappy.enchantment;

public class QuickdrawEnchantment extends GunEnchantment
{
    protected QuickdrawEnchantment(Rarity rarity)
    {
        super(rarity);
    }

    @Override
    public int getMinPower(int level)
    {
        return 20;
    }

    @Override
    public int getMaxPower(int level)
    {
        return 50;
    }

    @Override
    public int getMaxLevel()
    {
        return 1;
    }
}
