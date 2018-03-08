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
    public ArrayList<Metacard> performForDispatch(File file){
        if(fileIsJsonObject(file)) {
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
    private JSONObject transformFileToJson(File file){
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


    //The goal of this method is to determine is the file is
    // a whole JSONArray or a JSONObject.
    //Done by checking if the first character is a '[' or a '{'
    private boolean fileIsJsonObject(File file){
        try {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(file),
                            "UTF-8"));
            String line = in.readLine();
            if(line!=null && line.length() >5)
                line = line.substring(0, 1);
            if(line.equals("[")){return false;} else {return true;}

        }catch(FileNotFoundException ioe){
            ioe.printStackTrace();
            pr.print("file doesnt exist fail");
        }catch(Exception e) {
            e.printStackTrace();
            pr.print("fileJsonArrayOrObject fail");
        }

        return false;
    }


}
