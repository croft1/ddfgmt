package com.croft1.ddfgmt.model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Calendar;
import java.util.Iterator;

public class Metacard implements MetacardInterface {

    String id_value = "";
    String type_value = "";
    String geometry_name_value = "";
    JSONObject properties = new JSONObject();
    JSONObject geometry = new JSONObject();
    JSONObject base_info = new JSONObject();

    public static int count = 0;


    public Metacard(JSONObject jsonObject){
        properties = (JSONObject) jsonObject.get("properties");
        geometry = (JSONObject) jsonObject.get("geometry");

        properties.put(title_name, jsonObject.get("title").toString() + "-" + getCountForTitle());
        properties.put(thumbnail_name, "CA==");
        properties.put(resourceUri_name, "");
        properties.put(created_name, Calendar.getInstance().getTime().toString());
        properties.put(metadataContentTypeVersion_name, "1");
        properties.put(metadataContentType_name, "ddf.metacard");
        properties.put(metadata_name, "<xml>All Metadata</xml>");
        properties.put(modified_name, Calendar.getInstance().getTime().toString());


        for(Iterator<?> keys = jsonObject.keySet().iterator(); keys.hasNext();){
            String key = (String) keys.next();
            if(key.equals(geometry_name) || key.equals(properties_name)){
                //only want extraneous data
            }else {
                base_info.put(key, jsonObject.get(key));
            }
        }
        int i;

    }

    public Metacard(JSONArray jsonArray){
        //TODO add JSONARRAY parsing Support
    }


    public String getJSONForDispatch(){
        JSONObject metacard = base_info;
        metacard.put(properties_name, properties);
        metacard.put(geometry_name, geometry);
        metacard.put(type_name, type_value);

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

    public static void resetCount(){count = 0;}
}
