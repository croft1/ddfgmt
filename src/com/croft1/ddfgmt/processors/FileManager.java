package com.croft1.ddfgmt.processors;

import com.croft1.ddfgmt.models.Metacard;
import com.croft1.ddfgmt.outputs.ColourInterface;
import com.croft1.ddfgmt.outputs.Printer;

import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;

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
        String metacardFilePath = transformedDirectory + metacard.getTitle() + acceptedExtensions[1];
        file = new File (metacardFilePath);

        try {
            file.createNewFile();

            FileWriter fw = new FileWriter(metacardFilePath);
            fw.write(metacard.getJSONForDispatch());

            return true;
        }catch(IOException ioe){ioe.printStackTrace(); }

        return false;
    }
}
