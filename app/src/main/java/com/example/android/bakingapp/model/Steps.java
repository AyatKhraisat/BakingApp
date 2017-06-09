package com.example.android.bakingapp.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by dell on 6/7/2017.
 */

public class Steps {
    @SerializedName("id")
    private int id;

    @SerializedName("shortDescription")
    private String shortDescription;

    @SerializedName("description")
    private String description;

    @SerializedName("videoURL")
    private String videoURL;

    @SerializedName("thumbnailURL")
    private String thumbnailURL;


}

