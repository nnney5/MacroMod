package com.hashblen.macromod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.renderer.Tessellator;
import net.minecraftforge.fml.client.GuiScrollingList;

import java.io.IOException;
import java.util.ArrayList;

public class MacroFileGUI extends GuiScrollingList {
    private ArrayList<MacroLine> lines;
    private MenuGUI parent;

    private ArrayList<GuiTextField> counterFields;

    private Minecraft mc = Minecraft.getMinecraft();

    public MacroFileGUI(MenuGUI parent, ArrayList<MacroLine> lines, int listWidth) {
        super(parent.getMinecraftInstance(), listWidth, parent.height, 32, parent.height - 88 + 4, 10, 20, parent.width, parent.height);
        this.parent = parent;
        this.lines = lines;

        counterFields = new ArrayList<GuiTextField>();
        for(int i=0; i<lines.size(); i++){
            GuiTextField g = new GuiTextField(i, mc.fontRendererObj, this.left, top, 25, 20);
            g.setText(Integer.toString(i));
            counterFields.add(g);
        }
    }

    @Override
    protected int getSize() {
        return lines.size();
    }

    @Override
    protected void elementClicked(int index, boolean doubleClick) {
        this.parent.selectBoxIndex(index);
    }

    @Override
    protected boolean isSelected(int index) {
        return this.parent.boxIndexSelected(index);
    }

    @Override
    protected void drawBackground() {
        //this.parent.drawDefaultBackground();
    }

    @Override
    protected int getContentHeight()
    {
        return (this.getSize()) * 20 + 1;
    }

    ArrayList<MacroLine> getLines(){ return lines; }

    @Override
    protected void drawSlot(int slotIdx, int entryRight, int slotTop, int slotBuffer, Tessellator tess) {

        MacroLine line = lines.get(slotIdx);

        line.drawLine(slotIdx, this.left+30, slotTop, slotBuffer, mouseX, mouseY, isSelected(slotIdx));
        GuiTextField cField = counterFields.get(slotIdx);
        cField.xPosition = this.left+5;
        cField.yPosition = slotTop;
        cField.drawTextBox();

    }

    public void mouseClicked(int mouseX, int mouseY, int mouseButton){
        int i=0;
        for( MacroLine l : lines){
            l.mousePressed(i, mouseX, mouseY, mouseButton);
            i++;
        }
    }

    public void updateScreen(){
        int i=0;
        for( MacroLine l : lines){
            l.updateScreen();
            i++;
        }
    }

    public void keyTyped(char par1, int par2) throws IOException {
        int i=0;
        for( MacroLine l : lines){
            l.keyTyped(par1, par2);
            i++;
        }
    }

}
