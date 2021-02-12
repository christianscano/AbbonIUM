package com.splashBrothers.abbonium;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.splashBrothers.abbonium.Data.Utente;
import com.splashBrothers.abbonium.HomeFragment.HomeFragment;
import com.splashBrothers.abbonium.HomeFragment.MyAccountFragment;
import com.splashBrothers.abbonium.HomeFragment.MyGroupFragment;


public class HomeActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;

    Utente utenteAttivo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        recoveryData();

        bottomNavigationView = findViewById(R.id.bottomNavBar);
        bottomNavigationView.getMenu().getItem(3).setEnabled(false);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentHome, new HomeFragment(LoginActivity.utentiGlobali, LoginActivity.serviziGlobali, utenteAttivo)).commit();
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;

                switch (item.getItemId()) {
                    case R.id.home:
                        fragment = new HomeFragment(LoginActivity.utentiGlobali, LoginActivity.serviziGlobali, utenteAttivo);
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


    public void recoveryData() {
        Intent intent = getIntent();
        utenteAttivo = (Utente) intent.getSerializableExtra("UtenteAttivo");
    }

}