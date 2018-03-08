package com.croft1.ddfgmt.controller;

import com.croft1.ddfgmt.model.Metacard;
import com.croft1.ddfgmt.view.ColourInterface;
import com.croft1.ddfgmt.view.Printer;

import java.io.*;

public class FileManager implements FileInterface {
    private static final Printer pr = new Printer();
    public FileManager() {

    }
    public File[] getFiles(){
        File file = new File(rawDirectory);
        File[] matchingFiles = file.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File file, String name) {

                return name.endsWith(".json") || name.endsWith(".geojson");
            }
        });
        try{
            if(matchingFiles.length < 1){
                //no files in directory
            }
        }catch(Exception e){
            e.printStackTrace();
            pr.print("No files ", ColourInterface.ANSI_RED);
        }
        return matchingFiles;
    }

    public boolean dispatchMetacardToFile(Metacard metacard){
        //TODO from metacard object, build the file for saving
        File file = new File (transformedDirectory);
        file.mkdir();
        String metacardFilePath = transformedDirectory +
                metacard.getTitle() +
                acceptedExtensions[0]; //0-json;1-geojson; harcoded bc it doesnt matter
        file = new File (metacardFilePath);

        try(PrintStream out = new PrintStream(new FileOutputStream(metacardFilePath))) {
            file.createNewFile();
            out.print(metacard.getJSONForDispatch());


//            FileWriter fw = new FileWriter(metacardFilePath);
//            fw.write(metacard.getJSONForDispatch());

            return true;
        }catch(IOException ioe){ioe.printStackTrace(); }

        return false;
    }
}
