package com.croft1.ddfgmt.outputs;

public class Printer implements ColourInterface {
    public static String currentTextColour = ANSI_BLACK;
    public static String currentBackgroundColour = ANSI_BLACK_BACKGROUND;
    public static boolean isSticky = false;
    private static String printBuffer = "";
    public Printer() {
        isSticky = false;
    }
    public void addToPrintBuffer(String output){
        printBuffer += output;
    }
    public void print(String output){
        System.out.println(printBuffer + currentTextColour + output);
        if(isSticky){reset();}
    }
    public void print(String output, String newTextColour){
        currentTextColour = newTextColour;
        System.out.println(printBuffer + currentTextColour + output);
        if(!isSticky){reset();}
    }
    public void print(String output, String newTextColour, String newBgColour){
        currentTextColour = newTextColour;
        currentBackgroundColour = newBgColour;
        System.out.println(printBuffer + currentBackgroundColour + currentTextColour + output);
        if(!isSticky){reset();}
    }

    private void reset(){
           currentTextColour = ANSI_BLACK;
           currentBackgroundColour = ANSI_BLACK_BACKGROUND;
    }

    //sets the assignment of colors sticky.
    //Colours dont reset back to black after each time, but stay set until changed again
    public void setSticky(boolean is){
        isSticky = is;
    }

}
