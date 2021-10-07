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
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.maihao.littlerecipes.R;
import com.maihao.littlerecipes.activity.RecipeActivity;
import com.maihao.littlerecipes.database.RecipeDataSQLHelper;
import com.maihao.littlerecipes.databinding.ItemCardViewBinding;
import com.maihao.littlerecipes.model.RecipeData;
import com.maihao.littlerecipes.util.RecyclerItemTouchHelper;
import com.maihao.littlerecipes.util.Utility;
import com.maihao.littlerecipes.viewmodel.QueryDataViewModel;

import java.util.Collections;
import java.util.List;

public class MainViewAdapter extends RecyclerView.Adapter<MainViewAdapter.MainViewHolder> implements RecyclerItemTouchHelper.ItemTouchHelperCallback {

    private Context mContext;

    private List<QueryDataViewModel> viewModels;

    private RecipeDataSQLHelper sqlHelper;

    public static class MainViewHolder extends RecyclerView.ViewHolder {

        ItemCardViewBinding binding;

        CardView cardView;

        TextView titleText;

        TextView contentText;

        public MainViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
            if (binding != null) {
                titleText = binding.itemRecipeTitle;
                contentText = binding.itemRecipeContent;
                cardView = binding.mainCardView;
            }
        }
    }

    public MainViewAdapter(Context mContext, List<QueryDataViewModel> viewModels, RecipeDataSQLHelper sqlHelper) {
        this.mContext = mContext;
        this.viewModels = viewModels;
        this.sqlHelper = sqlHelper;
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_view, parent, false);
        final MainViewHolder holder = new MainViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                QueryDataViewModel viewModel = viewModels.get(holder.getAdapterPosition());
                Intent intent = new Intent(mContext, RecipeActivity.class);
                intent.putExtra("title", viewModel.title.getValue());
                intent.putExtra("imageId", viewModel.imageId.getValue());
                intent.putExtra("imageSrc", viewModel.imageSrc.getValue());
                intent.putExtra("ingredients", viewModel.ingredients.getValue());
                intent.putExtra("procedures", viewModel.procedures.getValue());
                // todo 其他属性
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
        QueryDataViewModel viewModel = viewModels.get(position);
        holder.binding.setViewModel(viewModel);
    }

    @Override
    public int getItemCount() {
        return viewModels.size();
    }


    // 接口实现
    @Override
    public void onMove(int fromPosition, int toPosition) {
        // todo 数据库操作
        Collections.swap(viewModels, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void onItemDelete(int position) {
        // todo 数据库操作
        Utility.deleteRecipeData(viewModels.get(position).title.getValue(), sqlHelper);
        viewModels.remove(position);
        notifyItemRemoved(position);
    }
}
