package com.croft1.ddfgmt.view;

public class Printer implements ColourInterface {
    public static String currentTextColour = ANSI_BLACK;
    public static String currentBackgroundColour = ANSI_BLACK_BACKGROUND;
    public static boolean isSticky = false;
    private static String printBuffer = "";


    /*******************************
     *
     * Printer class is used as a controller between the processing layer and the view layer
     * Since at the time of this, GMT is only terminal based, we just use SYSO
     * view, but in the future, you would pass this onto a view to
     * make use of it
     *
     *
     * @author croft1
     * @version 1
     */
    public Printer() {
        isSticky = false;
    }
    public void addToPrintBuffer(String output){
        printBuffer += output;
    }
    public String usePrintBuffer(){String out = printBuffer; printBuffer = ""; return out;}
    public void print(String output){
        System.out.println(usePrintBuffer() + currentTextColour + output);
        if(isSticky){reset();}
    }
    public void print(String output, String newTextColour){
        currentTextColour = newTextColour;
        System.out.println(usePrintBuffer() + currentTextColour + output);
        if(!isSticky){reset();}
    }
    public void print(String output, String newTextColour, String newBgColour){
        currentTextColour = newTextColour;
        currentBackgroundColour = newBgColour;
        System.out.println(usePrintBuffer() + currentBackgroundColour + currentTextColour + output);
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
