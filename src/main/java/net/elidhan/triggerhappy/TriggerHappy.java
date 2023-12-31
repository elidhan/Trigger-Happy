package net.elidhan.triggerhappy;

import com.chocohead.mm.api.ClassTinkerers;
import net.elidhan.triggerhappy.enchantment.ModEnchantments;
import net.elidhan.triggerhappy.entity.BulletEntity;
import net.elidhan.triggerhappy.item.ModItems;
import net.elidhan.triggerhappy.sound.ModSounds;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TriggerHappy implements ModInitializer {
	public static final String MOD_ID = "triggerhappy";
	public static final EnchantmentTarget GUN = ClassTinkerers.getEnum(EnchantmentTarget.class, "GUN");
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final EntityType<BulletEntity> BulletEntityType = Registry.register(
			Registries.ENTITY_TYPE,
			new Identifier(MOD_ID, "bullet"),
			FabricEntityTypeBuilder.<BulletEntity>create(SpawnGroup.MISC, BulletEntity::new).dimensions(EntityDimensions.fixed(0.5f, 0.5f)).trackRangeBlocks(4).trackedUpdateRate(20).build());

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		ModEnchantments.registerModEnchantments();
		ModSounds.registerSounds();

		LOGGER.info("Trigger Happy initialized");
	}
}
