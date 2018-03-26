# DDF - GMT 

**Distributed Data Framework Geojson to Metacard Transformer** 
or simply
**GMT**

>The goal of GMT is to make it faster to produce valid metacard JSON or GEOJSON data to upload to an instance of DDF
>Currently you cannot upload one large file with Intrigue to create many metacards, so we need to split it into many
> in the format that is ready for DDF.


### How to run:

- Compile the project 
- Create directory called ```/raw``` and fill with geojson you want to put in ddf
- Run the compiled project 
``` java -jar ddfgmt.jar ```
- In the ```/transformed``` directory you'll find the new geojson files
- Upload to your instance of DDF and get busy 



###### Rest Endpoints


```REST_ENDPOINTS.json```

```{
     "endpoints":
       [
         {
           "name":"Melbs Bike Share Stations",
           "link": "https://data.melbourne.vic.gov.au/resource/qnjw-wgaj.geojson"
         },
         {
           "name":"Address but not json",
           "link": "https://data.melbourne.vic.gov.au/"
         },
         {
           "name":"Not even an address",
           "link": "this should fail.com"
         }
       ]
   }
```

- Follow the structure above and add a value to the endpoints array in ```REST_ENDPOINTS.json```
- Run the script
- Valid geojson/json URL's will be looked at and processed
- It'll save the data files in ```/raw```
- Then processing will commence


=====================
###### Author - croft1 - https://github.com/croft1