package com.example.tictactoe;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class TicTacToeActivity2 extends AppCompatActivity {

    private ImageView backgroundImageView;
    private AnimationDrawable backgroundAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.interface2);

        // Initialize views
        backgroundImageView = findViewById(R.id.background_image_view);
        Button startAgainButton = findViewById(R.id.start_again_button);

        // Create animation drawable from PNG sequence
        backgroundAnimation = new AnimationDrawable();
        backgroundAnimation.addFrame(getResources().getDrawable(R.drawable.stars_00035), 200);
        backgroundAnimation.addFrame(getResources().getDrawable(R.drawable.stars_00036), 200);
        backgroundAnimation.addFrame(getResources().getDrawable(R.drawable.stars_00037), 200);
        backgroundAnimation.addFrame(getResources().getDrawable(R.drawable.stars_00038), 200);
        // Add more frames for each PNG in your sequence

        // Set animation drawable as background of image view
        backgroundImageView.setImageDrawable(backgroundAnimation);

        // Set click listener for start again button
        startAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start TicTacToeActivity activity
                Intent intent = new Intent(TicTacToeActivity2.this, activity.class);
                startActivity(intent);
            }
        });

        // Start or stop animation based on current state
        if (backgroundAnimation.isRunning()) {
            backgroundAnimation.stop();
        } else {
            backgroundAnimation.start();
        }
    }
}
