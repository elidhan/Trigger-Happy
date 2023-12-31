package net.elidhan.triggerhappy.item;

import net.elidhan.triggerhappy.TriggerHappy;
import net.elidhan.triggerhappy.sound.ModSounds;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ToolMaterials;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems
{
    public static final Item IRON_HANDGUN = registerItem("iron_handgun", new GunItem(new FabricItemSettings().maxDamage(256),
            4,
            16,
            1,
            1,
            1,
            ToolMaterials.IRON,
            ModSounds.HANDGUN,
            true));
    public static final Item GOLDEN_HANDGUN = registerItem("golden_handgun", new GunItem(new FabricItemSettings().maxDamage(256),
            4,
            12,
            1,
            0.5f,
            1,
            ToolMaterials.IRON,
            ModSounds.HANDGUN,
            true));
    public static final Item DIAMOND_SNIPER_RIFLE = registerItem("diamond_sniper_rifle", new GunItem(new FabricItemSettings().maxDamage(1024),
            8,
            24,
            1,
            0.125f,
            1.35f,
            ToolMaterials.DIAMOND,
            ModSounds.RIFLE,
            false));
    public static final Item DIAMOND_SHOTGUN = registerItem("diamond_shotgun", new GunItem(new FabricItemSettings().maxDamage(1024),
            0.5f,
            24,
            5,
            7.5f,
            1,
            ToolMaterials.DIAMOND,
            ModSounds.SHOTGUN,
            false));
    public static final Item DIAMOND_LMG = registerItem("diamond_lmg", new GunItem(new FabricItemSettings().maxDamage(1024),
            4,
            4,
            1,
            2,
            1,
            ToolMaterials.DIAMOND,
            ModSounds.LMG,
            false));
    public static final Item NETHERITE_SNIPER_RIFLE = registerItem("netherite_sniper_rifle", new GunItem(new FabricItemSettings().maxDamage(2048),
            8,
            20,
            1,
            0,
            1.8f,
            ToolMaterials.NETHERITE,
            ModSounds.RIFLE,
            false));
    public static final Item IRON_BULLET = registerItem("iron_bullet", new BulletItem(new FabricItemSettings().maxCount(64),
            1.5f,
            4,
            false));
    public static final Item HIGH_VELOCITY_BULLET = registerItem("high_velocity_bullet", new BulletItem(new FabricItemSettings().maxCount(64),
            2.5f,
            10,
            false));
    public static final Item EXPLOSIVE_BULLET = registerItem("explosive_bullet", new BulletItem(new FabricItemSettings().maxCount(64),
            1.5f,
            4,
            true));

    private static Item registerItem(String name, Item item)
    {
        return Registry.register(Registries.ITEM, new Identifier(TriggerHappy.MOD_ID, name), item);
    }

    public static void registerModItems()
    {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries ->
                {
                    entries.add(IRON_HANDGUN);
                    entries.add(GOLDEN_HANDGUN);
                    entries.add(DIAMOND_SNIPER_RIFLE);
                    entries.add(DIAMOND_SHOTGUN);
                    entries.add(DIAMOND_LMG);
                    entries.add(NETHERITE_SNIPER_RIFLE);
                    entries.add(IRON_BULLET);
                    entries.add(HIGH_VELOCITY_BULLET);
                    entries.add(EXPLOSIVE_BULLET);
                }
        );

        TriggerHappy.LOGGER.info("Registering ModItems for " + TriggerHappy.MOD_ID);
    }
}
