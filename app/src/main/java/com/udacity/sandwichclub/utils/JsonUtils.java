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
    public static final String KEY_NAME = "name";
    public static final String KEY_MAIN_NAME = "mainName";
    public static final String KEY_ALSO_KNOWN_AS = "alsoKnownAs";
    public static final String KEY_PLACE_OF_ORIGIN = "placeOfOrigin";
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_IMAGE = "image";
    public static final String KEY_INGREDIENTS = "ingredients";

    public static Sandwich parseSandwichJson(String json) {

        Sandwich sandwich = new Sandwich();

        try{
            JSONObject object = new JSONObject(json);
            JSONObject name = object.getJSONObject(KEY_NAME);
            sandwich.setMainName(name.getString(KEY_MAIN_NAME));
            sandwich.setAlsoKnownAs(parseArray(name.getJSONArray(KEY_ALSO_KNOWN_AS)));
            sandwich.setPlaceOfOrigin(object.getString(KEY_PLACE_OF_ORIGIN));
            sandwich.setDescription(object.getString(KEY_DESCRIPTION));
            sandwich.setImage(object.getString(KEY_IMAGE));
            sandwich.setIngredients(parseArray(object.getJSONArray(KEY_INGREDIENTS)));
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
