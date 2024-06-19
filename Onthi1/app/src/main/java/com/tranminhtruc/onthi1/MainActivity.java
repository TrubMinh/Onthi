package com.tranminhtruc.onthi1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.tranminhtruc.onthi1.databinding.ActivityMainBinding;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    int RandNumb, numb;
    Random random = new Random();
    Handler handler = new Handler();
    Runnable foregroundThread = new Runnable() {
        @Override
        public void run() {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 100, 1);
            params.setMargins(10, 10, 10, 10);
            EditText editText = new EditText(MainActivity.this);
            Button button = new Button(MainActivity.this);
            if (numb % 2 == 0) {
                editText.setLayoutParams(params);
                editText.setText(String.valueOf(RandNumb));
                editText.setTextColor(Color.BLACK);
                editText.setTextSize(26);
                binding.containerLayout.addView(editText);
            } else {
                button.setLayoutParams(params);
                button.setText(String.valueOf(RandNumb));
                button.setTextColor(Color.BLACK);
                button.setGravity(Gravity.CENTER);
                button.setTextSize(26);
                binding.containerLayout.addView(button);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        addEvent();
    }

    private void addEvent() {
        binding.btnDraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                execRunningTask();
            }
        });
        binding.btncau2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this,MainActivity2.class);
            startActivity(intent);
            }
        });
    }

    private void execRunningTask() {
        binding.containerLayout.removeAllViews();
        int numbOfViews = Integer.parseInt(binding.edtinput.getText().toString());
        Thread BackgroundThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i < numbOfViews; i++) {
                    numb = i;
                    RandNumb = random.nextInt(100);
                    handler.post(foregroundThread);
                    SystemClock.sleep(100);
                }
            }
        });
        BackgroundThread.start();
    }
}
