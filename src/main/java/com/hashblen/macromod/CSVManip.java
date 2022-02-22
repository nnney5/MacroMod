package com.hashblen.macromod;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static com.hashblen.macromod.MacroMod.macroName;
import static com.hashblen.macromod.MacroMod.path;


public class CSVManip {
    //public CSVManip(){}

    public static List<String[]> readLines(String name){
        try {
            CSVReader reader = new CSVReader(new FileReader(name));
            List<String[]> data = reader.readAll();
            reader.close();
            return data;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<String[]>();
    }

    public static List<MacroLine> linesToMacroLines(String name){
        try {
            CSVReader reader = new CSVReader(new FileReader(name));
            reader.readNext();
            String[] record;
            List<MacroLine> lines = new ArrayList<MacroLine>();
            while((record = reader.readNext()) != null){
                float y = Float.valueOf(record[5]);
                float p = Float.valueOf(record[6]);
                boolean w = Boolean.valueOf(record[7]);
                boolean a = Boolean.valueOf(record[8]);
                boolean s = Boolean.valueOf(record[9]);
                boolean d = Boolean.valueOf(record[10]);
                boolean sprint = Boolean.valueOf(record[11]);
                boolean sneak = Boolean.valueOf(record[12]);
                boolean jump = Boolean.valueOf(record[13]);
                boolean lmb = Boolean.valueOf(record[14]);
                boolean rmb = Boolean.valueOf(record[15]);
                MacroLine l = new MacroLine(w, a, s, d, sprint, sneak, jump, y, p, lmb, rmb);
                System.out.println(record.toString());
                lines.add(l);
            }
            reader.close();
            return lines;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<MacroLine>();
    }

    public static void createFile(Path p){
        try {
            Files.createFile(p);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<String[]> macroLinesToLines(List<MacroLine> l){
        List<String[]> list = new ArrayList<String[]>();
        for(MacroLine line : l){
            list.add(line.toString().split(","));
        }
        return list;
    }

    public static void writeLines(List<String[]> l, String name){
        try {
            CSVWriter writer = new CSVWriter(new FileWriter(name), CSVWriter.DEFAULT_SEPARATOR, CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);
            writer.writeNext("X,Y,Z,YAW,PITCH,ANGLE_X,ANGLE_Y,W,A,S,D,SPRINT,SNEAK,JUMP,LMB,RMB,VEL_X,VEL_Y,VEL_Z".split(","));
            writer.writeAll(l);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
