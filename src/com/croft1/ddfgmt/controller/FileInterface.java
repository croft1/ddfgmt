package com.croft1.ddfgmt.controller;

public interface FileInterface {
    String[] acceptedExtensions = {
            ".json",
            ".geojson"
    };
    String rawDirectory = "raw/";
    String transformedDirectory = "transformed/";
}
