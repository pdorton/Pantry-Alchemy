package com.thealchemists.pantryalchemy;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Tab1List extends Fragment {
    private ListView tab1_listview;
    private ArrayAdapter<String> arrayAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab1list, container, false);
        /*
        String[] testArray = new String[3];
        testArray[0] = "Sugar";
        testArray[1] = "Eggs";
        testArray[2] = "Milk";

        tab1_listview = (ListView) getActivity().findViewById(R.id.tab1_listview);
        arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, testArray);
        tab1_listview.setAdapter(arrayAdapter);
        */
        return rootView;
    }
}
