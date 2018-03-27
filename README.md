# DDF - GMT 

**Distributed Data Framework Geojson to Metacard Transformer** 
or simply
**GMT**

>The goal of GMT is to make it faster to produce valid metacard JSON or GEOJSON data to upload to an instance of DDF
>Currently you cannot upload one large file with Intrigue to create many metacards, so we need to split it into many
> in the format that is ready for DDF.


### How to run:

- Compile the project  ```javac Main.java```

###### Define data sources
1.  Inside ```/raw``` fill with geojson files you want to process 
**or**
2.  Inside ```REST_ENDPOINTS.json``` add a REST API endpoint to process json data with

###### Run project
- Run the compiled project 
``` java -jar ddfgmt.jar ```
- In the ```/transformed``` directory you'll find the new geojson files

###### DDF Integration
1. Ingest directly to your instance of DDF and get busy
2. Setup directory monitor with  



###### Rest Endpoints


In the file ```/REST_ENDPOINTS.json``` you define REST API endpoints pointing to **.geojson** data to process

```{
     "endpoints":
       [
         {
            "name":"Parking Bay Sensors",
            "link":"https://data.melbourne.vic.gov.au/resource/dtpv-d4pf.json"
         }
         {
           "name":"Melbs Bike Share Stations",
           "link": "https://data.melbourne.vic.gov.au/resource/qnjw-wgaj.geojson"
         },
         {
           "name":"Dont use a link like this - Address but not json, will fail and  -- skipped",
           "link": "https://data.melbourne.vic.gov.au/"
         },
         {
           "name":"Dont use a link like this - Not even an address, will fail and -- skipped",
           "link": "this should fail.com"
         }
       ]
   }
```

- Follow the json structure above and add a value to the endpoints array in ```REST_ENDPOINTS.json```
- Make sure you provide a valid link
- Run the script
- Valid geojson/json URL's will be looked at and processed
- It'll save the data files in ```/raw```
- Then processing will commence


=====================
###### Author - croft1 - https://github.com/croft1