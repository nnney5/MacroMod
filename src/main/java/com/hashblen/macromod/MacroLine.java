package com.hashblen.macromod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.config.GuiCheckBox;

@SuppressWarnings("FieldMayBeFinal")
public class MacroLine {

    private boolean W;
    private boolean A;
    private boolean S;
    private boolean D;
    private boolean sprint;
    private boolean sneak;
    private boolean jump;
    private float yaw;
    private float pitch;
    private boolean lmb;
    private boolean rmb;

    public boolean isW() {
        return W;
    }

    public boolean isA() {
        return A;
    }

    public boolean isS() {
        return S;
    }

    public boolean isD() {
        return D;
    }

    public boolean isSprint() {
        return sprint;
    }

    public boolean isSneak() {
        return sneak;
    }

    public boolean isJump() {
        return jump;
    }

    public float getYaw() {
        return Float.parseFloat(fieldYaw.getText());
    }

    public float getPitch() {
        return Float.parseFloat(fieldPitch.getText());
    }

    public boolean isLmb() {
        return lmb;
    }

    public boolean isRmb() {
        return rmb;
    }

    private GuiCheckBox boxW;
    private GuiCheckBox boxA;
    private GuiCheckBox boxS;
    private GuiCheckBox boxD;
    private GuiCheckBox boxSprint;
    private GuiCheckBox boxSneak;
    private GuiCheckBox boxJump;
    private GuiTextField fieldYaw;
    private GuiTextField fieldPitch;
    private GuiCheckBox boxLmb;
    private GuiCheckBox boxRmb;


    public MacroLine(boolean W, boolean A, boolean S, boolean D, boolean sprint, boolean sneak, boolean jump, float yaw, float pitch, boolean lmb, boolean rmb){
        this.W=W;
        this.A=A;
        this.S=S;
        this.D=D;
        this.sprint=sprint;
        this.sneak=sneak;
        this.jump=jump;
        this.yaw=yaw;
        this.pitch=pitch;
        this.lmb = lmb;
        this.rmb = rmb;

        this.boxW = new GuiCheckBox(1, 0, 0, "", W);
        this.boxA = new GuiCheckBox(2, 0, 0, "", A);
        this.boxS = new GuiCheckBox(3, 0, 0, "", S);
        this.boxD = new GuiCheckBox(4, 0, 0, "", D);
        this.boxSprint = new GuiCheckBox(5, 0, 0, "", sprint);
        this.boxSneak = new GuiCheckBox(6, 0, 0, "", sneak);
        this.boxJump = new GuiCheckBox(7, 0, 0, "", jump);
        this.boxLmb = new GuiCheckBox(8, 0, 0, "", lmb);
        this.boxRmb = new GuiCheckBox(9, 0, 0, "", rmb);

        Minecraft mc = Minecraft.getMinecraft();

        this.fieldYaw = new GuiTextField(8, mc.fontRendererObj, 0, 0, 45, 20);
        fieldYaw.setText(Float.toString(yaw));
        this.fieldPitch = new GuiTextField(9, mc.fontRendererObj, 0, 0, 45, 20);
        fieldPitch.setText(Float.toString(pitch));

    }

    public MacroLine(MacroLine l){
        this(l.isW(), l.isA(), l.isS(), l.isD(), l.isSprint(), l.isSneak(), l.isJump(), l.getYaw(), l.getPitch(), l.isLmb(), l.isRmb());

        this.boxW = new GuiCheckBox(1, 0, 0, "", l.isW());
        this.boxA = new GuiCheckBox(2, 0, 0, "", l.isA());
        this.boxS = new GuiCheckBox(3, 0, 0, "", l.isS());
        this.boxD = new GuiCheckBox(4, 0, 0, "", l.isD());
        this.boxSprint = new GuiCheckBox(5, 0, 0, "", l.isSprint());
        this.boxSneak = new GuiCheckBox(6, 0, 0, "", l.isSneak());
        this.boxJump = new GuiCheckBox(7, 0, 0, "", l.isJump());
        this.boxLmb = new GuiCheckBox(8, 0, 0, "", l.isLmb());
        this.boxRmb = new GuiCheckBox(9, 0, 0, "", l.isRmb());

        Minecraft mc = Minecraft.getMinecraft();

        this.fieldYaw = new GuiTextField(8, mc.fontRendererObj, 0, 0, 45, 20);
        this.yaw = l.getYaw();
        fieldYaw.setText(Float.toString(this.yaw));
        this.fieldPitch = new GuiTextField(9, mc.fontRendererObj, 0, 0, 45, 20);
        this.pitch = l.getPitch();
        fieldPitch.setText(Float.toString(this.pitch));
    }

