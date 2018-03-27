package com.croft1.ddfgmt.controller;

import com.croft1.ddfgmt.view.ColourInterface;
import com.croft1.ddfgmt.view.Printer;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

public class HttpManager {
    private static final Printer pr = new Printer();

    public JSONObject readRestJsonFromUrl(String url) throws IOException {
        pr.print("Preparing to read: " + url);
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(is, Charset.forName("UTF-8"))
            );
            String jsonText = readAll(reader);

            pr.print(jsonText.substring(0, 63), ColourInterface.ANSI_PURPLE);
            JSONObject json = (JSONObject) new JSONParser().parse(jsonText);
            return json;
        } catch (MalformedURLException mue) {
            pr.print(url + " is not a valid url -- skipped", ColourInterface.ANSI_RED);
        } catch (ParseException parseException) {
            pr.print(url + " does not point to valid json, parse failure -- skipped", ColourInterface.ANSI_RED);
        } catch (Exception e){
            pr.print("General error occured for this url -- skipped", ColourInterface.ANSI_RED);
        }
        finally{is.close();}
        return null;
    }

    private String readAll(Reader rd) throws IOException{
        StringBuilder sb = new StringBuilder();
        int cp;
        while((cp = rd.read()) != -1){
            sb.append((char) cp);
        }
        return sb.toString();
    }
}
