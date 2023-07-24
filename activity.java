package com.example.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class activity extends AppCompatActivity {

    private ImageView backgroundImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity);

        // Initialize views
        backgroundImageView = findViewById(R.id.background_image_view2);
        Button startAgainButton = findViewById(R.id.start_again_button);

        // Set click listener for start again button
        startAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start TicTacToeActivity2 activity
                Intent intent = new Intent(activity.this, TicTacToeActivity.class);
                startActivity(intent);
            }
        });
    }
}
