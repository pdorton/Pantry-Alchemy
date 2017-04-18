package com.thealchemists.pantryalchemy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class Tab3Recipes extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab3recipes);


        Button recipesListButton = (Button) findViewById(R.id.recipiesListButton);
        Button recipesPantryButton = (Button) findViewById(R.id.recipesPantryButton);

        recipesListButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //TODO:BACKEND: PUT IN CODE TO PASS DATA FROM RECIPE TO LIST FOR COMPARISON
                Intent moveFromRecipeToList = new Intent(Tab3Recipes.this, Tab1List.class);
                //moveFromRecipeToList.putExtra("passingRecipe", )  //THIS LINE NEEDS A String[] of the ingredients to compare
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

        ImageButton image1 = (ImageButton) findViewById(R.id.imageButton);

        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewChicken = new Intent(Tab3Recipes.this, GrilledChickenAndSalad.class);
                startActivity(viewChicken);
            }
        });


    }
}
