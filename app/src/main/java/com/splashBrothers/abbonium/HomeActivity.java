package com.splashBrothers.abbonium;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.splashBrothers.abbonium.HomeFragment.HomeFragment;
import com.splashBrothers.abbonium.HomeFragment.MyAccountFragment;
import com.splashBrothers.abbonium.HomeFragment.MyGroupFragment;


public class HomeActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNavigationView = findViewById(R.id.bottomNavBar);
        bottomNavigationView.getMenu().getItem(3).setEnabled(false);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentHome, new HomeFragment()).commit();
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;

                switch (item.getItemId()) {
                    case R.id.home:
                        fragment = new HomeFragment();
                        break;
                    case R.id.myGroup:
                        fragment = new MyGroupFragment();
                        break;
                    case R.id.myAccount:
                        fragment = new MyAccountFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentHome, fragment).commit();
                return true;
            }
        });

    }
}