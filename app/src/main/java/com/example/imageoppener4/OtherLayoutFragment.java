package com.example.imageoppener4;

import android.graphics.Bitmap;
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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OtherLayoutFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OtherLayoutFragment extends Fragment {



    //public static String buttonForImageText = "Upload Image";
    public static String imageShapeText = "";
    public static String buttonForVideoText = "Select Video";
    //public static int buttonForVideoVisibility = View.GONE;

    public static boolean buttonForVideoEnabled = false;

    public static String progressStatusText = "";
    public static int progressBarVisibility = View.GONE;
    public static int progressBarValue = 0;
    public static int imageViewVisibility = View.VISIBLE;
    public static Bitmap imageBitmap = null;
    //public static int videoViewVisibility = View.VISIBLE;

    public OtherLayoutFragment() {
        // Required empty public constructor
    }

    public static OtherLayoutFragment newInstance() {
        OtherLayoutFragment fragment = new OtherLayoutFragment();
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
        return inflater.inflate(R.layout.fragment_other_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);


        //((Button)view.findViewById(R.id.buttonForImage)).setText(buttonForImageText);
        ((TextView)view.findViewById(R.id.imageShape)).setText(imageShapeText);

        Button buttonForVideo = (Button)view.findViewById(R.id.buttonForVideo);

        //((Button)view.findViewById(R.id.buttonForVideo)).setText(buttonForVideoText);
        //((Button)view.findViewById(R.id.buttonForVideo)).setVisibility(buttonForVideoVisibility);

        if(buttonForVideoEnabled){
            buttonForVideo.setText(buttonForVideoText);
            buttonForVideo.setClickable(true);
            buttonForVideo.setAlpha(1.0f);
        } else {
            buttonForVideo.setText("Disabled");
            buttonForVideo.setClickable(false);
            buttonForVideo.setAlpha(0.5f);
        }


        ((TextView)view.findViewById(R.id.progressStatusText)).setText(progressStatusText);
        ((ProgressBar)view.findViewById(R.id.progressBar)).setVisibility(progressBarVisibility);
        ((ProgressBar)view.findViewById(R.id.progressBar)).setProgress(progressBarValue);
        ((ImageView)view.findViewById(R.id.imageView)).setVisibility(imageViewVisibility);

        if(imageBitmap != null){
            ((ImageView)view.findViewById(R.id.imageView)).setImageBitmap(imageBitmap);
        } else {
            ((ImageView)view.findViewById(R.id.imageView)).setImageResource(R.drawable.back);
        }

        //VideoView videoView = view.findViewById(R.id.videoView);



    }

    public void buttonForImageClick(View view){}
    public void buttonForVideoClick(View view){}

    public static void refresh(){
        FragmentTransaction fragmentTransaction = MainActivity.fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainerView2, newInstance());
        fragmentTransaction.commit();
    }


}