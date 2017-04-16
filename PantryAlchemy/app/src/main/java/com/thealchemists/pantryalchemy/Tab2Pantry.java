package com.thealchemists.pantryalchemy;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.*;



import java.io.IOException;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


import java.util.ArrayList;

public class Tab2Pantry extends Activity implements AdapterView.OnItemSelectedListener
{

    private DataBaseHelper myDbHelper = null;
    private SQLiteDatabase db = null;


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        System.out.println("DEBUG: created the instance of pantry");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab2pantry);

        /*FloatingActionButton addToPantry = (FloatingActionButton) findViewById(R.id.pantryAddIngredientButton);
        final Spinner pantryIngredientListSpinner = (Spinner) findViewById(R.id.ingredientAdderSpinner);
        int numIngredients = 5; //TODO:DATABASE: NEED TO ASSIGN THIS INT TO BE THE NUMBER OF ITEMS IN THE SQL RETURN OF THE INGREDIENT LIST AND REMOVE THE BELOW CODE
        ArrayList<String> ingredientList = new ArrayList<>();
        //TODO:DATABASE: NEED TO PUT CODE HERE TO INSERT ALL THE INGREDIENTS FROM THE SQL CALL
        ingredientList.add("apples");
        ingredientList.add("butter");
        ingredientList.add("crab");
        ingredientList.add("donut");
        ingredientList.add("eclair");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ingredientList);

        final EditText pantryIngredientList = (EditText) findViewById(R.id.pantryIngredientDisplay);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pantryIngredientListSpinner.setAdapter(dataAdapter);


        pantryIngredientListSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int selectionPos = pantryIngredientListSpinner.getSelectedItemPosition();
                String ingredientToAddToPantry = (String) pantryIngredientListSpinner.getItemAtPosition(selectionPos);
                pantryIngredientList.append(ingredientToAddToPantry);
            }
        });
        addToPantry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                pantryIngredientListSpinner.setVisibility(View.VISIBLE);

            }
        });*/




        myDbHelper = new DataBaseHelper(this);
        File database = getApplicationContext().getDatabasePath(DataBaseHelper.DB_NAME);
        if(false == database.exists()){
            myDbHelper.getReadableDatabase();
            if(DataBaseHelper.copyDataBase(this)){
                Toast.makeText(this, "Copy database success", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Copy database failed", Toast.LENGTH_SHORT).show();
            }
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {
        System.out.println("position = " + position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent)
    {


    }


}
