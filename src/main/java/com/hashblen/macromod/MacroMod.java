package com.hashblen.macromod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.apache.commons.io.FileUtils;
import org.lwjgl.input.Keyboard;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
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
    public static KeyBinding record = new KeyBinding("Record Macro", Keyboard.KEY_I, "Macro Mod");
    public static Minecraft mc = Minecraft.getMinecraft();
    public static String path = "";
    public static String macroName = "default.csv";
    public static boolean srv = false;
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

        Property mSrv = config.get("Test", "srv", false);
        srv = mSrv.getBoolean();

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
        ClientRegistry.registerKeyBinding(record);

        MinecraftForge.EVENT_BUS.register(this);

        loadLines();
    }

    public void loadLines(){
        this.lineList = CSVManip.linesToMacroLines(path + macroName);
    }
    public void saveLines(){
        CSVManip.createFile(Paths.get(path + macroName));
        CSVManip.writeLines(CSVManip.macroLinesToLines(lineList), path + macroName);
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
        GameSettings gameSettings = mc.gameSettings;
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

    public static float round(float d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }
    private boolean using=false;
    private int tickCount=0;
    @SubscribeEvent
    public void onUse(PlayerInteractEvent event){
        if(!using && tickCount>0 && ( event.action==PlayerInteractEvent.Action.RIGHT_CLICK_AIR ||event.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK )) {
            using = true;
            //mc.thePlayer.addChatMessage(new ChatComponentText("UseItem, " + tickCount + " " + (tickCount != 0)));
            tickCount=0;

        }/*else if(event.action== PlayerInteractEvent.Action.LEFT_CLICK_BLOCK){)*/
    }

    private float lastYaw=0;
    private float lastPitch=0;
    private MacroLine inputLine(){
        Minecraft minecraft = Minecraft.getMinecraft();
        GameSettings gameSettings = minecraft.gameSettings;
        return new MacroLine(
                gameSettings.keyBindForward.isKeyDown(),
                gameSettings.keyBindLeft.isKeyDown(),
                gameSettings.keyBindBack.isKeyDown(),
                gameSettings.keyBindRight.isKeyDown(),
                mc.thePlayer.isSprinting(),
                gameSettings.keyBindSneak.isKeyDown(),
                gameSettings.keyBindJump.isKeyDown(),
                round(minecraft.thePlayer.rotationYaw-lastYaw,2),
                round(minecraft.thePlayer.rotationPitch-lastPitch,2),
                gameSettings.keyBindAttack.isKeyDown(),
                using
        );
    }

    private boolean isRecording=false;

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent e){
        if(lineList==null) {
            return;
        }
        if(isRunning && tick>=lineList.size() && !lineList.isEmpty()){
            isRunning=false;
            endMacro();
            tick=0;
            mc.thePlayer.addChatMessage(new ChatComponentText("\247cMacroMod: \247rStopped macro " + macroName));
        }
        if(e.phase==TickEvent.Phase.END ){
            if(isRunning && !lineList.isEmpty()) {
                runTick(tick);
                tick++;
            }
            if(isRecording){
                lineList.add(inputLine());
            }
            using=false;
            try {
                lastYaw = mc.thePlayer.rotationYaw;
                lastPitch = mc.thePlayer.rotationPitch;
            }catch (NullPointerException error){
                System.out.println("Getting Yaw or Pitch threw a NullPointerException");
            }
        }
        if(e.phase==TickEvent.Phase.START){
            tickCount++;
        }



    }

    @SubscribeEvent
    public void onKey(InputEvent.KeyInputEvent e){
        if(edit.isPressed()){
            if(isRecording){
                mc.thePlayer.addChatMessage(new ChatComponentText("\247cMacroMod: \247rStop the recording to be able to open the GUI"));
                return;
            }
            if(isRunning && !lineList.isEmpty()){
                mc.thePlayer.addChatMessage(new ChatComponentText("\247cMacroMod: \247rStop the playback to be able to open the GUI"));
                return;
            }
            mc.displayGuiScreen(new MenuGUI());
        }
        if(start.isPressed()){
            if(isRecording){
                mc.thePlayer.addChatMessage(new ChatComponentText("\247cMacroMod: \247rCan't playback when recording!"));
                return;
            }
            if(mc.currentScreen!=null){
                mc.thePlayer.addChatMessage(new ChatComponentText("\247cMacroMod: \247rCan't playback in GUIs yet"));
                return;
            }
            loadLines();
            if(lineList.isEmpty()){
                mc.thePlayer.addChatMessage(new ChatComponentText("\247cMacroMod: \247rMacro " + macroName + " is empty"));
                return;
            }
            if(isRunning){
                endMacro();
                tick=0;
                mc.thePlayer.addChatMessage(new ChatComponentText("\247cMacroMod: \247rStopped " + macroName));
            }else{
                mc.thePlayer.addChatMessage(new ChatComponentText("\247cMacroMod: \247rStarted " + macroName));
            }
            isRunning = !isRunning;
        }
        if(record.isPressed()){
            if(mc.currentScreen!=null){
                mc.thePlayer.addChatMessage(new ChatComponentText("\247cMacroMod: \247rCan't record in GUIs yet"));
                return;
            }
            if(!isRecording && (!isRunning || lineList.isEmpty())){
                loadLines();
                mc.thePlayer.addChatMessage(new ChatComponentText("\247cMacroMod: \247rRecording started!"));
                isRecording=true;
            }else if(!isRecording){
                mc.thePlayer.addChatMessage(new ChatComponentText("\247cMacroMod: \247rCan't record when playing!"));
            }
            else{
                saveLines();
                mc.thePlayer.addChatMessage(new ChatComponentText("\247cMacroMod: \247rRecording stopped!"));
                isRecording=false;
            }
        }
    }
}
