package com.hashblen.macromod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiTextField;
import net.minecraftforge.fml.client.config.GuiCheckBox;

import java.io.IOException;

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

    public boolean isW() {
        return W;
    }

    public void setW(boolean w) {
        W = w;
    }

    public boolean isA() {
        return A;
    }

    public void setA(boolean a) {
        A = a;
    }

    public boolean isS() {
        return S;
    }

    public void setS(boolean s) {
        S = s;
    }

    public boolean isD() {
        return D;
    }

    public void setD(boolean d) {
        D = d;
    }

    public boolean isSprint() {
        return sprint;
    }

    public void setSprint(boolean sprint) {
        this.sprint = sprint;
    }

    public boolean isSneak() {
        return sneak;
    }

    public void setSneak(boolean sneak) {
        this.sneak = sneak;
    }

    public boolean isJump() {
        return jump;
    }

    public void setJump(boolean jump) {
        this.jump = jump;
    }

    public float getYaw() {
        return yaw;
    }

    public void setYaw(float yaw) {
        this.yaw = yaw;
    }

    public float getPitch() {
        return pitch;
    }

    public void setPitch(float pitch) {
        this.pitch = pitch;
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

    Minecraft mc = Minecraft.getMinecraft();


    public MacroLine(boolean W, boolean A, boolean S, boolean D, boolean sprint, boolean sneak, boolean jump, float yaw, float pitch){
        this.W=W;
        this.A=A;
        this.S=S;
        this.D=D;
        this.sprint=sprint;
        this.sneak=sneak;
        this.jump=jump;
        this.yaw=yaw;
        this.pitch=pitch;

        this.boxW = new GuiCheckBox(1, 0, 0, "", W);
        this.boxA = new GuiCheckBox(2, 0, 0, "", A);
        this.boxS = new GuiCheckBox(3, 0, 0, "", S);
        this.boxD = new GuiCheckBox(4, 0, 0, "", D);
        this.boxSprint = new GuiCheckBox(5, 0, 0, "", sprint);
        this.boxSneak = new GuiCheckBox(6, 0, 0, "", sneak);
        this.boxJump = new GuiCheckBox(7, 0, 0, "", jump);

        this.fieldYaw = new GuiTextField(8, mc.fontRendererObj, 0, 0, 40, 20);
        fieldYaw.setText(Float.toString(yaw));
        this.fieldPitch = new GuiTextField(9, mc.fontRendererObj, 0, 0, 40, 20);
        fieldPitch.setText(Float.toString(pitch));

    }

    public MacroLine(MacroLine l){
        this(l.isW(), l.isA(), l.isS(), l.isD(), l.isSprint(), l.isSneak(), l.isJump(), l.getYaw(), l.getPitch());

        this.boxW = new GuiCheckBox(1, 0, 0, "", l.isW());
        this.boxA = new GuiCheckBox(2, 0, 0, "", l.isA());
        this.boxS = new GuiCheckBox(3, 0, 0, "", l.isS());
        this.boxD = new GuiCheckBox(4, 0, 0, "", l.isD());
        this.boxSprint = new GuiCheckBox(5, 0, 0, "", l.isSprint());
        this.boxSneak = new GuiCheckBox(6, 0, 0, "", l.isSneak());
        this.boxJump = new GuiCheckBox(7, 0, 0, "", l.isJump());

        this.fieldYaw = new GuiTextField(8, mc.fontRendererObj, 0, 0, 40, 20);
        fieldYaw.setText(Float.toString(l.getYaw()));
        this.fieldPitch = new GuiTextField(9, mc.fontRendererObj, 0, 0, 40, 20);
        fieldPitch.setText(Float.toString(l.getPitch()));
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

        this.boxW = new GuiCheckBox(1, 0, 0, "", false);
        this.boxA = new GuiCheckBox(2, 0, 0, "", false);
        this.boxS = new GuiCheckBox(3, 0, 0, "", false);
        this.boxD = new GuiCheckBox(4, 0, 0, "", false);
        this.boxSprint = new GuiCheckBox(5, 0, 0, "", false);
        this.boxSneak = new GuiCheckBox(6, 0, 0, "", false);
        this.boxJump = new GuiCheckBox(7, 0, 0, "", false);

        this.fieldYaw = new GuiTextField(8, mc.fontRendererObj, 0, 0, 40, 20);
        fieldYaw.setText(Float.toString(0.0f));
        this.fieldPitch = new GuiTextField(9, mc.fontRendererObj, 0, 0, 40, 20);
        fieldPitch.setText(Float.toString(0.0f));
    }

    public MacroLine(int lineNumber){
        this.W=false;
        this.A=false;
        this.S=false;
        this.D=false;
        this.sprint=false;
        this.sneak=false;
        this.jump=false;
        this.yaw=0.0f;
        this.pitch=0.0f;

        this.boxW = new GuiCheckBox(1, 0, 0, "", false);
        this.boxA = new GuiCheckBox(2, 0, 0, "", false);
        this.boxS = new GuiCheckBox(3, 0, 0, "", false);
        this.boxD = new GuiCheckBox(4, 0, 0, "", false);
        this.boxSprint = new GuiCheckBox(5, 0, 0, "", false);
        this.boxSneak = new GuiCheckBox(6, 0, 0, "", false);
        this.boxJump = new GuiCheckBox(7, 0, 0, "", false);

        this.fieldYaw = new GuiTextField(8, mc.fontRendererObj, 0, 0, 40, 20);
        fieldYaw.setText(Float.toString(0.0f));
        this.fieldPitch = new GuiTextField(9, mc.fontRendererObj, 0, 0, 40, 20);
        fieldPitch.setText(Float.toString(0.0f));
    }

    public void drawLine(int slotIdx, int x, int y, int slotHeight, int mouseX, int mouseY, boolean isSelected){

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

        this.boxW.xPosition = x;
        this.boxA.xPosition = x+20;
        this.boxS.xPosition = x+40;
        this.boxD.xPosition = x+60;
        this.boxSprint.xPosition = x+80;
        this.boxSneak.xPosition = x+100;
        this.boxJump.xPosition = x+120;
        this.fieldYaw.xPosition = x+140;
        this.fieldPitch.xPosition = x+180;

        this.boxW.enabled = W;
        this.boxA.enabled = A;
        this.boxS.enabled = S;
        this.boxD.enabled = D;
        this.boxSprint.enabled = sprint;
        this.boxSneak.enabled = sneak;
        this.boxJump.enabled = jump;
        this.fieldYaw.setText(String.valueOf(yaw));
        this.fieldPitch.setText(String.valueOf(pitch));

        this.boxW.drawButton(mc, mouseX, mouseY);
        this.boxA.drawButton(mc, mouseX, mouseY);
        this.boxS.drawButton(mc, mouseX, mouseY);
        this.boxD.drawButton(mc, mouseX, mouseY);
        this.boxSprint.drawButton(mc, mouseX, mouseY);
        this.boxSneak.drawButton(mc, mouseX, mouseY);
        this.boxJump.drawButton(mc, mouseX, mouseY);
        this.fieldYaw.drawTextBox();
        this.fieldPitch.drawTextBox();
        //System.out.println("drawLine " + slotIdx + " " + mouseX);
    }

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
    }

    public boolean mousePressed(int slotIdx, int x, int y, int mouseButton){
        if(this.boxW.isMouseOver()){
            W = !W;
            boxW.setIsChecked(W);
            return true;
        }
        else if(this.boxA.isMouseOver()){
            A = !A;
            boxA.setIsChecked(A);
            return true;
        }
        else if(this.boxS.isMouseOver()){
            S = !S;
            boxS.setIsChecked(S);
            return true;
        }
        else if(this.boxD.isMouseOver()){
            D = !D;
            boxD.setIsChecked(D);
            return true;
        }
        else if(this.boxSprint.isMouseOver()){
            sprint = !sprint;
            boxSprint.setIsChecked(sprint);
            return true;
        }
        else if(this.boxSneak.isMouseOver()){
            sneak = !sneak;
            boxSneak.setIsChecked(sneak);
            return true;
        }
        else if(this.boxJump.isMouseOver()){
            jump = !jump;
            boxJump.setIsChecked(jump);
            return true;
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
        return false;
    }

    public void updateScreen(){
        this.fieldYaw.updateCursorCounter();
        this.fieldPitch.updateCursorCounter();
        //System.out.println("updateScreen ");
    }

    public void keyTyped(char par1, int par2) throws IOException {
        this.fieldYaw.textboxKeyTyped(par1, par2);
        //System.out.println("keyTyped " + par1 + " " + par2);
        this.fieldPitch.textboxKeyTyped(par1, par2);
    }

}
