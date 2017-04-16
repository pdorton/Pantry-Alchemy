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
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.miguelcatalan.materialsearchview.MaterialSearchView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private MaterialSearchView searchView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("CREATION", "onCreate: MAINACTIVITY ");
        // Init super.onCreate and set initial content view
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create and configure toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Pantry Alchemy");
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));



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

        Button pantryButton = (Button)findViewById(R.id.mainPantryButton);
        pantryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent goToPantry = new Intent(getApplicationContext(),Tab2Pantry.class);
                startActivity(goToPantry);
            }
        });
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

    protected void facebookSDKInitialize() {
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
    // Facebook Login
    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.splash, container, false);

        loginButton = (LoginButton) view.findViewById(R.id.login_button);
        loginButton.setReadPermissions("email");

        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // this method should be called on the 'User' class
                // and yield to their corresponding 'Pantry' class through the database.
            }

            @Override
            public void onCancel() {
                // this method should return the User to the initial page app renders.
            }

            @Override
            public void onError(FacebookException exception) {
                // When the app cannot connect through Facebook and authenticate user, do
            }
        });
    }

    // Facebook Main Activity
    public class MainActivity {
        CallbackManager callbackManager;
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            callbackManager = CallbackManager.Factory.create();
            LoginButton loginButton = (LoginButton) view.findViewById(R.id.usersettings_fragment_login_button);
            loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                // Main Activity; user already in database.
            });
        }

    // Debugging
    private void toast(String text) { Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show(); }
}