    public MacroLine(){
        this.W=false;
        this.A=false;
        this.S=false;
        this.D=false;
        this.sprint=false;
        this.sneak=false;
        this.jump=false;
        this.yaw=0.0f;
        this.pitch=0.0f;
        this.lmb=false;
        this.rmb=false;

        this.boxW = new GuiCheckBox(1, 0, 0, "", false);
        this.boxA = new GuiCheckBox(2, 0, 0, "", false);
        this.boxS = new GuiCheckBox(3, 0, 0, "", false);
        this.boxD = new GuiCheckBox(4, 0, 0, "", false);
        this.boxSprint = new GuiCheckBox(5, 0, 0, "", false);
        this.boxSneak = new GuiCheckBox(6, 0, 0, "", false);
        this.boxJump = new GuiCheckBox(7, 0, 0, "", false);
        this.boxLmb = new GuiCheckBox(8, 0, 0, "", false);
        this.boxRmb = new GuiCheckBox(9, 0, 0, "", false);

        Minecraft mc = Minecraft.getMinecraft();

        this.fieldYaw = new GuiTextField(8, mc.fontRendererObj, 0, 0, 45, 20);
        fieldYaw.setText(Float.toString(0.0f));
        this.fieldPitch = new GuiTextField(9, mc.fontRendererObj, 0, 0, 45, 20);
        fieldPitch.setText(Float.toString(0.0f));
    }

    public void drawLine(int x, int y, int mouseX, int mouseY){

        Minecraft mc = Minecraft.getMinecraft();
        //y=y+slotIdx*20;
        x=x+10;

        this.boxW.yPosition = y;
        this.boxA.yPosition = y;
        this.boxS.yPosition = y;
        this.boxD.yPosition = y;
        this.boxSprint.yPosition = y;
        this.boxSneak.yPosition = y;
        this.boxJump.yPosition = y;
        this.fieldYaw.yPosition = y;
        this.fieldPitch.yPosition = y;
        this.boxLmb.yPosition = y;
        this.boxRmb.yPosition = y;

        this.boxW.xPosition = x;
        this.boxA.xPosition = x+20;
        this.boxS.xPosition = x+40;
        this.boxD.xPosition = x+60;
        this.boxSprint.xPosition = x+80;
        this.boxSneak.xPosition = x+110;
        this.boxJump.xPosition = x+140;
        this.fieldYaw.xPosition = x+160;
        this.fieldPitch.xPosition = x+205;
        this.boxLmb.xPosition = x+260;
        this.boxRmb.xPosition = x+280;

        this.boxW.enabled = W;
        this.boxA.enabled = A;
        this.boxS.enabled = S;
        this.boxD.enabled = D;
        this.boxSprint.enabled = sprint;
        this.boxSneak.enabled = sneak;
        this.boxJump.enabled = jump;
        //this.fieldYaw.setText(String.valueOf(yaw));
        //this.fieldPitch.setText(String.valueOf(pitch));
        this.boxLmb.enabled = lmb;
        this.boxRmb.enabled = rmb;

        this.boxW.drawButton(mc, mouseX, mouseY);
        this.boxA.drawButton(mc, mouseX, mouseY);
        this.boxS.drawButton(mc, mouseX, mouseY);
        this.boxD.drawButton(mc, mouseX, mouseY);
        this.boxSprint.drawButton(mc, mouseX, mouseY);
        this.boxSneak.drawButton(mc, mouseX, mouseY);
        this.boxJump.drawButton(mc, mouseX, mouseY);
        this.fieldYaw.drawTextBox();
        this.fieldPitch.drawTextBox();
        this.boxLmb.drawButton(mc, mouseX, mouseY);
        this.boxRmb.drawButton(mc, mouseX, mouseY);
        //System.out.println("drawLine " + slotIdx + " " + mouseX);
    }

    //doesn't work everything goes to 0.0 and over the other, everything is bugged...
    /*public void drawFields(){
        this.fieldYaw.drawTextBox();
        this.fieldPitch.drawTextBox();
    }*/

    public void initLineGui(){
        boxW.setIsChecked(W);
        boxA.setIsChecked(A);
        boxS.setIsChecked(S);
        boxD.setIsChecked(D);
        boxSprint.setIsChecked(sprint);
        boxSneak.setIsChecked(sneak);
        boxJump.setIsChecked(jump);
        fieldYaw.setText(String.valueOf(yaw));
        fieldPitch.setText(String.valueOf(pitch));
        boxLmb.setIsChecked(lmb);
        boxRmb.setIsChecked(rmb);
    }

