package com.example.android.bakingapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.model.Steps;

import java.util.ArrayList;
import java.util.List;

public class RecipeActivity extends AppCompatActivity {
    FragmentManager fragmentManager;
    StepsFragment stepsFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        Intent intent = getIntent();
        if (savedInstanceState == null) {


            fragmentManager = getSupportFragmentManager();
            stepsFragment = new StepsFragment();
            if (intent == null)
                throw new NullPointerException("Intent must not be null");
            else {
                List<Steps> steps = intent.getParcelableArrayListExtra("steps");
                stepsFragment.setmSteps((ArrayList<Steps>) steps);
                fragmentManager.beginTransaction().add(R.id.head_container, stepsFragment).commit();
            }
        }    }

}

