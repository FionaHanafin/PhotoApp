package com.example.picturechange;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ImageView i;
    boolean stop = false;
    Button pre, next;
    int x = 0;
    ToggleButton slide;
    Handler mHandler;
    int[] images = {(R.drawable.accross), (R.drawable.avicii_hey_brother), (R.drawable.break_free), (R.drawable.chasing_pavements), (R.drawable.good_girls)};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        i = findViewById(R.id.b1);
        mHandler = new Handler();
        i.setImageResource(R.drawable.accross);
        pre = findViewById(R.id.pre);
        next = findViewById(R.id.next);
        slide = findViewById(R.id.slide);


        pre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int x = Integer.parseInt(i.getContentDescription().toString());
                Log.d("***********x", String.valueOf(x));
                if (x > 0) {
                    i.setImageResource(images[x - 1]);
                    i.setContentDescription(String.valueOf(x - 1));
                    Log.d("***********x", String.valueOf(x - 1));
                } else if (x == 0) {
                    i.setContentDescription(String.valueOf(images.length - 1));
                    i.setImageResource(images[images.length - 1]);
                    Log.d("***********x", String.valueOf(images.length - 1));
                }
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int x = Integer.parseInt(i.getContentDescription().toString());
                if (x < images.length - 1) {
                    Log.d("***********x", String.valueOf(x));
                    i.setImageResource(images[x + 1]);
                    Log.d("***********x", String.valueOf(x + 1));
                    i.setContentDescription(String.valueOf(x + 1));
                } else if (x == images.length - 1) {
                    i.setImageResource(images[0]);
                    i.setContentDescription(String.valueOf(0));
                    Log.d("***********x", String.valueOf(0));
                }
            }
        });
        final Runnable[] r = new Runnable[1];
        slide.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    r[0] = new Runnable() {

                        @Override
                        public void run() {
                            i.setImageResource(images[x]);
                            i.setContentDescription(String.valueOf(x));
                            x++;
                            if(x>images.length-1){ x = 0;}
                            mHandler.postDelayed(this,500);
                        }
                    };
                    mHandler.postDelayed(r[0],500);
                }else {
                    mHandler.removeCallbacks(r[0]);
                }
            }
        });
    }
}
