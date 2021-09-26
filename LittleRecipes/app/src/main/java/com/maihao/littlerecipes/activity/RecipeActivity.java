package com.maihao.littlerecipes.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.maihao.littlerecipes.R;
import com.maihao.littlerecipes.databinding.ActivityRecipeBinding;

public class RecipeActivity extends AppCompatActivity implements View.OnClickListener {

    FloatingActionButton button;

    ActivityRecipeBinding binding;

    DrawerLayout mDrawerLayout;

    Toolbar toolbar;

    CollapsingToolbarLayout toolbarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_recipe);
        button = binding.chooseRecipesFloatingButton;
        toolbar = binding.recipeToolbar;
        toolbarLayout = binding.collapsingToolbar;
        mDrawerLayout = (DrawerLayout) binding.getRoot();
        button.setOnClickListener(this);
        initActionBar();
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