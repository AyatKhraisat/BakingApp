package com.example.android.bakingapp.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.bakingapp.Adapters.StepsAdapter;
import com.example.android.bakingapp.R;
import com.example.android.bakingapp.model.Steps;

import java.util.ArrayList;


public class StepsFragment extends Fragment  {


    private ArrayList<Steps> mSteps;
    private StepsAdapter stepsAdapter;

    public StepsFragment() {
        // Required empty public constructor
    }

    public void setmSteps(ArrayList<Steps> mSteps) {
        this.mSteps = mSteps;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_steps, container, false);
        RecyclerView rvSteps = (RecyclerView) view.findViewById(R.id.rv_steps);
        if (mSteps != null) {
            stepsAdapter = new StepsAdapter(mSteps, getContext());
            LinearLayoutManager atepsLayoutManager = new LinearLayoutManager(getContext());
            atepsLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            rvSteps.setHasFixedSize(true);
            rvSteps.setAdapter(stepsAdapter);
            rvSteps.setLayoutManager(atepsLayoutManager);
        }
        return view;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }




    }
