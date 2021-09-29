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

        initData();

        mRecyclerView = binding.recipesRecyclerView;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        MainViewAdapter adapter = new MainViewAdapter(getActivity(), viewModels);
        mRecyclerView.setAdapter(adapter);
        return binding.getRoot();
    }


    private void initData() {

        String[] titles = {
                "Thai Beef Salad",
                "Greek Salad I",
                "Wedge Salad with Elegant Blue Cheese Dressing",
                "Chef John's Classic Macaroni Salad",
                "Very Easy Fruit Salad",
                "Black Bean and Corn Salad I",
                "Black Bean and Corn Salad II",
                "Asian Coleslaw",
                "Chef John's Ranch Dressing",
                "Strawberry Spinach Salad I"};

        String[] contents = {
                "A colorful, tangy salad that brings out the best in Thai cuisine and spices.",
                "This is an incredibly good Greek salad recipe, nice and tangy and even better in the summer when you use fresh vegetables!",
                "This a great salad to have for a simple lunch or to serve with a nice dinner. Fresh ingredients make this salad very elegant and the wedge presentation makes it a great starter for a special occasion dinner. It is best to chill the dressing for 24 hours prior to serving.",
                "Whether it's sitting next to some smoky ribs or just a humble hot dog, this deli-style macaroni salad will always be a crowd favorite, as long as you pay attention to a few key details.",
                "In a hurry? Make this fruit salad in about 10 minutes. You can add or subtract different fruit according to your taste and what is in season.",
                "This salad is very colorful and includes a very tasty lime dressing.",
                "This salad is very colorful.",
                "A great twist on cabbage salad. The peanut butter in the dressing is the secret.",
                "It's been ages since I made homemade ranch dressing, and I'd forgotten how much better it is than the bottled stuff.",
                "Someone brought this salad to a pot luck dinner and I had to have the recipe. I have made it many, many times since then and I have been asked for the recipe every time I bring it somewhere. It is also a great way to get kids to eat spinach!"
        };

        int[] imageIds = {
                R.drawable.recipe_1,
                R.drawable.recipe_2,
                R.drawable.recipe_3,
                R.drawable.recipe_4,
                R.drawable.recipe_5,
                R.drawable.recipe_6,
                R.drawable.recipe_7,
                R.drawable.recipe_8,
                R.drawable.recipe_9,
                R.drawable.recipe_10,
        };

        String[] imageSrcs = {
                "@drawable/recipe_1",
                "@drawable/recipe_2",
                "@drawable/recipe_3",
                "@drawable/recipe_4",
                "@drawable/recipe_5",
                "@drawable/recipe_6",
                "@drawable/recipe_7",
                "@drawable/recipe_8",
                "@drawable/recipe_9",
                "@drawable/recipe_10",
        };

        for (int i = 0; i < 10; i++) {
            RecipeData data = new RecipeData(titles[i], contents[i]);
            data.setImageId(imageIds[i]);
            data.setIngredients("test ingredients" + i);
            data.setProcedure("test procedures" + i);
            data.setImageSrc(imageSrcs[i]);
            QueryDataViewModel viewModel = new QueryDataViewModel();
//            QueryDataViewModel viewModel = new ViewModelProvider(this,
//                    new ViewModelProvider.NewInstanceFactory()).get(QueryDataViewModel.class);
            viewModel.title.setValue(data.getTitle());
            viewModel.content.setValue(data.getContent());
            viewModel.imageId.setValue(data.getImageId());
            viewModel.imageSrc.setValue(data.getImageSrc());
            viewModel.ingredients.setValue(data.getIngredients());
            viewModel.procedures.setValue(data.getProcedure());
            viewModels.add(viewModel);
        }
    }
}