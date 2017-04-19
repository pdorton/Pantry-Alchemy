package com.thealchemists.pantryalchemy;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class GrilledChickenAndSalad extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chicken);

        ListView recipeInstructions = (ListView)findViewById(R.id.recipeInstructions);
        ArrayList<String> passedItems = new ArrayList<>();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, passedItems);

        recipeInstructions.setAdapter(adapter);

        adapter.add("\nIngredients:\n\n\t1-2 Chicken Breasts\n\t1/4 Head of Lettuce\n\t1/2 Cup of Strawberries\n\t1/4 Cantaloupe\n\t1/4 Pineapple\n\tRaspberry Vinaigrette Dressing\n");
        adapter.add("\nInstructions:\n\n\t1. Grill chicken breast\n\t2. Slice chicken breast\n\t3. Slice lettuce\n\t4. Slice strawberries\n\t5. Slice cantaloupe\n\t6. Slice pineapple\n\t7. Mix lettuce, fruits, and chicken\n\t8. Add desired amount of dressing\n\t9. Enjoy\n");
    }
}
