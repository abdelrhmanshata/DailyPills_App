package com.example.dailypillsapp.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.dailypillsapp.Fragment.DailyFragment;
import com.example.dailypillsapp.Fragment.WeeklyFragment;

public class ViewPagerAdapter extends FragmentStateAdapter {

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 1:
                return new WeeklyFragment();
            case 0:
            default:
                return new DailyFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}