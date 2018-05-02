package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static final String TAG = "JsonUtils.class";
    public static Sandwich parseSandwichJson(String json) {

        Sandwich sandwich = new Sandwich();

        try{
            JSONObject object = new JSONObject(json);
            JSONObject name = object.getJSONObject("name");
            sandwich.setMainName(name.getString("mainName"));
            sandwich.setAlsoKnownAs(parseArray(name.getJSONArray("alsoKnownAs")));
            sandwich.setPlaceOfOrigin(object.getString("placeOfOrigin"));
            sandwich.setDescription(object.getString("description"));
            sandwich.setImage(object.getString("image"));
            sandwich.setIngredients(parseArray(object.getJSONArray("ingredients")));
            Log.d(TAG,  sandwich.getImage());

        }catch (JSONException jsone){
            jsone.printStackTrace();
        }

        return sandwich;
    }

    private static List<String> parseArray(JSONArray arr){
        if(arr != null){
            List<String> listdata = new ArrayList<String>();
            for(int i = 0; i<arr.length(); i++){
                try{
                    listdata.add(arr.getString(i));
                }catch (JSONException jsone){
                    jsone.printStackTrace();
                }

            }
            return listdata;
        }
        return null;
    }
}
