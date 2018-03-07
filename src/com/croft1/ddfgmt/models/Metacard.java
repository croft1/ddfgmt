package com.croft1.ddfgmt.models;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Calendar;

public class Metacard implements MetacardInterface {

    String id = "";
    String type = "";
    String geometry_name = "";
    JSONObject properties = new JSONObject();
    JSONObject geometry = new JSONObject();
    public static int count = 0;


    public Metacard(JSONObject jsonObject){
        properties = (JSONObject) jsonObject.get("properties");
        properties.put(title_name, jsonObject.get("title").toString() + "-" + getCountForTitle());
        properties.put(thumbnail_name, "CA==");
        properties.put(resourceUri_name, "");
        properties.put(created_name, Calendar.getInstance().getTime().toString());
        properties.put(metadataContentTypeVersion_name, "1");
        properties.put(metadataContentType_name, "ddf.metacard");
        properties.put(metadata_name, "<xml>All Metadata</xml>");
        properties.put(modified_name, Calendar.getInstance().getTime().toString());

        geometry = (JSONObject) jsonObject.get("geometry");

        id = jsonObject.get(id_name).toString();
        type = jsonObject.get(type_name).toString();
        geometry_name = jsonObject.get(geometry_name_name).toString();
    }

    public Metacard(JSONArray jsonArray){
        //TODO add JSONARRAY parsing Support
    }


    public String getJSONForDispatch(){
        JSONObject metacard = new JSONObject();
        metacard.put(properties_name, properties);
        metacard.put(geometry_name, geometry);
        metacard.put(geometry_name_name, geometry_name);
        metacard.put(type_name, type);
        metacard.put(id_name, id);

        return metacard.toJSONString();
    }

    private int getCountForTitle(){count++; return count;}

    public String getTitle(){return properties.get("title").toString(); }

    @Override
    public String toString() {
        return
                properties.toString() +
                "\n" + geometry.toString();
    }
}
