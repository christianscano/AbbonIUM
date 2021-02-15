package com.splashBrothers.abbonium.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.splashBrothers.abbonium.Data.Utente;
import com.splashBrothers.abbonium.Fragment.HomeFragment;
import com.splashBrothers.abbonium.Fragment.MyAccountFragment;
import com.splashBrothers.abbonium.Fragment.MyGroupFragment;
import com.splashBrothers.abbonium.R;


public class HomeActivity extends AppCompatActivity{
    BottomNavigationView bottomNavigationView;
    FloatingActionButton btnAddGruppo;

    Utente utenteAttivo;
    boolean isHome; //serve per capire quale fragment caricare

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        recoveryData();

        bottomNavigationView = findViewById(R.id.bottomNavBar);
        btnAddGruppo = findViewById(R.id.btnAddGroup);

        bottomNavigationView.getMenu().getItem(3).setEnabled(false);

        if(isHome)
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentHome, HomeFragment.newInstance(LoginActivity.utentiGlobali, LoginActivity.serviziGlobali, utenteAttivo)).commit();
        else {
            bottomNavigationView.setSelectedItemId(R.id.myGroup);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentHome, MyGroupFragment.newInstance(LoginActivity.utentiGlobali, LoginActivity.serviziGlobali, utenteAttivo)).commit();
        }
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;

                switch (item.getItemId()) {
                    case R.id.home:
                        fragment = HomeFragment.newInstance(LoginActivity.utentiGlobali, LoginActivity.serviziGlobali, utenteAttivo);
                        break;
                    case R.id.myGroup:
                        fragment = MyGroupFragment.newInstance(LoginActivity.utentiGlobali, LoginActivity.serviziGlobali, utenteAttivo);
                        break;
                    case R.id.myAccount:
                        fragment = new MyAccountFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentHome, fragment).commit();
                return true;
            }
        });

        btnAddGruppo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, AggiungiGruppoActivity.class);
                intent.putExtra("utenteAttivo", utenteAttivo);
                startActivity(intent);
            }
        });
    }

    /* --- FUNZIONI SUPPORTO --- */

    public void recoveryData() {
        Intent intent = getIntent();
        utenteAttivo = (Utente) intent.getSerializableExtra("utenteAttivo");
        isHome = intent.getBooleanExtra("isHome", true);
    }
}