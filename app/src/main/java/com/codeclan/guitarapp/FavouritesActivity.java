package com.codeclan.guitarapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class FavouritesActivity extends AppCompatActivity {

    public static final String FAVOURITES = "MyFavourites";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);

        SharedPreferences sharedPref = getSharedPreferences(FAVOURITES, Context.MODE_PRIVATE);
        String favourites = sharedPref.getString("guitarList", "Nothing to return"); // guitarList key

        // Now we have to de-serialize our object

        Gson gson = new Gson();
        TypeToken<ArrayList<Guitar>> guitarArrayList = new TypeToken<ArrayList<Guitar>>(){};

        // TypeToken is us telling GSON what the object is

        ArrayList<Guitar> myFavourites = gson.fromJson(favourites, guitarArrayList.getType());

        // myFavourites is now a java arraylist

        // now we loop through our arraylist and add the items to the menu

        TextView list = (TextView)findViewById(R.id.favourites_list);
        String guitarString = "";

        for(Guitar guitar : myFavourites){
            guitarString += guitar.getBrand() + " " + guitar.getModel() + " " + guitar.getPrice() + "p" + "\r\n";  // creates a new line
        }

        list.setText(guitarString);

    }
}
