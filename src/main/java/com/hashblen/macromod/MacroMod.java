package com.hashblen.macromod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.apache.commons.io.FileUtils;
import org.lwjgl.input.Keyboard;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

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
    private List<MacroLine> lineList;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event){
        path  = event.getModConfigurationDirectory().getParent() + "/MPKMod/macros/";
        Path p = Paths.get(path);

        File configFile = new File(Loader.instance().getConfigDir(), "macromod.cfg");
        Configuration config = new Configuration(configFile);
        config.load();
        Property mName = config.get("CSV", "lastFile", "default.csv");
        macroName = mName.getString();
        if(!macroName.endsWith(".csv")){
            macroName = macroName + ".csv";
            mName.set(macroName);
            System.err.println("Last Name didn't end with .csv, so changed the name to: " + macroName);
        }


        try {
            Files.createDirectories(p);
            if(!Files.exists(Paths.get(path + macroName))){
                File folder = new File(path);
                List<File> files = (List<File>) FileUtils.listFiles(folder, new String[] {"csv"}, false);
                if(!files.isEmpty()){
                    macroName = files.get(0).getName();
                    mName.set(macroName);
                    System.out.println("macroName changed to: " + macroName);
                }
            }
            if(isEmpty(p)){
                File def = new File(path + macroName);
                boolean notAlready = def.createNewFile();
                if(notAlready){
                    System.out.print("created /MPKMod/macros/" + macroName);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(config.hasChanged())
            config.save();
    }



    public boolean isEmpty(Path path) throws IOException {
        if (Files.isDirectory(path)) {
            try (DirectoryStream<Path> directory = Files.newDirectoryStream(path)) {
                return !directory.iterator().hasNext();
            }
        }

        return false;
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {

        ClientRegistry.registerKeyBinding(start);
        ClientRegistry.registerKeyBinding(edit);

        MinecraftForge.EVENT_BUS.register(this);

        loadLines();
    }

    public void loadLines(){
        this.lineList = CSVManip.linesToMacroLines(path + macroName);
    }

    public void runTick(int index){
        if(index<0 || index>=lineList.size()){
            return;
        }
        MacroLine line = lineList.get(index);
        line.run();
    }
    private boolean isRunning = false;
    private int tick=0;

    private void endMacro(){
        Minecraft minecraft = Minecraft.getMinecraft();
        GameSettings gameSettings = minecraft.gameSettings;
        KeyBinding.setKeyBindState(gameSettings.keyBindForward.getKeyCode(), false);
        KeyBinding.setKeyBindState(gameSettings.keyBindLeft.getKeyCode(), false);
        KeyBinding.setKeyBindState(gameSettings.keyBindBack.getKeyCode(), false);
        KeyBinding.setKeyBindState(gameSettings.keyBindRight.getKeyCode(), false);
        KeyBinding.setKeyBindState(gameSettings.keyBindSprint.getKeyCode(), false);
        KeyBinding.setKeyBindState(gameSettings.keyBindSneak.getKeyCode(), false);
        KeyBinding.setKeyBindState(gameSettings.keyBindJump.getKeyCode(), false);
        KeyBinding.setKeyBindState(gameSettings.keyBindAttack.getKeyCode(), false);
        KeyBinding.setKeyBindState(gameSettings.keyBindUseItem.getKeyCode(), false);
    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent e){
        if(lineList.isEmpty()) {
            return;
        }
        if(tick>=lineList.size()){
            isRunning=false;
            endMacro();
            tick=0;
        }
        if(e.phase==TickEvent.Phase.START && isRunning){
            runTick(tick);
            tick++;
        }
    }

    @SubscribeEvent
    public void onKey(InputEvent.KeyInputEvent e){
        if(edit.isPressed()){
            mc.displayGuiScreen(new MenuGUI());
        }
        if(start.isPressed()){
            loadLines();
            if(isRunning){
                endMacro();
                tick=0;
            }
            isRunning = !isRunning;
        }
    }
}
