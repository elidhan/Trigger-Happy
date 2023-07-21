package net.elidhan.triggerhappy.enchantment;

import net.elidhan.triggerhappy.TriggerHappy;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEnchantments
{
    public static final Enchantment LETHAL = register("lethal", new GunEnchantment(Enchantment.Rarity.COMMON));
    public static final Enchantment QUICKDRAW = register("quickdraw", new QuickdrawEnchantment(Enchantment.Rarity.UNCOMMON));
    public static final Enchantment AMMO_MANAGEMENT = register("ammo_management", new AmmoManagementEnchantment(Enchantment.Rarity.UNCOMMON));
    public static final Enchantment FORCE_OF_NATURE = register("force_of_nature", new ForceOfNatureEnchantment(Enchantment.Rarity.UNCOMMON));

    private static Enchantment register(String name, Enchantment enchantment) {
        return Registry.register(Registries.ENCHANTMENT, new Identifier(TriggerHappy.MOD_ID, name), enchantment);
    }

    public static void registerModEnchantments()
    {
        TriggerHappy.LOGGER.info("Registering ModEnchantments for " + TriggerHappy.MOD_ID);
    }
}
