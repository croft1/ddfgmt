package com.croft1.ddfgmt.processors;

import com.croft1.ddfgmt.models.Metacard;
import com.croft1.ddfgmt.outputs.ColourInterface;
import com.croft1.ddfgmt.outputs.Printer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;

public class MetacardFactory {
    private static final Printer pr = new Printer();
    //this class is a handler to take in one large JSON object, that may contain an array of Features
    //or geo items that we can create into metacards.
    // It then spits out an ArrayList of Metacards ready for dispatch into files for ddf


    public MetacardFactory() {

    }


    public ArrayList<Metacard> extractMetacards(JSONObject transformedFile) {
        ArrayList<Metacard> metacards = new ArrayList<>();
        int totalFeatures = Integer.parseInt(transformedFile.get("totalFeatures").toString());
        JSONArray features = (JSONArray) transformedFile.get("features");
        for(int i = 0; i < totalFeatures; i++){
            JSONObject feature = (JSONObject) features.get(i);
            feature.put("title", transformedFile.get("title").toString());
            Metacard validMetacard = new Metacard(feature);
            pr.print(validMetacard.getTitle() + " made.", ColourInterface.ANSI_BLUE);
            metacards.add(validMetacard);
        }

        return metacards;
    }


}
