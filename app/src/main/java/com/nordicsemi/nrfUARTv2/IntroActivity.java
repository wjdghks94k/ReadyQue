package com.nordicsemi.nrfUARTv2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class IntroActivity extends Activity {
    Button buttonMyposition,buttonLearn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        buttonMyposition = (Button) findViewById(R.id.buttonMyposition);
        buttonLearn = (Button) findViewById(R.id.buttonLearn);

        buttonMyposition.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(IntroActivity.this, SelectStrokeActivity.class);
                startActivity(intent);
            }
        });
        buttonLearn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(IntroActivity.this, LearnActivity.class);
                startActivity(intent);
            }
        });
    }

}
