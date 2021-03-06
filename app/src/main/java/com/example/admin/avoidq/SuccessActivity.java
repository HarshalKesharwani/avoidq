package com.example.admin.avoidq;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.admin.avoidq.R;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class SuccessActivity extends AppCompatActivity {
    Timer timer;
    int images[] = {R.drawable.back1, R.drawable.back4, R.drawable.back5};
    RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);
        relativeLayout=(RelativeLayout)findViewById(R.id.parents);
//at the beginning background color is red and it will keep changing every second
        // relativeLayout.setBackgroundColor(Color.RED);

        relativeLayout.setBackgroundResource(R.drawable.back3);

        timer=new Timer();

        MyTimerTask myTimerTask=new MyTimerTask();
//schedule to change background color every second
        timer.schedule(myTimerTask,1500,2500);

        TextView textView1 = (TextView)findViewById(R.id.viewaddress);

        Intent intent = getIntent();
        String str = intent.getStringExtra("email");

        textView1.setText("Hello " + str);



    }
    public class MyTimerTask extends TimerTask {

        @Override
        public void run() {
//Since we want to change something which is on hte UI
//so we have to run on UI thread..
            runOnUiThread(new Runnable() {
                @Override
                public void run()
                {

                    Random random=new Random();//this is random generator
                    //  for(int i=0;i<500;i++) {
                    //System.out.println("***"+i);
                    relativeLayout.setBackgroundResource(images[random.nextInt(3)]);
                    //System.out.println("***"+((random.nextInt(1))%1000000000)%3);
                    System.out.println("no"+random.nextInt(3));
                    // }
                    //  relativeLayout.setBackgroundColor(Color.rgb(random.nextInt(256),random.nextInt(256),random.nextInt(256)));
                }
            });
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
//you have to stop the timer when is your activity has stopped
//otherwise it will keep running in the background
        timer.cancel();
    }
}