package com.maihao.littlerecipes.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maihao.littlerecipes.R;
import com.maihao.littlerecipes.adapter.MainViewAdapter;
import com.maihao.littlerecipes.databinding.FragmentChooseRecipesBinding;
import com.maihao.littlerecipes.model.RecipeData;
import com.maihao.littlerecipes.viewmodel.QueryDataViewModel;

import java.util.ArrayList;
import java.util.List;

public class ChooseRecipesFragment extends Fragment {

    RecyclerView mRecyclerView;

    FragmentChooseRecipesBinding binding;

    List<QueryDataViewModel> viewModels = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_choose_recipes, container, false);
        // todo：测试，之后记得改成MVVM形式
        initData();

        mRecyclerView = binding.recipesRecyclerView;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        MainViewAdapter adapter = new MainViewAdapter(getActivity(), viewModels);
        mRecyclerView.setAdapter(adapter);
        return binding.getRoot();
    }


    private void initData() {

        for (int i = 0; i < 10; i++) {
            RecipeData data = new RecipeData("test title", "test content");
//            RecipeData data = new RecipeData();
            QueryDataViewModel viewModel = new QueryDataViewModel();
            viewModel.title.setValue(data.getTitle());
            viewModels.add(viewModel);
        }

    }
}