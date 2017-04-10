package com.thealchemists.pantryalchemy;

import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.speech.RecognizerIntent;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import com.miguelcatalan.materialsearchview.MaterialSearchView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private MaterialSearchView searchView;
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Init super.onCreate and set initial content view
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create and configure toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Pantry Alchemy");
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));

        // Set up the ViewPager with the sections adapter
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        // Set up tabs
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        // Set up search view
        searchView = (MaterialSearchView) findViewById(R.id.search_view);
        searchView.setVoiceSearch(true);
        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
                searchView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onSearchViewClosed() {
                searchView.setVisibility(View.GONE);
            }
        });
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                toast("You searched for: " + query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //Do some magic
                return false;
            }
        });
        // DEBUGGING
        /*
        // Add to favorites test
        User user1 = new User("MarcAnthony", "Fanfan", "mfanx2@gmail.com");
        Ingredient ing1 = new Ingredient("black beans", new Measurement(1, "can"));
        Ingredient ing2 = new Ingredient("white rice", new Measurement(1, "can"));
        Recipe rec1 = new Recipe("Black beans and rice", "Prepare beans and rice separately.\nThen combine and serve.", (Image) null);
        user1.addToFavorites(rec1);
        toast(user1.favorites.get(0).getRecipe_name() + "\nadded to favorites");
        */
        /*
        // Remove from favorites test
        User user1 = new User("MarcAnthony", "Fanfan", "mfanx2@gmail.com");
        Ingredient ing1 = new Ingredient("black beans", new Measurement(1, "can"));
        Ingredient ing2 = new Ingredient("white rice", new Measurement(1, "can"));
        Recipe rec1 = new Recipe("Black beans and rice", "Prepare beans and rice separately.\nThen combine and serve.", (Image) null);
        user1.addToFavorites(rec1);
        user1.removeFromFavorites(rec1);
        if(user1.favorites.isEmpty()) toast("Success!");
        else toast("Failure...");
        */
        /*
        // Add to shopping list test
        User user1 = new User("MarcAnthony", "Fanfan", "mfanx2@gmail.com");
        Ingredient ing1 = new Ingredient("black beans", new Measurement(1, "can"));
        Ingredient ing2 = new Ingredient("white rice", new Measurement(1, "can"));
        user1.addToShoppingList(ing1);
        user1.addToShoppingList(ing2);
        toast(user1.getShoppingList().get(0).getName() + "\n" + user1.getShoppingList().get(1).getName());
        */
        /*
        // Remove from shopping list test
        User user1 = new User("MarcAnthony", "Fanfan", "mfanx2@gmail.com");
        Ingredient ing1 = new Ingredient("black beans", new Measurement(1, "can"));
        Ingredient ing2 = new Ingredient("white rice", new Measurement(1, "can"));
        user1.addToShoppingList(ing1);
        user1.addToShoppingList(ing2);
        user1.removeFromShoppingList(ing1);
        user1.removeFromShoppingList(ing2);
        if(user1.getShoppingList().isEmpty()) toast("Success!");
        else toast("Failure...");
        */
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == MaterialSearchView.REQUEST_VOICE && resultCode == RESULT_OK) {
            ArrayList<String> matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            if(matches != null && matches.size() > 0) {
                String searchWrd = matches.get(0);
                if(!TextUtils.isEmpty(searchWrd)) searchView.setQuery(searchWrd, false);
            }

            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.action_settings) {
            toast("Settings are a work in progress...");
            return true;
        }
        else return super.onOptionsItemSelected(item);
    }

    // Subclass SectionsPagerAdapter
    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        // Subclass constructor
        public SectionsPagerAdapter(FragmentManager fm) { super(fm); }

        // Subclass methods
        @Override
        public Fragment getItem(int position) {
            // Returning the current tabs
            switch(position){
                case 0:
                    Tab1List tab1 = new Tab1List();
                    return tab1;
                case 1:
                    Tab2Pantry tab2 = new Tab2Pantry();
                    return tab2;
                case 2:
                    Tab3Recipes tab3 = new Tab3Recipes();
                    return tab3;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() { return 3; }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "List";
                case 1:
                    return "Pantry";
                case 2:
                    return "Recipes";
            }
            return null;
        }
    }

    // Debugging
    private void toast(String text) { Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show(); }
}
