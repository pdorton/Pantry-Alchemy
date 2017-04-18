package com.thealchemists.pantryalchemy;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

public class DataBaseHelper extends SQLiteOpenHelper{

    //The Android's default system path of your application database.
    private static String DB_PATH = "/data/data/com.thealchemists.pantryalchemy/databases/";

    protected static String DB_NAME = "pantry_database.sqlite";

    private SQLiteDatabase myDataBase;

    private final Context myContext;

    /**
     * Constructor
     * Takes and keeps a reference of the passed context in order to access to the application assets and resources.
     * @param context
     */
    protected DataBaseHelper(Context context) {

        super(context, DB_NAME, null, 1);
        this.myContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //opens a read only DB
    protected void openDataBaseRead() throws SQLException{

        //Open the database
        String myPath = myContext.getDatabasePath(DB_NAME).getPath();
        if(myDataBase != null && myDataBase.isOpen()){
            return;
        }
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

    }

    //opens DB to be able to be written to
    protected  void openDataBaseWrite() throws SQLException{
        //Open the database
        String myPath = myContext.getDatabasePath(DB_NAME).getPath();
        if(myDataBase != null && myDataBase.isOpen()){
            return;
        }
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    //copies DB to phone on first app startup
    protected static boolean copyDataBase(Context context) {

        try{
            Log.i("Database", "DB copying initiated");
            Log.i("DB Path",DB_PATH);
            InputStream inputStream = context.getAssets().open(DB_NAME);
            String outFileName = DB_PATH + "/" + DB_NAME ;
            OutputStream outputStream = new FileOutputStream(outFileName);
            byte[] buffer = new byte[1024];
            int length = 0;
            while((length = inputStream.read(buffer)) > 0){
                outputStream.write(buffer, 0, length);
            }
            outputStream.flush();
            outputStream.close();
            Log.w("MainActivity", "DB copied");
            return true;
        }catch (IOException e){
            e.printStackTrace();
            return false;
        }

    }

    //closes db
    public void closeDatabase() {

        if(myDataBase != null)
            myDataBase.close();

    }

    //makes arraylist of strings of all ingredients in ingredients table
    protected ArrayList<String> getIngredientList(){

        String query = "SELECT ing_name FROM ingredients ORDER BY ing_name";
        ArrayList<String> list = new ArrayList<>();
        Log.i("Getting DB", "getting DB");
        openDataBaseRead();
        Log.i("Getting DB", "Got the DB");

        Cursor results = myDataBase.rawQuery(query, null);
        results.moveToFirst();

        while (!results.isAfterLast()) {
            list.add(results.getString(0));
            results.moveToNext();
        }

        results.close();
        closeDatabase();
        return list;

    }


    //inserts ingredient
    protected void insertIngredient(String ingName){

        long i = 0;
        //open writable db
        openDataBaseWrite();
        Log.i("db", "opened writable db");
        //make insert statement
        SQLiteStatement stmt = myDataBase.compileStatement("INSERT INTO ingredients (ing_name) VALUES (?)");

        //bind the ing name and execute the statement
        try{
            myDataBase.beginTransaction();
            stmt.bindString(1, ingName);
            i = stmt.executeInsert();
            Log.i("dberror", "" + i);
            myDataBase.setTransactionSuccessful();
        }catch(SQLException e){
            e.printStackTrace();
        }
        finally {
            myDataBase.endTransaction();
        }
        //close db
        closeDatabase();

    }

    //makes a ingredient arraylist based off of recipeId param
    protected ArrayList<Ingredient> getRecipesIngredients(int recipeId){
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        Ingredient tempIngredient = null;
        Log.i("get List", "Getting DB");
        openDataBaseRead();
        Log.i("get List", "Got DB");

        String query = "SELECT ing_name, unit, quantity "
                    + "FROM recipe_ingredient "
                    + "JOIN ingredients, measurements, recipe "
                    + "WHERE recipe_ingredient.rid = recipe.id "
                    + "AND recipe_ingredient.measure_id = measurements.id "
                    + "AND recipe_ingredient.ing_id = ingredients.id "
                    + "AND recipe.id = "+ recipeId;

        try{
            Cursor results = myDataBase.rawQuery(query, null);
            results.moveToFirst();
            while(!results.isAfterLast()){
                tempIngredient = new Ingredient(results.getString(0), results.getString(1), results.getInt(2));
                ingredients.add(tempIngredient);
                results.moveToNext();
            }
            results.close();

        }catch(SQLException e){
            e.printStackTrace();
        }

        closeDatabase();
        return ingredients;
    }

    //makes a recipe
    protected Recipe makeRecipe(int recipeId) throws SQLException{

        ArrayList<Ingredient> ingredients = getRecipesIngredients(recipeId);
        Recipe thisRecipe = null;
        openDataBaseRead();

        String query = "SELECT recipe_name, directions, photo "
                    + "FROM recipe "
                    + "WHERE recipe.id = " + recipeId;

        try{
            Cursor results = myDataBase.rawQuery(query, null);
            if(results.moveToFirst()){
                //get photo as blob

                thisRecipe = new Recipe(results.getString(0), results.getString(1), results.getBlob(2), ingredients);
                closeDatabase();
                return thisRecipe;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        closeDatabase();
        return thisRecipe;
    }

    // Returns a list of recipes that have all ingredients in the pantry
    protected ArrayList<Recipe> getMakeableRecipes(){
        ArrayList<Ingredient> pantry = getPantry();
        // TODO: either add items to favorites or hardcode a list of recipes here
        ArrayList<Recipe> availableRecipes = getFavorites();
        ArrayList<Recipe> makeableRecipes = new ArrayList<>();
        ArrayList<Ingredient> recipeIngredients = new ArrayList<>();
        int foundIngredients;

        // iterate through all the recipes we want to consider
        for(int i = 0; i < availableRecipes.size(); i++){
            // Iterate through all of the ingredients in a recipe
            recipeIngredients = getRecipesIngredients(availableRecipes.get(i));
            for(int j = 0; j < recipeIngredients.size(); j++){
                // Count the number of ingredients that are found in the pantry
                foundIngredients = 0;
                for(int k = 0; k < pantry.size(); k++){
                    if(recipeIngredients.get(j).equals(pantry.get(k)){
                        foundIngredients++;
                    }
                }
                // If all of a recipes ingredients were found in the pantry, it's makeable
                if(foundIngredients = recipeIngredients.size())
                    makeableRecipes.add(availableRecipes.get(i));
            }
        }

        return makeableRecipes;
    }

    // Return a list of recipes that have at least one ingredient in common with the pantry
    protected ArrayList<Recipe> getAlmostMakeableRecipes(){
        ArrayList<Ingredient> pantry = getPantry();
        ArrayList<Recipe> availableRecipes = getFavorites();
        ArrayList<Recipe> almostMakeableRecipes = new ArrayList<>();
        ArrayList<Ingredient> recipeIngredients = new ArrayList<>();
        int foundIngredients;

        // iterate through all the recipes we want to consider
        for(int i = 0; i < availableRecipes.size(); i++){
            // Iterate through all of the ingredients in a recipe
            recipeIngredients = getRecipesIngredients(availableRecipes.get(i));
            for(int j = 0; j < recipeIngredients.size(); j++){
                // Compare the recipe ingredients to the pantry
                foundIngredients = 0;
                for(int k = 0; k < pantry.size(); k++){
                    // If any ingredients are in the pantry, its almost makeable
                    if(recipeIngredients.get(j).equals(pantry.get(k)){
                        almostMakeableRecipes.add(availableRecipes.get(i));
                    }
                }
            }
        }

        return almostMakeableRecipes;
    }


}
