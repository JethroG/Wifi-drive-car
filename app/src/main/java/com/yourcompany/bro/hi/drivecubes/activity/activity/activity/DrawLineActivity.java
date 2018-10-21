package com.yourcompany.bro.hi.drivecubes.activity.activity.activity;





import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.yourcompany.bro.hi.drivecubes.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


class DrawLineActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_line);
        Motion();

    }

    public class FeedTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            try {
                //change IP to the IP you set in the ARDUINO
                URL url = new URL("http://192.168.4.1/car/" + params[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder result = new StringBuilder();
                String inputLine;
                while ((inputLine = in.readLine()) != null)
                    result.append(inputLine).append("\n");

                in.close();
                connection.disconnect();
                return result.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    public void Motion() {

//            Button right = (Button) findViewById(R.id.button_right);
//            Button left = (Button) findViewById(R.id.button_left);
        Button forward = findViewById(R.id.button_forward);
           Button backward = (Button) findViewById(R.id.button_backward);
//            Button led_on = (Button) findViewById(R.id.led_on);
//            Button led_off = (Button) findViewById(R.id.led_off);


//        forward.setOnTouchListener(new View.OnTouchListener() {
//
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if (event.getAction() == MotionEvent.ACTION_DOWN) {
//                    new FeedTask().execute("up");
//
//                } else if (event.getAction() == MotionEvent.ACTION_UP) {
//                    new FeedTask().execute("null");
//                }
//
//                return true;
//            }
//        });

        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new FeedTask().execute("up");
                Log.i("Status: ", "up");

            }
        });

        backward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new FeedTask().execute("down");
                Log.i("Status: ", "down");

            }
        });
    }
}
