package com.maihao.littlerecipes.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.maihao.littlerecipes.fragment.ProcedureFragment;

import java.util.ArrayList;
import java.util.List;

public class ProcedureViewPagerAdapter extends FragmentStateAdapter {

    private List<ProcedureFragment> fragments = new ArrayList<>();

    public ProcedureViewPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle, List<ProcedureFragment> fragments) {
        super(fragmentManager, lifecycle);
        this.fragments = fragments;
    }

    @NonNull
    @Override
    public ProcedureFragment createFragment(int position) {
        return fragments.get(position);
    }

    @Override
    public int getItemCount() {
        return fragments.size();
    }
}
