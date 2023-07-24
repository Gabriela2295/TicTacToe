package com.example.tictactoe;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class TicTacToeActivity extends AppCompatActivity {

    private ImageView backgroundImageView;
    private AnimationDrawable backgroundAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_tac_toe);

        // Initialize views
        backgroundImageView = findViewById(R.id.background_image_view);
        Button startAgainButton = findViewById(R.id.switchButton);
        Button interface2Button = findViewById(R.id.switchButton);
        Button interface3Button = findViewById(R.id.switchButton);

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
                // Reload the activity to start over
                recreate();
            }
        });

        // Set click listener for interface 2 button
        interface2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Switch to interface 2 layout
                switchToInterface2();
            }
        });

        // Set click listener for interface 3 button
        interface3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Switch to interface 3 layout
                switchToInterface3();
            }
        });

        // Start or stop animation based on current state
        if (backgroundAnimation.isRunning()) {
            backgroundAnimation.stop();
        } else {
            backgroundAnimation.start();
        }
    }

    public void switchToInterface2() {
        setContentView(R.layout.interface2);
    }

    public void switchToInterface3() {
        setContentView(R.layout.activity);
    }
}
