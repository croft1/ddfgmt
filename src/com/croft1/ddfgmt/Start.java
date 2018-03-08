package com.croft1.ddfgmt;

import com.croft1.ddfgmt.model.Metacard;
import com.croft1.ddfgmt.view.ColourInterface;
import com.croft1.ddfgmt.view.Printer;
import com.croft1.ddfgmt.controller.FileManager;
import com.croft1.ddfgmt.controller.Transformer;

import java.io.File;
import java.util.ArrayList;

public class Start {
    FileManager fm = new FileManager();
    private static final Printer pr = new Printer();
    public Start(){


    }

    public void processing(){
        File[] files = fm.getFiles();

        pr.print( files.length + " files to process.");
        //iterate through each file in raw/
        for(int i = 0; i < files.length ; i++){
            Metacard.resetCount();
            pr.print("PROCESSING:" + files[i].getName(), ColourInterface.ANSI_CYAN);

            ArrayList<Metacard> metacardArrayList = new Transformer().performForDispatch(files[i]);

            for(int m = 0; m < metacardArrayList.size() ; m++) {
                if(fm.dispatchMetacardToFile(metacardArrayList.get(m))){
                    pr.print(metacardArrayList.get(m).getTitle() + " dispatch good.", ColourInterface.ANSI_GREEN);
                }else{
                    pr.addToPrintBuffer(metacardArrayList.get(m).getTitle() + " dispatch fail.");
                }


            }
        }
    }


}
