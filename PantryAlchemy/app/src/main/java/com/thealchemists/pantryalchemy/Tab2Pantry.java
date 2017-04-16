package com.thealchemists.pantryalchemy;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
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

public class Tab2Pantry extends Activity
{
    private DataBaseHelper myDbHelper = null;
    private SQLiteDatabase db = null;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab2pantry);

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


}
