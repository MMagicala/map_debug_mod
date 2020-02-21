package map_debug_mod;

import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;

@SpireInitializer
public class MapDebugMod{
    public MapDebugMod() {
        System.out.println("Map Debug Mod initialized! -Mysterio's Magical Assistant");
    }

    public static void initialize() {
        new MapDebugMod();
    }
}