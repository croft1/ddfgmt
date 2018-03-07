package com.croft1.ddfgmt;

import com.croft1.ddfgmt.models.Metacard;
import com.croft1.ddfgmt.outputs.ColourInterface;
import com.croft1.ddfgmt.outputs.Printer;
import com.croft1.ddfgmt.processors.FileManager;
import com.croft1.ddfgmt.processors.Transformer;

import java.io.File;
import java.util.ArrayList;

public class Start {
    FileManager fm = new FileManager();
    private static final Printer pr = new Printer();
    public Start(){


    }

    public void processing(){
        File[] files = fm.getFiles();

        pr.print("raw/ directory contents. " + files.length);
        //iterate through each file in raw/
        for(int i = 0; i < files.length ; i++){
            pr.print(files[i].getName(), ColourInterface.ANSI_CYAN);

            ArrayList<Metacard> metacardArrayList = new Transformer().performForDispatch(files[i]);

            for(int m = 0; m < metacardArrayList.size() ; m++) {
                if(fm.dispatchMetacardToFile(metacardArrayList.get(m))){
                    pr.print(metacardArrayList.get(m).getTitle() + " dispatch all good.", ColourInterface.ANSI_GREEN);
                }else{
                    pr.addToPrintBuffer(metacardArrayList.get(m).getTitle() + " failed to dispatch");
                }


            }
        }
    }


}
