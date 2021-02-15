package com.splashBrothers.abbonium.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.splashBrothers.abbonium.Data.Services.Servizio;
import com.splashBrothers.abbonium.Data.Utente;
import com.splashBrothers.abbonium.R;

import java.util.ArrayList;
import java.util.HashMap;

public class MyGroupFragment extends Fragment {

    Utente utenteAttivo;
    ArrayList<Servizio> serviziGlobali;
    HashMap<String, Utente> utentiGlobali;

    TabLayout tabBar;
    ViewPager2 viewPager;

    public MyGroupFragment() { }

    public static MyGroupFragment newInstance(HashMap<String, Utente> utentiGlobali, ArrayList<Servizio> serviziGlobali, Utente utenteAttivo) {
        MyGroupFragment myFragment = new MyGroupFragment();

        Bundle args = new Bundle();
        args.putSerializable("utentiGlobali", utentiGlobali);
        args.putSerializable("serviziGlobali", serviziGlobali);
        args.putSerializable("utenteAttivo", utenteAttivo);
        myFragment.setArguments(args);

        return myFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_my_group, container, false);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        utenteAttivo = (Utente) getArguments().getSerializable("utenteAttivo");
        utentiGlobali = (HashMap<String, Utente>) getArguments().getSerializable("utentiGlobali");
        serviziGlobali = (ArrayList<Servizio>) getArguments().getSerializable("serviziGlobali");
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tabBar = view.findViewById(R.id.tabView);
        viewPager = view.findViewById(R.id.viewPager);

        ViewStateAdapter adapter = new ViewStateAdapter(getFragmentManager(),getLifecycle());
        viewPager.setAdapter(adapter);

        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(
                tabBar, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch(position) {
                    case 0:
                        tab.setText("CREATI DA ME");
                        break;
                    case 1:
                        tab.setText("IN CUI PARTECIPO");
                }
            }
        });

        tabLayoutMediator.attach();
    }


    //Adapter per la TabView
    private class ViewStateAdapter extends FragmentStateAdapter {
        public ViewStateAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
            super(fragmentManager, lifecycle);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            if (position == 0)
                return MyGroupCreatiFragment.newInstance(serviziGlobali, utenteAttivo);
            else
                return MyGroupUnitoFragment.newInstance(serviziGlobali, utenteAttivo);
        }

        @Override
        public int getItemCount() {
            return 2;
        }
    }
}