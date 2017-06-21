package com.gap.atpractice.utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Key on 5/30/2017.
 */
public class JsonUtils {

    /**
     * Read a JSON file from resources path, file must contain email and password keys and values per object
     * @return 2D Array type Object, containing all the information from JSON file
     */
    public Object[][] readJsonFile(){
        Object[][] jsonResult = null;

        try {
            JSONParser parser = new JSONParser();

            //path to the JSON file.
            JSONArray data = (JSONArray) parser.parse(new FileReader("./src/main/resources/dataproviders/login_creds.json"));

            //Know what size should the 2D array have
            int jsonSize = data.toArray().length;
            jsonResult = new Object[jsonSize][2];

            //Iterate JSON information
            int cont = 0;
            for(Object o : data) {
                JSONObject jo = (JSONObject) o;

                //Add information to 2D array
                jsonResult[cont][0] = jo.get("email");
                jsonResult[cont][1] = jo.get("password");
                cont ++;
            }

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return jsonResult;
    }
}
