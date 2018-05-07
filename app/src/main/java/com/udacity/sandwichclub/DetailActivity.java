package com.udacity.sandwichclub;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import java.util.Iterator;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    private TextView originTv;
    private TextView descriptionTv;
    private TextView ingredientsTv;
    private TextView alsoKnownAsTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);
        originTv = findViewById(R.id.origin_tv);
        descriptionTv = findViewById(R.id.description_tv);
        ingredientsTv = findViewById(R.id.ingredients_tv);
        alsoKnownAsTv = findViewById(R.id.also_known_tv);


        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .placeholder(R.drawable.ic_wallpaper_black_24dp)
                .error(R.drawable.ic_error_red_24dp)
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {

        Iterator<String> alsoKnownAs = sandwich.getAlsoKnownAs().iterator();
        while (alsoKnownAs.hasNext()){
            alsoKnownAsTv.append(alsoKnownAs.next());
            if(alsoKnownAs.hasNext()){
                ingredientsTv.append(", ");
            }
        }

        Iterator<String> ingredients = sandwich.getIngredients().iterator();
        while(ingredients.hasNext()){
            ingredientsTv.append(ingredients.next());
            if(ingredients.hasNext()){
                ingredientsTv.append(", ");
            }
        }
        originTv.setText(sandwich.getPlaceOfOrigin());
        descriptionTv.setText(sandwich.getDescription());


    }
}
