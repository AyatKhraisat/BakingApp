package com.example.android.bakingapp.Adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.model.Recipe;

import java.util.List;

/**
 * Created by dell on 6/8/2017.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecipeViewHolder> {

    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }

    final private ListItemClickListener mOnClickListener;

    private Context mContext;

    private List<Recipe> mRecipes;

    public RecyclerAdapter(ListItemClickListener listener, List<Recipe> recipes, Context context) {

        mOnClickListener = listener;
        mRecipes = recipes;
        mContext = context;
    }

    @Override
    public int getItemCount() {

        if (mRecipes != null)
            return mRecipes.size();
        else
            return 0;
    }

    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);
        RecipeViewHolder pvh = new RecipeViewHolder(v);
        return pvh;
    }

    public void setmRecipes(List<Recipe> recipes) {
        mRecipes = recipes;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(RecipeViewHolder holder, int position) {
        if (mRecipes != null) {
          String x=mRecipes.get(position).getName();
             holder.recipeName.setText(mRecipes.get(position).getName());
        }
    }


    public class RecipeViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        TextView recipeName;
CardView cv;
        public RecipeViewHolder(View itemView) {
            super(itemView);
            recipeName = (TextView) itemView.findViewById(R.id.tv_recipe_title);
            cv = (CardView)itemView.findViewById(R.id.cv);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onListItemClick(clickedPosition);
        }
    }
}
