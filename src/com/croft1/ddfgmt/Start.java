package com.croft1.ddfgmt;

import com.croft1.ddfgmt.controller.HttpManager;
import com.croft1.ddfgmt.model.Metacard;
import com.croft1.ddfgmt.view.ColourInterface;
import com.croft1.ddfgmt.view.Printer;
import com.croft1.ddfgmt.controller.FileManager;
import com.croft1.ddfgmt.controller.Transformer;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Start {
    FileManager fm = new FileManager();
    HttpManager httpm = new HttpManager();
    private static final Printer pr = new Printer();
    public Start(){


    }

    public void processing(){
        fetchDataAtEndpoints(); //do this first
        dataFilesInFolder();
    }

    private void dataFilesInFolder(){
        File[] files = fm.getDataFiles();
        pr.print( files.length + " files to process.");
        processFile(files);
    }

    private void fetchDataAtEndpoints() {
        ArrayList<String> restEndpoints = fm.getRestEndpointLinks();
        

    }

    private void processFile(File[] files){
        //iterate through each file in raw/
        for(int i = 0; i < files.length ; i++){
            Metacard.resetCount();
            pr.print("PROCESSING:" + files[i].getName(), ColourInterface.ANSI_CYAN);
            ArrayList<Metacard> metacardArrayList = new Transformer().performOnFileForDispatch(files[i]);

            int good = 0;
            for(int m = 0; m < metacardArrayList.size() ; m++) {
                if(fm.dispatchMetacardToFile(metacardArrayList.get(m))){
                    good++;
                }else{
                    pr.addToPrintBuffer(metacardArrayList.get(m).getTitle() + " dispatch fail.");
                }
            }
            pr.print(good + " successfully dispatched.", ColourInterface.ANSI_GREEN);
        }
    }




}
