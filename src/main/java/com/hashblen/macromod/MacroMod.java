package com.hashblen.macromod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.init.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Mod(modid = MacroMod.MODID, version = MacroMod.VERSION)
public class MacroMod
{
    public static final String MODID = "macromod";
    public static final String VERSION = "1.0";

    public static KeyBinding start = new KeyBinding("Start Macro", Keyboard.KEY_O, "Macro Mod");
    public static KeyBinding edit = new KeyBinding("Edit Macro", Keyboard.KEY_P, "Macro Mod");
    public static Minecraft mc = Minecraft.getMinecraft();
    public static String path = "";
    public static String macroName = "default.csv";

    @EventHandler
    public void preInit(FMLPreInitializationEvent event){
        path  = event.getModConfigurationDirectory().getParent() + "/MPKMod/macros/";
        Path p = Paths.get(path);
        try {
            Files.createDirectories(p);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {

        ClientRegistry.registerKeyBinding(start);
        ClientRegistry.registerKeyBinding(edit);

        MinecraftForge.EVENT_BUS.register(this);
    }

    public void runMacro(){

    }

    @SubscribeEvent
    public void onKey(InputEvent.KeyInputEvent e){
        if(edit.isPressed()){
            mc.displayGuiScreen(new MenuGUI());
        }
        if(start.isPressed()){
            runMacro();
        }
    }
}
