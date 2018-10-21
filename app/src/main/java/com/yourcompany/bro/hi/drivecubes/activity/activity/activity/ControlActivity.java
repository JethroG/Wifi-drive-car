
package com.yourcompany.bro.hi.drivecubes.activity.activity.activity;


import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;


import com.yourcompany.bro.hi.drivecubes.R;

public class ControlActivity extends AppCompatActivity {

    private EditText ipAddress;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setContentView(R.layout.activity_control);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Button up = findViewById(R.id.up_move_first_tab);
        Button down = findViewById(R.id.down_move_first_tab);
        Button right = findViewById(R.id.right_move_first_tab);
        Button left = findViewById(R.id.left_move_first_tab);
        ipAddress= findViewById(R.id.editText);

        final int[] i = {0};
        final int[] j = {0};
        final int[] k = {0};
        final int[] l = {0};


        up.setOnTouchListener(new View.OnTouchListener() {
        String carStatus = null;
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    //вперед рух
                    case MotionEvent.ACTION_DOWN:
                        if(i[0] ==0){
                            // нажатие
                            carStatus = "up";
                            Log.i("Status: ", carStatus);

                        }else {
                            i[0]++;
                        }
                        break;

                    case MotionEvent.ACTION_UP: // отпускание
                        carStatus = "null";
                        i[0] =0;
                       Log.i("Status: ", carStatus);

                        break;
                }

                String serverAdress = ipAddress.getText().toString() + ":" + "80";
                HttpRequestTask requestTask = new HttpRequestTask(serverAdress);
                requestTask.execute(carStatus);

                return true;
            }
        });

        down.setOnTouchListener(new View.OnTouchListener() {
            String carStatus = null;
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    // назад рух
                    case MotionEvent.ACTION_DOWN:
                        if(j[0] ==0){
                            // нажатие
                            carStatus = "down";
                            Log.i("Status: ", carStatus);
                        }else {
                            j[0]++;
                        }
                        break;

                    case MotionEvent.ACTION_UP: // отпускание
                        carStatus = "null";
                        Log.i("Status: ", carStatus);
                        j[0] =0;
                        break;
                }
                String serverAdress = ipAddress.getText().toString() + ":" + "80";
                HttpRequestTask requestTask = new HttpRequestTask(serverAdress);
                requestTask.execute(carStatus);
                return true;
            }
        });

        right.setOnTouchListener(new View.OnTouchListener() {
            String carStatus = null;
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    // назад вправо
                    case MotionEvent.ACTION_DOWN:
                        if(k[0] ==0){
                            // нажатие
                            carStatus = "right";
                            Log.i("Status: ", carStatus);
                        }else {
                            k[0]++;
                        }
                        break;

                    case MotionEvent.ACTION_UP: // отпускание
                        carStatus = "null";
                        Log.i("Status: ", carStatus);
                        k[0] =0;
                        break;
                }
                String serverAdress = ipAddress.getText().toString() + ":" + "80";
                HttpRequestTask requestTask = new HttpRequestTask(serverAdress);
                requestTask.execute(carStatus);
                return true;
            }
        });

        left.setOnTouchListener(new View.OnTouchListener() {
            String carStatus = null;
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    // рух вліво
                    case MotionEvent.ACTION_DOWN:
                        if(l[0] ==0){
                            // нажатие
                            carStatus = "left";
                            Log.i("Status: ", carStatus);
                        }else {
                            l[0]++;
                        }
                        break;

                    case MotionEvent.ACTION_UP: // отпускание
                        carStatus = "null";
                        Log.i("Status: ", carStatus);
                        l[0]=0;
                        break;
                }
                String serverAdress = ipAddress.getText().toString() + ":" + "80";
                HttpRequestTask requestTask = new HttpRequestTask(serverAdress);
                requestTask.execute(carStatus);
                return true;
            }
        });

    }



    class HttpRequestTask extends AsyncTask<String, Void, String> {
        private String serverAdress;
        private String serverResponse = "";


        public HttpRequestTask(String serverAdress) {
            this.serverAdress = serverAdress;

        }

        @Override
        protected String doInBackground(String... params) {

            String val = params[0];
            final String url = "http://" + serverAdress + "/car/" + val;

            try {
                HttpClient client = new DefaultHttpClient();
                HttpGet getRequest = new HttpGet();
                getRequest.setURI(new URI(url));
                HttpResponse response = client.execute(getRequest);

//                InputStream inputStream = null;
//                inputStream = response.getEntity().getContent();
//                BufferedReader bufferedReader =
//                        new BufferedReader(new InputStreamReader(inputStream));
//
//                serverResponse = bufferedReader.readLine();
//                inputStream.close();

            } catch (URISyntaxException e) {
                e.printStackTrace();
                serverResponse = e.getMessage();
            } catch (ClientProtocolException e) {
                e.printStackTrace();
                serverResponse = e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
                serverResponse = e.getMessage();
            }

            return serverResponse;
        }


    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


}
