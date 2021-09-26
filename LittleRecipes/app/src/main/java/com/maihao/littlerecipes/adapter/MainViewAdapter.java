package com.maihao.littlerecipes.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.maihao.littlerecipes.R;
import com.maihao.littlerecipes.activity.RecipeActivity;
import com.maihao.littlerecipes.model.RecipeData;

import java.util.List;

public class MainViewAdapter extends RecyclerView.Adapter<MainViewAdapter.MainViewHolder> {

    private Context mContext;

    private List<RecipeData> recipeDataList;

    public static class MainViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;

        TextView titleText;

        TextView contentText;

        public MainViewHolder(@NonNull View itemView) {
            super(itemView);
            titleText = itemView.findViewById(R.id.item_recipe_title);
            contentText = itemView.findViewById(R.id.item_recipe_content);
            cardView = itemView.findViewById(R.id.main_card_view);
        }
    }

    public MainViewAdapter(Context mContext, List<RecipeData> recipeDataList) {
        this.mContext = mContext;
        this.recipeDataList = recipeDataList;
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_view, parent, false);

        final MainViewHolder holder = new MainViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RecipeData data = recipeDataList.get(holder.getAdapterPosition());
                Intent intent = new Intent(mContext, RecipeActivity.class);
                mContext.startActivity(intent);
                if (mContext instanceof RecipeActivity) {
                    ((Activity) mContext).finish();
                }
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        RecipeData data = recipeDataList.get(position);
        holder.titleText.setText(data.getTitle());
        holder.contentText.setText(data.getContent());
    }

    @Override
    public int getItemCount() {
        return recipeDataList.size();
    }

}
