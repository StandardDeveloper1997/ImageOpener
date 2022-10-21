package com.example.imageoppener4;

import android.os.AsyncTask;
import android.os.SystemClock;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpSenderTask  extends AsyncTask<Void, Void, Long> {
    @Override
    protected Long doInBackground(Void... voids) {

        //System.out.println("I was here, I was hese");
        //System.out.println("I was here, I was hese");
        //SystemClock.sleep(5000);
        long number = 0;

        URL url = null;
        HttpURLConnection connection = null;
        try {
            url = new URL("https://www.google.com");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            System.out.println("->>"+connection.getResponseCode());

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();

            System.out.println(content.toString());




        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("------>>>>> Finally was executed");
            if (connection != null) {
                connection.disconnect();
                System.out.println("------>>>>> Connection was closed");
                System.out.println("I wait some time");
                SystemClock.sleep(5000);
                System.out.println("I waited 5 seconds");
            }
        }

        return number;
    }

}
