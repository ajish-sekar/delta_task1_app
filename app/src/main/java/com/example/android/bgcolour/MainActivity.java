package com.example.android.bgcolour;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int red = 0;
    int green = 0;
    int blue = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("Prefs",MODE_PRIVATE);

        red = preferences.getInt("red", 0);
        green = preferences.getInt("green", 0);
        blue = preferences.getInt("blue", 0);
        setColour();

        Button redButton = (Button) findViewById(R.id.red_button);
        redButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                red = increment(red);
                setColour();
            }
        });

        Button greenButton = (Button) findViewById(R.id.green_button);
        greenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                green = increment(green);
                setColour();
            }
        });

        Button blueButton = (Button) findViewById(R.id.blue_button);
        blueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blue = increment(blue);
                setColour();
            }
        });

        Button reset = (Button) findViewById(R.id.reset);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                red = 0;
                blue = 0;
                green = 0;
                setColour();
            }
        });


    }

    public int increment(int value){
        value+=5;
        if(value>255)
            value = 0;
        return value;
    }

    public void setColour(){
        RelativeLayout backGround = (RelativeLayout) findViewById(R.id.activity_main);

        backGround.setBackgroundColor(Color.rgb(red,green,blue));

        TextView redValue = (TextView) findViewById(R.id.red_value);
        redValue.setText(red+"");
        TextView greenValue = (TextView) findViewById(R.id.green_value);
        greenValue.setText(green+"");
        TextView blueValue = (TextView) findViewById(R.id.blue_value);
        blueValue.setText(blue+"");
        redValue.setTextColor(Color.rgb(255-red,255-green,255-blue));
        greenValue.setTextColor(Color.rgb(255-red,255-green,255-blue));
        blueValue.setTextColor(Color.rgb(255-red,255-green,255-blue));
    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("Prefs",MODE_PRIVATE);

        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("red", red);
        editor.putInt("green", green);
        editor.putInt("blue", blue);
        editor.commit();
    }
}
