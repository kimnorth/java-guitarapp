package com.codeclan.guitarapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.content.SharedPreferencesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String FAVOURITES = "MyFavourites"; // Name of shared preferences file to store info
    ArrayList<Guitar> guitarList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        guitarList = new ArrayList<Guitar>();
        guitarList.add(new Guitar("Fender", "Stratocaster", 300000));
        guitarList.add(new Guitar("Fender", "Telecaster", 203000));
        guitarList.add(new Guitar("Fender", "Jazzmaster", 402000));

    }

    public void addListToSharedPreferences(View view){

        SharedPreferences sharedPref = getSharedPreferences(FAVOURITES, Context.MODE_PRIVATE);

        // MODE_PRIVATE means no other app can touch this file - which is the preferred state as
        // using WORLD_READABLE or another will throw up a security exception error

        SharedPreferences.Editor editor = sharedPref.edit();

        // Getting an editor object from SharedPreferences object we just created

        Gson gson = new Gson();

        // Add the guitarList to sharedPrefs as a JSON String. Then apply. (apply = save)

        editor.putString("guitarList", gson.toJson(guitarList));

//      other example e.g. a game storing your score - editor.putInt("topScore", 100)

        // Now we've created our Key-Value pair with guitarlist as key and arraylist as value

        editor.apply();

        // We can use a "Toast" to notifiy the user they've successfully updated their list

        Toast.makeText(MainActivity.this, "Guitars added! Hurrah!", Toast.LENGTH_LONG).show();

    }

    // Here we override the onCreate file to create our menu

    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.activity_main, menu); //
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == R.id.action_favourites){

            // If menu item clicked has the passed id, start a new activity

            Intent intent = new Intent(this, FavouritesActivity.class);
            startActivity(intent);


            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
