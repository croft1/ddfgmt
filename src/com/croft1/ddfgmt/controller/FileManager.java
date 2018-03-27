package com.croft1.ddfgmt.controller;

import com.croft1.ddfgmt.model.Metacard;
import com.croft1.ddfgmt.view.ColourInterface;
import com.croft1.ddfgmt.view.Printer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class FileManager implements FileInterface {
    private static final Printer pr = new Printer();
    public FileManager() {

    }
    public Map<String, String> getRestEndpointLinks(){
        File restEndpointsFile = getRestEndpointsFile();
        Map<String, String> endpoints = new HashMap<String, String>();
        JSONObject json = new Transformer().transformFileToJson(restEndpointsFile);
        JSONArray jsonEndpoints = (JSONArray) json.get("endpoints");
        int totalEndpoints = jsonEndpoints.size();


        for(int i = 0; i < totalEndpoints; i++){
            JSONObject endpoint = (JSONObject) jsonEndpoints.get(i);
            pr.print("Link defined: " + endpoint.get("link").toString());
            endpoints.put(endpoint.get("name").toString(), endpoint.get("link").toString());
        }
        return endpoints;
    }

    private File getRestEndpointsFile(){
        File file = new File(rootDirectory);
        File[] matchingFiles = file.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File file, String name) {
                    return name.startsWith(restEndpointsFileName);
            }
        });
        try{
            if(matchingFiles.length != 1){
                //more than one matching file
            }
        }catch(Exception e){
            e.printStackTrace();
            pr.print("No " + restEndpointsFileName + " file in " + System.getProperty("user.dir") +", please add one or remove a duplicate" +

                    " and consult the README.md for further info", ColourInterface.ANSI_RED);
        }
        return matchingFiles[0]; //should only be one
    }

    public boolean dispatchRestDataToFile(String name, JSONObject restJson){
        File file = new File (rawDirectory);
        file.mkdir();
        String restDataFilePath = rawDirectory +
                name +
                acceptedExtensions[0]; //0-json;1-geojson; harcoded bc it doesnt matter
        file = new File (restDataFilePath);

        try(PrintStream out = new PrintStream(new FileOutputStream(restDataFilePath))) {
            file.createNewFile();
            out.print(restJson.toJSONString());

//            FileWriter fw = new FileWriter(metacardFilePath);
//            fw.write(metacard.getJSONForDispatch());

            return true;
        }catch(IOException ioe){ioe.printStackTrace(); }

        return false;
    }

    public File[] getDataFiles(){
        File file = new File(rawDirectory);
        File[] matchingFiles = file.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File file, String name) {
                if(name.startsWith("REST_ENDPOINTS")){
                    return false;
                }
                if(name.endsWith(".json") || name.endsWith(".geojson") ){
                    return true;
                }else{
                    pr.print("INVALID FILE  " + name);
                    return false;
                }
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
