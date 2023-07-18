package net.elidhan.triggerhappy.mixin.enchantment;

import com.google.common.collect.Lists;
import net.elidhan.triggerhappy.enchantment.GunEnchantment;
import net.elidhan.triggerhappy.item.GunItem;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.enchantment.UnbreakingEnchantment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.ArrayList;
import java.util.List;

@Mixin(EnchantmentHelper.class)
public class EnchantmentHelperMixin
{
    @Inject(method = "generateEnchantments", at = @At(value = "INVOKE", target = "Ljava/util/List;isEmpty()Z", ordinal = 0), locals = LocalCapture.CAPTURE_FAILHARD)
    private static void generateEnchantments(Random random, ItemStack stack, int level, boolean treasureAllowed, CallbackInfoReturnable<List<EnchantmentLevelEntry>> cir, List<EnchantmentLevelEntry> list, Item item, int i, float f, List<EnchantmentLevelEntry> list2)
    {
        List<EnchantmentLevelEntry> newEnchantments = new ArrayList<>();

        list2.forEach(enchantmentLevelEntry ->
        {
            if (enchantmentLevelEntry.enchantment instanceof GunEnchantment)
            {
                if (enchantmentLevelEntry.enchantment.isAcceptableItem(stack))
                {
                    newEnchantments.add(enchantmentLevelEntry);
                }
            }
            else
            {
                newEnchantments.add(enchantmentLevelEntry);
            }
        });

        list2.clear();
        list2.addAll(newEnchantments);
    }

    @Inject(method = "getPossibleEntries", at = @At("HEAD"), cancellable = true)
    private static void getPossibleEntries(int power, ItemStack stack, boolean treasureAllowed, CallbackInfoReturnable<List<EnchantmentLevelEntry>> cir)
    {
        Item item = stack.getItem();

        if(item instanceof GunItem)
        {
            List<EnchantmentLevelEntry> entries = Lists.newArrayList();

            Registries.ENCHANTMENT.forEach(enchantment ->
            {
                if(!(enchantment instanceof GunEnchantment) && !(enchantment instanceof UnbreakingEnchantment)) return;
                if(!enchantment.isAcceptableItem(stack)) return;
                if(!enchantment.isAvailableForRandomSelection()) return;
                if(enchantment.isTreasure() && !treasureAllowed) return;

                for (int i = enchantment.getMaxLevel(); i > enchantment.getMinLevel() - 1; i--)
                {
                    /*
                    System.out.println("Enchantment: " + enchantment.getName(i));
                    System.out.println("Power: " + power);
                    System.out.println("Max Level: " + enchantment.getMaxLevel());
                    System.out.println("Min Power: " + enchantment.getMinPower(i));
                    System.out.println("Max Power: " + enchantment.getMaxPower(i) + "\n");
                     */

                    if (power >= enchantment.getMinPower(i) && power <= enchantment.getMaxPower(i))
                    {
                        entries.add(new EnchantmentLevelEntry(enchantment, i));
                        break;
                    }
                }
            });

            cir.setReturnValue(entries);
        }
    }
}
