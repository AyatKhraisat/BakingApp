package com.example.android.bakingapp.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.model.Ingredients;
import com.example.android.bakingapp.model.Recipe;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by dell on 6/8/2017.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecipeViewHolder> {

    public interface ListItemClickListener {
        void onListItemClick(Recipe clickedItemIndex);
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
            holder.recipeName.setText(mRecipes.get(position).getName());
            List<Ingredients> ingredients = mRecipes.get(position).getIngredients();
            holder.recipeIng.setText("");
            if (ingredients != null) {
                for (int i = 0; i < ingredients.size(); i++) {
                    holder.recipeIng.append(ingredients.get(i).getQuantity() + " ");
                    holder.recipeIng.append(ingredients.get(i).getMeasure() + " ");
                    holder.recipeIng.append(ingredients.get(i).getIngredient());
                    holder.recipeIng.append("\n");
                }
            }
            String imageURL = mRecipes.get(position).getImage();
            if (!TextUtils.isEmpty(imageURL))
                Picasso.with(mContext).load(imageURL).into(holder.recipeImage);
        }
    }


    public class RecipeViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        TextView recipeName;
        TextView recipeIng;
        ImageView recipeImage;

        public RecipeViewHolder(View itemView) {
            super(itemView);
            recipeName = (TextView) itemView.findViewById(R.id.tv_recipe_title);
            itemView.setOnClickListener(this);
            recipeIng = (TextView) itemView.findViewById(R.id.tv_ingredientss);
            recipeImage = (ImageView) itemView.findViewById(R.id.iv_recipe_image);
        }


        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            Recipe recipe = mRecipes.get(clickedPosition);
            List<Ingredients> ingredients = recipe.getIngredients();

            mOnClickListener.onListItemClick(recipe);
        }

    }

}
