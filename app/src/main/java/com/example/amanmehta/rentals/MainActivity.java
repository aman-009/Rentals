package com.example.amanmehta.rentals;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    ImageView iv;
    TextView tv;
    Animation mytrans;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv = (ImageView) findViewById(R.id.imageView);
        tv = (TextView) findViewById(R.id.textView);
        mytrans = AnimationUtils.loadAnimation(this,R.anim.mytransition);
        tv.startAnimation(mytrans);
        iv.startAnimation(mytrans);

        // feature to be include **if already existing then send to main page directly**
        final Intent i = new Intent(this, Home.class);
        Thread timer = new Thread(){
          public void run(){
              try{
                  sleep(1000);
              }
              catch (InterruptedException e){
                  e.printStackTrace();
              }
              finally {
                  startActivity(i);
                  finish();
              }
          }
        };
        timer.start();
    }
}
