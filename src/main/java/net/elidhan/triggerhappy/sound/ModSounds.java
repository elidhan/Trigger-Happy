package net.elidhan.triggerhappy.sound;

import net.elidhan.triggerhappy.TriggerHappy;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds
{
    public static SoundEvent HANDGUN = registerSoundEvent("handgun");
    public static SoundEvent SHOTGUN = registerSoundEvent("shotgun");
    public static SoundEvent RIFLE = registerSoundEvent("rifle");
    public static SoundEvent LMG = registerSoundEvent("lmg");
    private static SoundEvent registerSoundEvent(String name)
    {
        Identifier id = new Identifier(TriggerHappy.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static void registerSounds()
    {
        TriggerHappy.LOGGER.info("Registering ModSounds for " + TriggerHappy.MOD_ID);
    }
}
