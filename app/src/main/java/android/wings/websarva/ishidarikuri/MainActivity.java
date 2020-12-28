package android.wings.websarva.ishidarikuri;

import androidx.appcompat.app.AppCompatActivity;
/*import android.support.v7.app.AppCompatActivity;*/
import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.os.CountDownTimer;
import android.widget.EditText;
import android.os.Bundle;

import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import android.os.Handler;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Button;




public class MainActivity extends AppCompatActivity {

    private static long START_TIME ;
    private TextView mTextViewCountDown;
    private Button mButtonStartPause;
    private  Button getmButtonReset;
    private Button mButtonten;
    private Button mButtontwenty;
    private Button mButtonthirty;


    private CountDownTimer mCountDownTimer;
    private boolean mTimerRunning;

    private long mTimeLeftInMills = START_TIME;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("mTimerRunningの初期値は？"+mTimerRunning);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextViewCountDown = findViewById(R.id.textView_countdown);
        mButtonStartPause = findViewById(R.id.button1_start_pause);
        getmButtonReset = findViewById(R.id.button2_reset);
        mButtonten = findViewById(R.id.button3_10minutes);
        mButtontwenty = findViewById(R.id.button4_20minutes);
        mButtonthirty = findViewById(R.id.button5_30minutres);

        mButtonStartPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("mTimerRunningの値は？"+mTimerRunning);
                if (mTimerRunning) {
                    pauseTimer();
                } else {
                    startTimer();
                }
            }
        });

        getmButtonReset.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                resetTimer();
            }

        });
        updateCountDownText();
        mButtonten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tenminutes();
            }
        });
        mButtontwenty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                twentyminutes();
            }
        });
        mButtonthirty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thirtyminutes();
            }
        });


    }

    private void startTimer(){
        mCountDownTimer = new CountDownTimer(mTimeLeftInMills,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMills = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                mTimerRunning = false;
                mButtonStartPause.setText("スタート");
                getmButtonReset.setVisibility(View.VISIBLE);

            }
        }.start();

        mTimerRunning = true;
        mButtonStartPause.setText("一時停止");
        getmButtonReset.setVisibility(View.INVISIBLE);
    }
    private void pauseTimer(){
        System.out.println("一時停止処理前のmTimerRunningは？:"+ mTimerRunning);
        mCountDownTimer.cancel();
        mTimerRunning = false;
        System.out.println("一時停止処理後のmTimerRunningは？"+mTimerRunning);
        mButtonStartPause.setText("スタート");
        getmButtonReset.setVisibility(View.VISIBLE);
    }
    private void tenminutes() {
        mTimeLeftInMills =  600000;
        START_TIME = 600000;
        updateCountDownText();
    }
    private void stopTimer() {
        mCountDownTimer.cancel();
    }

    private void twentyminutes() {
        mTimeLeftInMills = 1200000;
        START_TIME = 1200000;
        updateCountDownText();
    }
    private void thirtyminutes() {
        mTimeLeftInMills = 1800000;
        START_TIME = 1800000;

        updateCountDownText();
    }


    private void resetTimer(){
        mTimeLeftInMills = START_TIME;
        updateCountDownText();
        mButtonStartPause.setVisibility(View.VISIBLE);
        getmButtonReset.setVisibility(View.INVISIBLE);
    }


    private void updateCountDownText(){
        int minutes = (int)(mTimeLeftInMills/1000)/60;
        int seconds = (int)(mTimeLeftInMills/1000)%60;
        String timerLeftFormatted = String.format(Locale.getDefault(),"%02d:%02d",minutes,seconds);
        mTextViewCountDown.setText(timerLeftFormatted);

    }
}


