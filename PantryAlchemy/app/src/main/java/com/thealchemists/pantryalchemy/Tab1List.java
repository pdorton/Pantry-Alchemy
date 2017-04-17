package com.thealchemists.pantryalchemy;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Tab1List extends Activity
{

        @Override
    protected void onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.tab1list);
            String[] passedPantry = getIntent().getStringArrayExtra("passingPantry");
            ListView shoppingList = (ListView)findViewById(R.id.tab1_listview);
            ArrayList<String> passedItems = new ArrayList<>();
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, passedItems);

            shoppingList.setAdapter(adapter);

            for(int i = 0 ; i < passedPantry.length; i++)
            {
                adapter.add(passedPantry[i]);
            }

        }






}
