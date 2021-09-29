package com.maihao.littlerecipes.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.maihao.littlerecipes.R;
import com.maihao.littlerecipes.databinding.ActivityRecipeBinding;
import com.maihao.littlerecipes.viewmodel.QueryDataViewModel;

public class RecipeActivity extends AppCompatActivity implements View.OnClickListener {

    FloatingActionButton button;

    ActivityRecipeBinding binding;

    DrawerLayout mDrawerLayout;

    Toolbar toolbar;

    CollapsingToolbarLayout toolbarLayout;

    private QueryDataViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Intent intent = getIntent();
//        viewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(QueryDataViewModel.class);
//        viewModel.imageSrc.setValue(intent.getStringExtra("ImageSrc"));

        binding = DataBindingUtil.setContentView(this, R.layout.activity_recipe);
        binding.setViewModel(viewModel);
        button = binding.chooseRecipesFloatingButton;
        toolbar = binding.recipeToolbar;
        toolbarLayout = binding.collapsingToolbar;
        mDrawerLayout = (DrawerLayout) binding.getRoot();
        button.setOnClickListener(this);
        initActionBar();
        toolbarLayout.setTitle(intent.getStringExtra("title"));
        // todo 这句注释掉是希望用viewModel绑定
        binding.recipeImageView.setImageResource(intent.getIntExtra("imageId", 0));
        binding.ingredientsTextView.setText(intent.getStringExtra("ingredients"));
        binding.proceduresTextView.setText(intent.getStringExtra("procedures"));
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


    // 菜单点击事件
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