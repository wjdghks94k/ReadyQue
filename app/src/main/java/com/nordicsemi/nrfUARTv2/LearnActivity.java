package com.nordicsemi.nrfUARTv2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LearnActivity extends Activity {
    Button buttonPosition, buttonBall, buttonCamera, buttonMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn);

        buttonPosition = (Button) findViewById(R.id.buttonPosition);
        buttonBall = (Button) findViewById(R.id.buttonBall);
        buttonCamera = (Button) findViewById(R.id.buttonCamera);
        buttonMap = (Button) findViewById(R.id.buttonMap);

        buttonPosition.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LearnActivity.this, SampleActivity.class);
                startActivity(intent);
            }
        });
        buttonBall.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LearnActivity.this, PositionActivity.class);
                startActivity(intent);
            }
        });
        buttonCamera.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LearnActivity.this, CameraActivity.class);
                startActivity(intent);
            }
        });
        buttonMap.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LearnActivity.this, MapActivity.class);
                startActivity(intent);
            }
        });
    }
}
