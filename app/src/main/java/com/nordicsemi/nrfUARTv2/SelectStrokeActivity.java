package com.nordicsemi.nrfUARTv2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SelectStrokeActivity extends Activity {
    Button buttonNormal, buttonPush, buttonPull, buttonHit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectstroke);

        buttonHit = (Button) findViewById(R.id.buttonHit);
        buttonNormal = (Button) findViewById(R.id.buttonNormal);
        buttonPush = (Button) findViewById(R.id.buttonPush);
        buttonPull = (Button) findViewById(R.id.buttonPull);

        buttonHit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectStrokeActivity.this, HitActivity.class);
                startActivity(intent);
            }
        });
        buttonNormal.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectStrokeActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        buttonPush.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectStrokeActivity.this, Main2Activity.class);
                startActivity(intent);
            }
        });
        buttonPull.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectStrokeActivity.this, Main3Activity.class);
                startActivity(intent);
            }
        });
    }
}
