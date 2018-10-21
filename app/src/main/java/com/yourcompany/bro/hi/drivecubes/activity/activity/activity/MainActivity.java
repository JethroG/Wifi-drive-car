package com.yourcompany.bro.hi.drivecubes.activity.activity.activity;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.yourcompany.bro.hi.drivecubes.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button control_Car,stepper_Motor,draw_Line;
    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drive);
        control_Car= findViewById(R.id.button);
        stepper_Motor=findViewById(R.id.button2);
        draw_Line=findViewById(R.id.button3);

        control_Car.setId(1);
        stepper_Motor.setId(2);
        draw_Line.setId(3);

        control_Car.setOnClickListener(this);
        stepper_Motor.setOnClickListener(this);
        draw_Line.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case 1:
                Intent control_Car = new Intent(MainActivity.this,ControlActivity.class);
                startActivity(control_Car);
                break;
            case 2:
                Intent stepper_Motor = new Intent(MainActivity.this,StepperMotorActivity.class);
                startActivity(stepper_Motor);
                break;
            case 3:
                Intent draw_Line = new Intent(MainActivity.this,DrawLineActivity.class);
                startActivity(draw_Line);
                break;
        }

    }
}