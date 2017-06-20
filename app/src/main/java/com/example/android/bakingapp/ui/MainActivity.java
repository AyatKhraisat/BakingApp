package com.example.android.bakingapp.ui;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.model.Ingredients;
import com.example.android.bakingapp.model.Steps;
import com.example.android.bakingapp.widgets.RecipeWidget;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity implements RecipesListFragment.OnRecipeClickListener {

   private boolean mTwoPane;
    private ArrayList<Steps> mSteps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (findViewById(R.id.steps_container) != null) {
            mTwoPane = true;

            FragmentManager fragmentManager = getSupportFragmentManager();
            StepsFragment stepsFragment = new StepsFragment();
            stepsFragment.setmSteps(mSteps);
            fragmentManager.beginTransaction().add(R.id.steps_container, stepsFragment).commit();

        }
    }

    @Override
    public void OnResipeSelected(ArrayList<Steps> steps, ArrayList<Ingredients> ingredientses) {

        StepsFragment stepsFragment = new StepsFragment();
        if (mTwoPane) {
            stepsFragment.setmSteps(steps);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.steps_container, stepsFragment)
                    .commit();

        } else {
            updateWidget(ingredientses);
            Intent intent = new Intent(MainActivity.this, RecipeActivity.class);

            intent.putParcelableArrayListExtra("steps", steps);
            startActivity(intent);

        }
    }

    public void updateWidget(ArrayList<Ingredients> ingredients) {
        Set<String> ingredientsesSet = new HashSet<>();
        if (ingredients != null) {

            for (int i = 0; i < ingredients.size(); i++) {
                StringBuilder builder = new StringBuilder();
                builder.append(ingredients.get(i).getQuantity() + " ");
                builder.append(ingredients.get(i).getMeasure() + " ");
                builder.append(ingredients.get(i).getIngredient());
                builder.append("\n");
                ingredientsesSet.add(builder.toString());
            }
        }

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        SharedPreferences.Editor editor = sp.edit();
        editor.putStringSet(getString(R.string.pref_ingredients_key), ingredientsesSet);
        editor.apply();
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this, RecipeWidget.class));
        //Trigger data update to handle the GridView widgets and force a data refresh
        appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.lv_ing_widget);
        RecipeWidget.updateRecipeWidgets(this, appWidgetManager, appWidgetIds);


    }
}


