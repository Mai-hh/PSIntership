package com.maihao.littlerecipes.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.maihao.littlerecipes.R;
import com.maihao.littlerecipes.databinding.ActivityRecipeBinding;

public class RecipeActivity extends AppCompatActivity implements View.OnClickListener {

    FloatingActionButton button;

    ActivityRecipeBinding binding;

    DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_recipe);
        button = binding.chooseRecipesFloatingButton;
        mDrawerLayout = (DrawerLayout) binding.getRoot();
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        mDrawerLayout.openDrawer(GravityCompat.END);
    }
}