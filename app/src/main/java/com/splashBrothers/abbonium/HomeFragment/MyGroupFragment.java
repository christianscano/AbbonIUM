package com.splashBrothers.abbonium.HomeFragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.splashBrothers.abbonium.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyGroupFragment extends Fragment {

    public MyGroupFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_group, container, false);
    }
}