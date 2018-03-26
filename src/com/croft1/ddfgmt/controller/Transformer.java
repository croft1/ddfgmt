package com.croft1.ddfgmt.controller;

import com.croft1.ddfgmt.model.Metacard;
import com.croft1.ddfgmt.model.MetacardFactory;
import com.croft1.ddfgmt.view.ColourInterface;
import com.croft1.ddfgmt.view.Printer;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;

public class Transformer {
    private static final Printer pr = new Printer();
    private static final JSONParser parser = new JSONParser();
    private static final FileValidator fileValidator = new FileValidator();


    /**
    *
     * * Transformer class is a processing unit to get a files data and transform it into
     * a usable java format (like a JSONObject)
    *
    * @author croft1
    *
    * */
    public Transformer() {

    }

//    Take file from dir and extract many metacards from it
    public ArrayList<Metacard> performOnFileForDispatch(File file){
        if(fileValidator.fileIsJsonObject(file)) {
            JSONObject transformedFile = transformFileToJson(file);
            transformedFile.put("title", file.getName().substring(0,
                    file.getName().lastIndexOf('.')));
            return new MetacardFactory().extractMetacards(transformedFile);

        }else{
            //TODO add support for JSONArray
            pr.print("JSONArray support coming soon", ColourInterface.ANSI_WHITE);

        }

        return null;
    }

    //here we take a geo/json file and from its contents, parse it into a JSONObject
    //for ease of manipulation
    public JSONObject transformFileToJson(File file){
        try{
            Object o = parser.parse(new FileReader(file));
            JSONObject jsonObject = (JSONObject) o;

            return jsonObject;
        }catch(IOException e){
            e.printStackTrace();
            pr.print("File doesn't exist", ColourInterface.ANSI_RED);
        }catch(ParseException p){
            pr.print(file.getName() + " is not valid JSON", ColourInterface.ANSI_RED);

        }


        JSONObject j = new JSONObject();
        j.put("failure", "true");
        return j;
    }





}
