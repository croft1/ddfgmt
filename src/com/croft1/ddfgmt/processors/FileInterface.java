package com.croft1.ddfgmt.processors;

public interface FileInterface {
    String[] acceptedExtensions = {
            ".json",
            ".geojson"
    };
    String rawDirectory = "raw/";
    String transformedDirectory = "transformed/";
}