    public void mousePressed(int x, int y, int mouseButton){
        if(this.boxW.isMouseOver()){
            W = !W;
            boxW.setIsChecked(W);
            return;
        }
        else if(this.boxA.isMouseOver()){
            A = !A;
            boxA.setIsChecked(A);
            return;
        }
        else if(this.boxS.isMouseOver()){
            S = !S;
            boxS.setIsChecked(S);
            return;
        }
        else if(this.boxD.isMouseOver()){
            D = !D;
            boxD.setIsChecked(D);
            return;
        }
        else if(this.boxSprint.isMouseOver()){
            sprint = !sprint;
            boxSprint.setIsChecked(sprint);
            return;
        }
        else if(this.boxSneak.isMouseOver()){
            sneak = !sneak;
            boxSneak.setIsChecked(sneak);
            return;
        }
        else if(this.boxJump.isMouseOver()){
            jump = !jump;
            boxJump.setIsChecked(jump);
            return;
        }
        else if(this.boxLmb.isMouseOver()){
            lmb = !lmb;
            boxLmb.setIsChecked(lmb);
            return;
        }
        else if(this.boxRmb.isMouseOver()){
            rmb = !rmb;
            boxRmb.setIsChecked(rmb);
            return;
        }
        /*
        this.boxW.mousePressed(mc, x, y);
        this.boxA.mousePressed(mc, x, y);
        this.boxS.mousePressed(mc, x, y);
        this.boxD.mousePressed(mc, x, y);
        this.boxSprint.mousePressed(mc, x, y);
        this.boxSneak.mousePressed(mc, x, y);
        this.boxJump.mousePressed(mc, x, y);*/
        this.fieldYaw.mouseClicked(x, y, mouseButton);
        this.fieldPitch.mouseClicked(x, y, mouseButton);
        //System.out.println("mousePressed " + x + " " + y);
    }

    public void drawScreen(){
        this.fieldYaw.drawTextBox();
        this.fieldPitch.drawTextBox();
    }

    public void updateScreen(){
        this.fieldYaw.updateCursorCounter();
        this.fieldPitch.updateCursorCounter();
        try {
            yaw = Float.parseFloat(fieldYaw.getText());
            pitch = Float.parseFloat(fieldPitch.getText());
        }catch(NumberFormatException ignored){

        }
        //System.out.println("updateScreen ");
    }

    public void keyTyped(char par1, int par2) {
        this.fieldYaw.textboxKeyTyped(par1, par2);
        //System.out.println("keyTyped " + par1 + " " + par2);
        this.fieldPitch.textboxKeyTyped(par1, par2);
    }

    @Override
    public String toString() {
        return "0.0,0.0,0.0,0.0,0.0," +
                yaw + "," +
                pitch + "," +
                W + "," +
                A + "," +
                S + "," +
                D + "," +
                sprint + "," +
                sneak + "," +
                jump + "," +
                lmb + "," +
                rmb + "," +
                " 0.0, 0.0, 0.0";
    }

    public void run(){
        Minecraft minecraft = Minecraft.getMinecraft();
        GameSettings gameSettings = minecraft.gameSettings;
        KeyBinding.setKeyBindState(gameSettings.keyBindForward.getKeyCode(), this.W);
        KeyBinding.setKeyBindState(gameSettings.keyBindLeft.getKeyCode(), this.A);
        KeyBinding.setKeyBindState(gameSettings.keyBindBack.getKeyCode(), this.S);
        KeyBinding.setKeyBindState(gameSettings.keyBindRight.getKeyCode(), this.D);
        KeyBinding.setKeyBindState(gameSettings.keyBindSprint.getKeyCode(), this.sprint);
        KeyBinding.setKeyBindState(gameSettings.keyBindSneak.getKeyCode(), this.sneak);
        KeyBinding.setKeyBindState(gameSettings.keyBindJump.getKeyCode(), this.jump);
        KeyBinding.setKeyBindState(gameSettings.keyBindAttack.getKeyCode(), this.lmb);
        if (this.lmb) {
            KeyBinding.onTick(gameSettings.keyBindAttack.getKeyCode());
        }
        KeyBinding.setKeyBindState(gameSettings.keyBindUseItem.getKeyCode(), this.rmb);
        if (this.rmb) {
            KeyBinding.onTick(gameSettings.keyBindUseItem.getKeyCode());
        }
        minecraft.thePlayer.rotationYaw = (float)((double)minecraft.thePlayer.rotationYaw+ this.yaw);
        minecraft.thePlayer.rotationPitch = (float)((double)minecraft.thePlayer.rotationPitch + this.pitch);
    }
}
