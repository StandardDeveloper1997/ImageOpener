package com.example.imageoppener4;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ButtonForImageFragment extends Fragment {

    public static String buttonForImageText = "Select Image";

    public ButtonForImageFragment() {
        // Required empty public constructor
    }

    public static ButtonForImageFragment newInstance() {
        ButtonForImageFragment fragment = new ButtonForImageFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_button_for_image, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);


        ((Button)view.findViewById(R.id.buttonForImage)).setText(buttonForImageText);


    }

    public void buttonForImageClick(View view){}

    public static void refresh(){
        FragmentTransaction fragmentTransaction = MainActivity.fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainerView, ButtonForImageFragment.newInstance());
        fragmentTransaction.commit();
    }
}