package com.nordicsemi.nrfUARTv2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class Youtube3Activity extends YouTubeBaseActivity {
    YouTubePlayerView youtubeView1;
    Button button1;
    YouTubePlayer.OnInitializedListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube3);
        button1 = (Button) findViewById(R.id.button1);
        youtubeView1 = (YouTubePlayerView) findViewById(R.id.youtubeView1);

        listener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideo("E-ixCfD4C6s"); //youtube주소의 watch?v= 뒤에있는 아이디 적기
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };
        button1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                youtubeView1.initialize("AIzaSyDlQS0lNHm1vAEn7LYDDvHYHP7fYlwqGsk", listener);
            }
        });
    }
}
