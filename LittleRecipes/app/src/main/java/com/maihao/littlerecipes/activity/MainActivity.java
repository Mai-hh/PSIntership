package com.maihao.littlerecipes.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.WindowManager;

import com.maihao.littlerecipes.R;
import com.maihao.littlerecipes.databinding.ActivityMainBinding;
import com.maihao.littlerecipes.viewmodel.QueryDataViewModel;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        toolbar = binding.mainToolbar;
        toolbar.setTitle("Choose what you like !");
        setSupportActionBar(toolbar);


    }
}