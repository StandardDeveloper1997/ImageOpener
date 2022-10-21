package com.example.imageoppener4;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.view.View;

import java.io.IOException;
import java.io.InputStream;

public class UploadImageTask extends AsyncTask<Uri, Void, Long> {


    @Override
    protected Long doInBackground(Uri... uris) {


        ButtonForImageFragment.buttonForImageText = "Stop Upload";
        ButtonForImageFragment.refresh();
        OtherLayoutFragment.imageBitmap = null;
        OtherLayoutFragment.progressBarVisibility = View.VISIBLE;
        //OtherLayoutFragment.imageViewVisibility = View.GONE;
        OtherLayoutFragment.progressStatusText = "0 %";
        OtherLayoutFragment.refresh();
        MainActivity.stopUploadImage = false;


        Bitmap bitmap = null;

        try{
            InputStream is = MainActivity.contentResolver.openInputStream(uris[0]);
            int bytestotal = is.available();
            byte[] bytes = new byte[bytestotal];
            int bytesperiteration = 1000;
            int iterationstotal = bytestotal / bytesperiteration;
            int lastbytes = bytestotal % bytesperiteration;
            int i = 0;
            int percent = 0;
            double part = ((double) bytestotal) / 100;

            while(i < iterationstotal && !MainActivity.stopUploadImage){
                is.read(bytes, i*bytesperiteration, bytesperiteration);


                while(i*bytesperiteration+bytesperiteration > percent*part){
                    percent++;
                    SystemClock.sleep(1);
                    OtherLayoutFragment.progressBarValue = percent;
                    OtherLayoutFragment.progressStatusText = percent+" %";
                    OtherLayoutFragment.refresh();
                }

                i++;
            }

            if(!MainActivity.stopUploadImage){
                is.read(bytes, iterationstotal*bytesperiteration, lastbytes);
                while(i*bytesperiteration+lastbytes > percent*part){

                    SystemClock.sleep(1);

                    OtherLayoutFragment.progressBarValue = percent;
                    OtherLayoutFragment.progressStatusText = percent+" %";
                    OtherLayoutFragment.refresh();
                    percent++;
                }

                bitmap = BitmapFactory.decodeByteArray(bytes,0,bytestotal);

                System.out.println("Width "+bitmap.getWidth()+" Height "+bitmap.getHeight());
                OtherLayoutFragment.imageShapeText = bitmap.getWidth()+" X "+bitmap.getHeight();
                OtherLayoutFragment.imageBitmap = bitmap;
                //OtherLayoutFragment.buttonForVideoVisibility = View.VISIBLE;
                OtherLayoutFragment.buttonForVideoEnabled = true;


                //HttpSenderTask hst = new HttpSenderTask();
                //hst.execute();

            } else {
                OtherLayoutFragment.imageShapeText = "";
                OtherLayoutFragment.imageBitmap = null;
            }

            ButtonForImageFragment.buttonForImageText = "Select Image";
            ButtonForImageFragment.refresh();

            OtherLayoutFragment.progressBarVisibility = View.GONE;
            OtherLayoutFragment.progressStatusText = "";
            OtherLayoutFragment.imageViewVisibility = View.VISIBLE;

            OtherLayoutFragment.refresh();


            is.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
