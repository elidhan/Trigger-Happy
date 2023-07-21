package net.elidhan.triggerhappy.asm;

import com.chocohead.mm.api.ClassTinkerers;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.MappingResolver;

public class EarlyRiser implements Runnable
{
    @Override
    public void run()
    {
        MappingResolver remapper = FabricLoader.getInstance().getMappingResolver();
        String enchantTargetClass = remapper.mapClassName("intermediary", "net.minecraft.class_1886");
        ClassTinkerers.enumBuilder(enchantTargetClass, new Class[0]).addEnumSubclass("GUN", "net.elidhan.triggerhappy.enchantment.GunEnchantmentTarget").build();
    }
}
