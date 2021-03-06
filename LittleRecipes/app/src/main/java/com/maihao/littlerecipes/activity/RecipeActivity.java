package com.maihao.littlerecipes.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.maihao.littlerecipes.R;
import com.maihao.littlerecipes.adapter.IngredientsAdapter;
import com.maihao.littlerecipes.adapter.ProcedureViewPagerAdapter;
import com.maihao.littlerecipes.databinding.ActivityRecipeBinding;
import com.maihao.littlerecipes.fragment.ProcedureFragment;
import com.maihao.littlerecipes.viewmodel.QueryDataViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecipeActivity extends AppCompatActivity implements View.OnClickListener {

    FloatingActionButton button;

    ActivityRecipeBinding binding;

    DrawerLayout mDrawerLayout;

    Toolbar toolbar;

    CollapsingToolbarLayout toolbarLayout;

    RecyclerView ingredientsRecyclerView;

    ViewPager2 proceduresViewPager2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Intent intent = getIntent();

        binding = DataBindingUtil.setContentView(this, R.layout.activity_recipe);

        button = binding.chooseRecipesFloatingButton;
        toolbar = binding.recipeToolbar;
        toolbarLayout = binding.collapsingToolbar;
        mDrawerLayout = (DrawerLayout) binding.getRoot();
        button.setOnClickListener(this);
        initActionBar();
        toolbarLayout.setTitle(intent.getStringExtra("title"));
        binding.recipeImageView.setImageResource(intent.getIntExtra("imageId", 0));

        // ??????????????????
        ingredientsRecyclerView = binding.ingredientsRecyclerView;
        ingredientsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<String> ingredients  = Arrays.asList(intent.getStringExtra("ingredients").split("@").clone());
        ingredientsRecyclerView.setAdapter(new IngredientsAdapter(this, ingredients));

        // ??????????????????
        proceduresViewPager2 = binding.proceduresViewPager2;
        List<String> procedures = Arrays.asList(intent.getStringExtra("procedures").split("- ").clone());
        List<ProcedureFragment> procedureFragments = new ArrayList<>();
        for (int i = 0; i < procedures.size(); i++) {
            procedureFragments.add(ProcedureFragment.newInstance(procedures.get(i)));
        }
        proceduresViewPager2.setAdapter(new ProcedureViewPagerAdapter(getSupportFragmentManager(), getLifecycle(), procedureFragments));


    }

    private void initActionBar() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

    }

    @Override
    public void onClick(View view) {
        mDrawerLayout.openDrawer(GravityCompat.END);
    }


    // ??????????????????
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);

    }
}