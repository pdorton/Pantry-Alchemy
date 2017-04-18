package com.thealchemists.pantryalchemy;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.sql.*;



import java.io.IOException;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


import org.w3c.dom.Text;

import java.util.ArrayList;

public class Tab2Pantry extends Activity implements AdapterView.OnItemSelectedListener
{

    private DataBaseHelper myDbHelper = null;
    private SQLiteDatabase db = null;
    private ArrayList<String> ingredientsInPantry = new ArrayList<>();
    private boolean hasPantryBeenOpened = false;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        System.out.println("DEBUG: created the instance of pantry");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab2pantry);


        //initialize database
        myDbHelper = new DataBaseHelper(this);
        copyDatabase();

        //debug
        //Log.i("get List", "Getting list");

        //get ingredients
        ArrayList<String> ingredientList = myDbHelper.getIngredientList();
        for(int i =0;i <ingredientList.size()-1;i++)
        {
            Log.i("List", ingredientList.get(i));
        }

        //debug
        //Log.i("checking", "all ingredients there if there are " +ingredientList.size() );


        FloatingActionButton addToPantry = (FloatingActionButton) findViewById(R.id.pantryAddIngredientButton);
        final Spinner pantryIngredientListSpinner = (Spinner) findViewById(R.id.ingredientAdderSpinner);
        //TODO:DATABASE: NEED TO PUT CODE HERE TO INSERT ALL THE INGREDIENTS FROM THE SQL CALL
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ingredientList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pantryIngredientListSpinner.setAdapter(dataAdapter);
        TextView pantryListDisplay = (TextView)findViewById(R.id.pantryIngredientDisplay);
        pantryListDisplay.setText("");
        addToPantry.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                pantryIngredientListSpinner.setVisibility(View.VISIBLE);

            }
        });

        pantryIngredientListSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                if(hasPantryBeenOpened)
                {
                    String pickedIngredient = (String)parent.getItemAtPosition(position);
                    Log.i("Selected ", "Selected " + pickedIngredient );
                    ingredientsInPantry.add(pickedIngredient);
                    TextView pantryIngredientList = (TextView) findViewById(R.id.pantryIngredientDisplay);
                    addToPantry(ingredientsInPantry,pantryIngredientList);
                }
                else
                {// this eliminates the issue of it seemingly always taking Acacia on open
                    hasPantryBeenOpened = true;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Button pantryListButton = (Button) findViewById(R.id.pantryListButton);

        pantryListButton.setOnClickListener(new View.OnClickListener()
        {//MOVES THE ACTIVITY TO THE LIST ACTIVITY AND PASSES ALL OF THE PANTRY ITEMS.
            @Override
            public void onClick(View v)
            {
                String[] passingPantry = new String[ingredientsInPantry.size()];
                for(int i = 0 ; i < ingredientsInPantry.size(); i++)
                {
                    passingPantry[i] = ingredientsInPantry.get(i);
                }
                Intent moveFromPantryToList = new Intent(Tab2Pantry.this,Tab1List.class);
                moveFromPantryToList.putExtra("passingPantry", passingPantry);
                startActivity(moveFromPantryToList);

            }
        });







    }


    public void copyDatabase()
    {

        File database = getApplicationContext().getDatabasePath(DataBaseHelper.DB_NAME);
        if(false == database.exists())
        {
            myDbHelper.getReadableDatabase();
            if(DataBaseHelper.copyDataBase(this))
            {
                Toast.makeText(this, "Copy database success", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(this, "Copy database failed", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void addToPantry(ArrayList<String> ingredientsInPantry, TextView pantryIngredientsList)
    {   String pantryBuffer = "";
        for( int i = 0 ; i < ingredientsInPantry.size(); i++)
        {
            pantryBuffer = (pantryBuffer + ingredientsInPantry.get(i) + "\n");
        }

        pantryIngredientsList.setText(pantryBuffer);

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {// not sure why this is needed here but it seems to be, but when item is picked, this is not called

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent)
    {

    }
}
