package com.thealchemists.pantryalchemy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.thealchemists.pantryalchemy.R;

public class MainActivity extends AppCompatActivity {


    private LoginButton loginButton;
    private AccessToken accessToken;
    private AccessTokenTracker accessTokenTracker;
    private ProfileTracker profileTracker;

    CallbackManager callbackManager;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        callbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {

                    // Grabs profile and switches activity upon successful login
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        //AccessToken accessToken = loginResult.getAccessToken();
                        Profile profile = Profile.getCurrentProfile();
                        launchActivity(profile);
                    }

                    @Override
                    public void onCancel() {
                        // App code
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                    }
                });
    }

    // When reopening the app and still logged in
    // bypasses login page to given activity
//    @Override
//    protected void onResume(){
//        super.onResume();
//        Profile profile = Profile.getCurrentProfile();
//        launchActivity(profile);
//    }


    // Checks if user is logged in
    // Launches next Activity to be displayed
    // TODO: Set method to take in type int and pass to Intent array
    private void launchActivity(Profile profile){

        Intent recipeIntent = new Intent(MainActivity.this, Tab3Recipes.class);
        startActivity(recipeIntent);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}