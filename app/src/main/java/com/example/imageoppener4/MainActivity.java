package com.example.imageoppener4;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.ContentResolver;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.VideoView;

import com.theartofdev.edmodo.cropper.CropImage;

public class MainActivity extends AppCompatActivity {

    public static int selectedId = 0;
    public static boolean videoselected = false;

    public static FragmentManager fragmentManager;
    public static ContentResolver contentResolver;
    public static boolean stopUploadImage;

    public String[] videoPaths;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
        contentResolver = getContentResolver();
        

        videoPaths = new String[4];
        videoPaths[0] = "android.resource://com.example.imageoppener4/raw/"+R.raw.obama;
        videoPaths[1] = "android.resource://com.example.imageoppener4/raw/"+R.raw.trump;
        videoPaths[2] = "android.resource://com.example.imageoppener4/raw/"+R.raw.dicaprio;
        videoPaths[3] = "android.resource://com.example.imageoppener4/raw/"+R.raw.whoisit;

        findViewById(R.id.videoView).setVisibility(View.INVISIBLE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            switch(requestCode){
                case 10:
                    //OtherLayoutFragment.buttonForVideoVisibility = View.INVISIBLE;
                    OtherLayoutFragment.buttonForVideoEnabled = false;
                    System.out.println(data.getData().getPath());
                    CropImage.activity(data.getData()).setAspectRatio(1,1).start(this);
                    //hello
                    break;
                case CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE:
                    CropImage.ActivityResult result = CropImage.getActivityResult(data);
                    if (resultCode == RESULT_OK) {
                        Uri uri = result.getUri();
                        UploadImageTask uip = new UploadImageTask();
                        uip.execute(uri);



                    } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                        Exception error = result.getError();
                    }
                    break;
                case 5:
                    System.out.println(data.getData());
                    VideoView videoView = findViewById(R.id.videoView);
                    videoView.setVisibility(View.VISIBLE);
                    videoView.setVideoURI(data.getData());
                    videoView.start();
                    break;
            }
        }
    }

    public void buttonForImageClick(View view){

        System.out.println("I clicked");

        Button button = (Button)findViewById(R.id.buttonForImage);

        System.out.println(button.getText());
        if(button.getText().equals("Select Image")){
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            startActivityForResult(intent, 10);
        } else if(button.getText().equals("Stop Upload")){
            System.out.println("Stop Upload Clicked");
            System.out.println(button.getText().equals("Stop Upload"));
            stopUploadImage = true;
        }
        //System.out.println("buttonForImageClick");


        //LayoutFragment.buttonForVideoVisibility = View.VISIBLE;
        //refreshLayout();

    }

    public void buttonForVideoClick(View view){

        //System.out.println("buttonForVideoClick");
        //Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        //intent.setType("video/*");
        //startActivityForResult(intent, 5);
        //refreshLayout();

        System.out.println("button for select Video Clicked");
        System.out.println("button for select Video Clicked");
        System.out.println("button for select Video Clicked");


        Intent intent = new Intent(this, SelectVideoActivity.class);
        startActivity(intent);

        //playVideo();


    }


    public void playVideo(){
        VideoView videoView = findViewById(R.id.videoView);
        Uri video = Uri.parse(videoPaths[selectedId]);
        videoView.setVideoURI(video);

        videoView.start();

        //MediaController mediaController = new MediaController(this);
        //videoView.setMediaController(mediaController);
        //videoView.

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener(){
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });

    }

    /*public static void refreshLayout(){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainerView2, OtherLayoutFragment.newInstance());
        fragmentTransaction.commit();
    }*/

    protected void onStart() {

        super.onStart();
        if(videoselected){
            findViewById(R.id.videoView).setVisibility(View.VISIBLE);
            playVideo();
        }

    }

}