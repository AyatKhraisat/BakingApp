package com.example.android.bakingapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.android.bakingapp.Adapters.RecyclerAdapter;
import com.example.android.bakingapp.model.Recipe;
import com.example.android.bakingapp.rest.ApiClient;
import com.example.android.bakingapp.rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements RecyclerAdapter.ListItemClickListener {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<List<Recipe>> call = apiService.getRecipe();
        RecyclerView rv = (RecyclerView) findViewById(R.id.recycler_view);
        final RecyclerAdapter adapter = new RecyclerAdapter(this, null, this);
        rv.setHasFixedSize(true);
        rv.setAdapter(adapter);
        LinearLayoutManager llm = new LinearLayoutManager(MainActivity.this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(llm);
        call.enqueue(new Callback<List<Recipe>>() {


            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {

                List recipes = response.body();

                adapter.setmRecipes(recipes);
            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {
                Log.e(TAG, "error" + t.toString());
            }
        });
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        Toast.makeText(this,clickedItemIndex+"",Toast.LENGTH_LONG).show();
    }
}

