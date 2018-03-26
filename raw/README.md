## .geojson & .json files to transform

###### Data Files
- Place your geojson and json files you want to convert into nice metacard artifacts here in ```/raw```
- You may take one large file with hundreds of geo artifacts and they will be each split into individual metacards as standard in DDF
- Run the script
- See *On Completion*

###### Rest Endpoints

```REST_ENDPOINTS.json```

```{
     "endpoints":
       [
         ["https://data.melbourne.vic.gov.au/resource/qnjw-wgaj.geojson"],
         ["https://data.melbourne.vic.gov.au/.json"],
       ]
   }
```

- Follow the structure above and add a value to the endpoints array in ```REST_ENDPOINTS.json```
- Run the script
- Valid geojson/json URL's will be looked at and processed
- See *On Completion*

###### On Completion
Once processing is complete, you will find the resulting file/s in ```transformed```

=====================
###### Author - croft1 - https://github.com/croft1
