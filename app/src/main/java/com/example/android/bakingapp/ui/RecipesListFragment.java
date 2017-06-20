package com.example.android.bakingapp.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.bakingapp.Adapters.RecyclerAdapter;
import com.example.android.bakingapp.R;
import com.example.android.bakingapp.model.Ingredients;
import com.example.android.bakingapp.model.Recipe;
import com.example.android.bakingapp.model.Steps;
import com.example.android.bakingapp.rest.ApiClient;
import com.example.android.bakingapp.rest.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RecipesListFragment extends Fragment {

    private OnRecipeClickListener mCallback;
    private RecyclerView rv;
    private TextView noInternetTextView;

    public interface OnRecipeClickListener {
        void OnResipeSelected(ArrayList<Steps> steps, ArrayList<Ingredients> ingredientses);
    }

    public RecipesListFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View roorView = inflater.inflate(R.layout.fragment_recipes_list, container, false);

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<List<Recipe>> call = apiService.getRecipe();
        rv = (RecyclerView) roorView.findViewById(R.id.recycler_view);
        noInternetTextView = (TextView) roorView.findViewById(R.id.tv_no_interent);

        final RecyclerAdapter adapter = new RecyclerAdapter(new RecyclerAdapter.ListItemClickListener() {
            @Override
            public void onListItemClick(Recipe recipe) {
                mCallback.OnResipeSelected((ArrayList<Steps>) recipe.getSteps(), (ArrayList<Ingredients>) recipe.getIngredients());
            }
        }, null, getContext());

        rv.setAdapter(adapter);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
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
                rv.setVisibility(View.GONE);
                noInternetTextView.setVisibility(View.VISIBLE);
            }
        });


        return roorView;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (OnRecipeClickListener) context;

        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implemented OnImageClickListener");
        }
    }


}
