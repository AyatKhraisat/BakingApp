package com.example.android.bakingapp.rest;

import com.example.android.bakingapp.model.Recipe;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by dell on 6/7/2017.
 */


public interface ApiInterface {
    @GET("baking.json")
    Call<List<Recipe>> getRecipe();

}