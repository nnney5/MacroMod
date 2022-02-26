package com.hashblen.macromod;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;


@SuppressWarnings("Convert2Diamond")
public class CSVManip {
    //public CSVManip(){}

    /*public static List<String[]> readLines(String name){
        try {
            CSVReader reader = new CSVReader(new FileReader(name));
            List<String[]> data = reader.readAll();
            reader.close();
            return data;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<String[]>();
    }*/

    public static List<MacroLine> linesToMacroLines(String name){
        try {
            CSVReader reader = new CSVReader(new FileReader(name));
            String[] firstLine = reader.readNext();
            if(!firstLine[0].equals("X")){
                System.err.println("file has wrong head, may start reading from 2nd line of the macro.");
            }
            String[] record;
            List<MacroLine> lines = new ArrayList<MacroLine>();
            while((record = reader.readNext()) != null){
                float y = Float.parseFloat(record[5]);
                float p = Float.parseFloat(record[6]);
                boolean w = Boolean.parseBoolean(record[7]);
                boolean a = Boolean.parseBoolean(record[8]);
                boolean s = Boolean.parseBoolean(record[9]);
                boolean d = Boolean.parseBoolean(record[10]);
                boolean sprint = Boolean.parseBoolean(record[11]);
                boolean sneak = Boolean.parseBoolean(record[12]);
                boolean jump = Boolean.parseBoolean(record[13]);
                boolean lmb = Boolean.parseBoolean(record[14]);
                boolean rmb = Boolean.parseBoolean(record[15]);
                MacroLine l = new MacroLine(w, a, s, d, sprint, sneak, jump, y, p, lmb, rmb);
                //System.out.println(Arrays.toString(record));
                lines.add(l);
            }
            reader.close();
            return lines;
        } catch (Exception e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }
        return new ArrayList<MacroLine>();
    }

    public static void createFile(Path p){
        try {
            if(!Files.exists(p)) {
                Files.createFile(p);
                CSVWriter writer = new CSVWriter(new FileWriter(String.valueOf(p)), CSVWriter.DEFAULT_SEPARATOR, CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);
                writer.writeNext("X,Y,Z,YAW,PITCH,ANGLE_X,ANGLE_Y,W,A,S,D,SPRINT,SNEAK,JUMP,LMB,RMB,VEL_X,VEL_Y,VEL_Z".split(","));
                writer.close();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage() + " already exists.");
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
