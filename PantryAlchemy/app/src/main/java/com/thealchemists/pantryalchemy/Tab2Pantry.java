package com.thealchemists.pantryalchemy;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.Toast;
import java.io.File;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;



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


        //initialize database
        myDbHelper = new DataBaseHelper(this);
        copyDatabase();

        //debug
        //Log.i("get List", "Getting list");

        //this makes recipe call to make recipe
        //Recipe recipe = myDbHelper.makeRecipe(1);

        //debug
        //Log.i("start", "start printing list");

        //debug
        //Log.i("recipe", recipe.getRecipe_name());


        //get ingredients for spinner
        ArrayList<String> ingredientList = new ArrayList<>();
        ingredientList = myDbHelper.getIngredientList();

        //debug
        //Log.i("checking", "all ingredients there if there are " +ingredientList.size() );

//
//        FloatingActionButton addToPantry = (FloatingActionButton) findViewById(R.id.pantryAddIngredientButton);
//        final Spinner pantryIngredientListSpinner = (Spinner) findViewById(R.id.ingredientAdderSpinner);
//
//        //TODO:DATABASE: NEED TO PUT CODE HERE TO INSERT ALL THE INGREDIENTS FROM THE SQL CALL
//
//
//        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ingredientList);
//
//        final EditText pantryIngredientList = (EditText) findViewById(R.id.pantryIngredientDisplay);
//        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        pantryIngredientListSpinner.setAdapter(dataAdapter);


        /*pantryIngredientListSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int selectionPos = pantryIngredientListSpinner.getSelectedItemPosition();
                String ingredientToAddToPantry = (String) pantryIngredientListSpinner.getItemAtPosition(selectionPos);
                pantryIngredientList.append(ingredientToAddToPantry);
            }
        });*/
//
//        addToPantry.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//                pantryIngredientListSpinner.setVisibility(View.VISIBLE);
//
//            }
//        });






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

    public void copyDatabase(){

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


}
