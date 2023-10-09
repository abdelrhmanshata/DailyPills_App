package com.example.dailypillsapp.UI;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.dailypillsapp.Fragment.GroupFragment;
import com.example.dailypillsapp.Fragment.HomeFragment;
import com.example.dailypillsapp.Fragment.ProgressFragment;
import com.example.dailypillsapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    int ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ID = (int) getIntent().getIntExtra("ID",0);

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        FragmentManager fragmentManager = getSupportFragmentManager();
        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment;
                switch (item.getItemId()) {
                    case R.id.action_group:
                        fragment = new GroupFragment();
                        break;
                    case R.id.action_progress:
                        fragment = new ProgressFragment();
                        break;
                    case R.id.action_home:
                    default:
                        fragment = new HomeFragment();
                        break;
                }
                fragmentManager.beginTransaction().replace(R.id.container, fragment).commit();
                return true;
            }
        });

        // Set default selection
        if(ID==0){
            bottomNavigationView.setSelectedItemId(R.id.action_home);
        }else{
            bottomNavigationView.setSelectedItemId(R.id.action_progress);
        }

    }

}