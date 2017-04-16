package com.thealchemists.pantryalchemy;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
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



    protected void openDataBase() throws SQLException{

        //Open the database
        String myPath = myContext.getDatabasePath(DB_NAME).getPath();
        if(myDataBase != null && myDataBase.isOpen()){
            return;
        }
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

    }


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


    public void closeDatabase() {

        if(myDataBase != null)
            myDataBase.close();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}