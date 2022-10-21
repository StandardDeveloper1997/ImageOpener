package com.example.imageoppener4;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class SelectVideoActivity extends AppCompatActivity {

    ImageView[] imageViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_video);

        imageViews = new ImageView[4];
        imageViews[0] = (ImageView)findViewById(R.id.obamaView);
        imageViews[1] = (ImageView)findViewById(R.id.trumpView);
        imageViews[2] = (ImageView)findViewById(R.id.dicaprioView);
        imageViews[3] = (ImageView)findViewById(R.id.whoisitView);


        imageViews[MainActivity.selectedId].setBackgroundColor(Color.rgb(98,0,238));
        MainActivity.videoselected = true;
    }

    public void obamaClick(View view){
        System.out.println("Clicked Obama");

        imageViews[MainActivity.selectedId].setBackgroundColor(Color.rgb(255,255,255));
        MainActivity.selectedId = 0;
        imageViews[MainActivity.selectedId].setBackgroundColor(Color.rgb(98,0,238));
    }

    public void trumpClick(View view){
        System.out.println("Clicked trumpClick");

        imageViews[MainActivity.selectedId].setBackgroundColor(Color.rgb(255,255,255));
        MainActivity.selectedId = 1;
        imageViews[MainActivity.selectedId].setBackgroundColor(Color.rgb(98,0,238));
    }

    public void dicaprioClick(View view){
        System.out.println("Clicked dicaprioClick");

        imageViews[MainActivity.selectedId].setBackgroundColor(Color.rgb(255,255,255));
        MainActivity.selectedId = 2;
        imageViews[MainActivity.selectedId].setBackgroundColor(Color.rgb(98,0,238));
    }

    public void whoisitClick(View view){
        System.out.println("Clicked whoisitClick");

        imageViews[MainActivity.selectedId].setBackgroundColor(Color.rgb(255,255,255));
        MainActivity.selectedId = 3;
        imageViews[MainActivity.selectedId].setBackgroundColor(Color.rgb(98,0,238));
    }

    public void nextButton(View view){
        System.out.println("Clicked next");
        this.finish();

    }

}