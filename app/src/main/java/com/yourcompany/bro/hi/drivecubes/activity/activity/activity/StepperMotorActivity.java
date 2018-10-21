package com.yourcompany.bro.hi.drivecubes.activity.activity.activity;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.yourcompany.bro.hi.drivecubes.R;
import com.yourcompany.bro.hi.drivecubes.activity.activity.adapter.HomeAdapter;
import com.yourcompany.bro.hi.drivecubes.activity.activity.model.Item;

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
import java.util.ArrayList;




public class StepperMotorActivity extends AppCompatActivity implements HomeAdapter.ItemListener {

    private Button moveUp, moveDown,moveLeft, moveRight,sendData,cleanDash;
    private RecyclerView recyclerView;
    private ArrayList<Item> arrayList;
    private ArrayList<String> doNext = new ArrayList<String>();
    private EditText ipAdr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setContentView(R.layout.activity_stepper_motor);
        setTitle("Stepper Motor");
        recyclerView = findViewById(R.id.recyclerView);
        arrayList = new ArrayList<>();
        moveUp= findViewById(R.id.up_move);
        moveDown= findViewById(R.id.down_move);
        moveRight=findViewById(R.id.right_move);
        moveLeft= findViewById(R.id.left_move);
        sendData=findViewById(R.id.sendData);
        cleanDash=findViewById(R.id.clearRecyclerView);
        ipAdr= findViewById(R.id.ipAdress);
        final HomeAdapter adapter = new HomeAdapter(this, arrayList, this);
        moveUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayList.add(new Item("Up", R.drawable.up, "#09A9FF"));
                recyclerView.setAdapter(adapter);
                Log.i("Status"," Click Up");
                doNext.add("up");
            }
        });
        moveDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayList.add(new Item("Down", R.drawable.down, "#3E51B1"));
                recyclerView.setAdapter(adapter);
                Log.i("Status"," Click Down");
                doNext.add("down");
            }
        });
        moveRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayList.add(new Item("Right", R.drawable.right, "#673BB7"));
                recyclerView.setAdapter(adapter);
                Log.i("Status"," Click Right");
                doNext.add("right");
            }
        });
        moveLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayList.add(new Item("Left", R.drawable.left, "#4BAA50"));
                recyclerView.setAdapter(adapter);
                Log.i("Status"," Click Left");
                doNext.add("left");
            }
        });
        sendData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String serverAdress = ipAdr.getText().toString() + ":" + "80";
//                HttpRequestTask requestTask = new HttpRequestTask(serverAdress);
//                requestTask.execute(String.valueOf(doNext));
                Log.i("Resualt", doNext.toString());
//                Log.i("Resualt", requestTask.toString());


            }
        });
        cleanDash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doNext.clear();
                arrayList.clear();
                recyclerView.setAdapter(null);
                Log.i("Status", doNext.toString());
            }
        });



        /**
         AutoFitGridLayoutManager that auto fits the cells by the column width defined.
         **/

        //AutoFitGridLayoutManager layoutManager = new AutoFitGridLayoutManager(this, 500);
        //recyclerView.setLayoutManager(layoutManager);


        /**
         Simple GridLayoutManager that spans two columns
         **/
        GridLayoutManager manager = new GridLayoutManager(this, 10, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
    }

    @Override
    public void onItemClick(Item item) {
        Toast.makeText(getApplicationContext(), item.text + " is clicked", Toast.LENGTH_SHORT).show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
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
            final String url = "http://" + serverAdress + "/stepper_motor/" + val;

            try {
                HttpClient client = new DefaultHttpClient();
                HttpGet getRequest = new HttpGet();
                getRequest.setURI(new URI(url));
                HttpResponse response = client.execute(getRequest);

                InputStream inputStream = null;
                inputStream = response.getEntity().getContent();
                BufferedReader bufferedReader =
                        new BufferedReader(new InputStreamReader(inputStream));

                serverResponse = bufferedReader.readLine();
                inputStream.close();

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

        @Override
        protected void onPostExecute(String s) {

        }

        @Override
        protected void onPreExecute() {

        }
    }
}
