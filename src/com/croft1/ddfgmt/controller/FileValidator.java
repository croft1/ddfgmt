package com.croft1.ddfgmt.controller;


import com.croft1.ddfgmt.view.Printer;
import org.json.simple.JSONObject;

import java.io.*;

/*File Validator
*
* Takes in .json or .geojson files and makes sure they have the necessary
* or valid information so that they can then be transformed into a Metacard
*
*
* */
public class FileValidator {

    private static final Printer pr = new Printer();

    public FileValidator() {

    }

    public boolean isValidForMetacard(JSONObject jsonToValidate){

        return true;
    }

    //The goal of this method is to determine is the file is
    // a whole JSONArray or a JSONObject.
    //Done by checking if the first character is a '[' or a '{'
    public boolean fileIsJsonObject(File file){
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
