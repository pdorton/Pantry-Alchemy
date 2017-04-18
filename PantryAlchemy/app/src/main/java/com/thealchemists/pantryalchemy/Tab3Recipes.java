package com.thealchemists.pantryalchemy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.facebook.Profile;
import com.facebook.login.LoginManager;

public class Tab3Recipes extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab3recipes);

        Button fbLogoutButton = (Button) findViewById(R.id.tempLogout);
        Button recipesListButton = (Button) findViewById(R.id.recipesListButton);
        Button recipesPantryButton = (Button) findViewById(R.id.recipesPantryButton);

        recipesListButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //TODO:BACKEND: PUT IN CODE TO PASS DATA FROM RECIPE TO LIST FOR COMPARISON
                Intent moveFromRecipeToList = new Intent(Tab3Recipes.this, Tab1List.class);
                startActivity(moveFromRecipeToList);
            }
        });

        recipesPantryButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {//TODO:BACKEND:PUT IN CODE TO PASS DATA FROM RECIPE TO PANTRY TO LATER BE PASSED TO THE LIST FOR COMPARISON
                Intent moveFromRecipeToPantry = new Intent(Tab3Recipes.this,Tab2Pantry.class);
                startActivity(moveFromRecipeToPantry);
            }
        });

        fbLogoutButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                LoginManager.getInstance().logOut();
                Profile profile = Profile.getCurrentProfile();
                launchActivity(profile);
            }
        });


    }

    private void launchActivity(Profile profile){
        if (profile == null) {
            Intent logoutIntent = new Intent(Tab3Recipes.this, MainActivity.class);
            startActivity(logoutIntent);
        }

    }
}