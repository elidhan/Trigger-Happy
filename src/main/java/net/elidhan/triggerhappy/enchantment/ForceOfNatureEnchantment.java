package net.elidhan.triggerhappy.enchantment;

public class ForceOfNatureEnchantment extends GunEnchantment
{
    protected ForceOfNatureEnchantment(Rarity rarity)
    {
        super(rarity);
    }

    @Override
    public int getMinPower(int level) {
        return 12 + (level - 1) * 20;
    }

    @Override
    public int getMaxPower(int level) {
        return this.getMinPower(level) + 25;
    }

    @Override
    public int getMaxLevel()
    {
        return 2;
    }
}
