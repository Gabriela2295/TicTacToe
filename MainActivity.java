package com.example.tictactoe;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tictactoe.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private final List<int[]> combinationList = new ArrayList<>();
    private int[] boxPositions = {0, 0, 0, 0, 0, 0, 0, 0, 0}; //9 zero
    private int playerTurn = 1;
    private int totalSelectedBoxes = 1;

    int animationCount = 0;
    ImageView imageView;
    AnimationDrawable animationStars, animationChange;
    int[] animationArray = new int[]{R.drawable.animation_stars,R.drawable.animation_universe, R.drawable.animation_city, R.drawable.animation_blue, R.drawable.animation_fly, R.drawable.animation_hotd, R.drawable.animation_fish, R.drawable.animation_desert, R.drawable.animation_stars};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        combinationList.add(new int[]{0, 1, 2});
        combinationList.add(new int[]{3, 4, 5});
        combinationList.add(new int[]{6, 7, 8});
        combinationList.add(new int[]{0, 3, 6});
        combinationList.add(new int[]{1, 4, 7});
        combinationList.add(new int[]{2, 5, 8});
        combinationList.add(new int[]{2, 4, 6});
        combinationList.add(new int[]{0, 4, 8});

        String getPlayerOneName = getIntent().getStringExtra("playerOne");
        String getPlayerTwoName = getIntent().getStringExtra("playerTwo");

        binding.playerOneName.setText(getPlayerOneName);
        binding.playerTwoName.setText(getPlayerTwoName);

        // Find the ImageView in the layout
        imageView = binding.myImageView;

        // Create the AnimationDrawable object
        animationStars = (AnimationDrawable) getResources().getDrawable(R.drawable.animation_stars);

        // Set the animation as the drawable for the ImageView
        imageView.setImageDrawable(animationStars);

        // Start the animation
        animationStars.start();

        binding.image1.setOnClickListener(view -> {
            if (isBoxSelectable(0)) {
                performAction((ImageView) view, 0);
            }
        });

        binding.image2.setOnClickListener(view -> {
            if (isBoxSelectable(1)) {
                performAction((ImageView) view, 1);
            }
        });
        binding.image3.setOnClickListener(view -> {
            if (isBoxSelectable(2)) {
                performAction((ImageView) view, 2);
            }
        });
        binding.image4.setOnClickListener(view -> {
            if (isBoxSelectable(3)) {
                performAction((ImageView) view, 3);
            }
        });
        binding.image5.setOnClickListener(view -> {
            if (isBoxSelectable(4)) {
                performAction((ImageView) view, 4);
            }
        });
        binding.image6.setOnClickListener(view -> {
            if (isBoxSelectable(5)) {
                performAction
                        ((ImageView) view, 5);
            }
        });
        binding.image7.setOnClickListener(view -> {
            if (isBoxSelectable(6)) {
                performAction((ImageView) view, 6);
            }
        });
        binding.image8.setOnClickListener(view -> {
            if (isBoxSelectable(7)) {
                performAction((ImageView) view, 7);
            }
        });
        binding.image9.setOnClickListener(view -> {
            if (isBoxSelectable(8)) {
                performAction((ImageView) view, 8);
            }
        });

    }

    private void performAction(ImageView imageView, int selectedBoxPosition) {
        boxPositions[selectedBoxPosition] = playerTurn;

        if (playerTurn == 1) {
            imageView.setImageResource(R.drawable.ximage);
            Animation animation = AnimationUtils.loadAnimation(this, R.anim.scale_up);
            imageView.startAnimation(animation);
            if (checkResults()) {
                ResultDialog resultDialog = new ResultDialog(MainActivity.this, binding.playerOneName.getText().toString()
                        + " is a Winner!", MainActivity.this);
                resultDialog.setCancelable(false);
                resultDialog.show();
            } else if (totalSelectedBoxes == 9) {
                ResultDialog resultDialog = new ResultDialog(MainActivity.this, "Match Draw", MainActivity.this);
                resultDialog.setCancelable(false);
                resultDialog.show();
            } else {
                changePlayerTurn(2);
                totalSelectedBoxes++;
            }
        } else {
            imageView.setImageResource(R.drawable.oimage);
            Animation animation = AnimationUtils.loadAnimation(this, R.anim.scale_up);
            imageView.startAnimation(animation);
            if (checkResults()) {
                ResultDialog resultDialog = new ResultDialog(MainActivity.this, binding.playerTwoName.getText().toString()
                        + " is a Winner!", MainActivity.this);
                resultDialog.setCancelable(false);
                resultDialog.show();
            } else if (totalSelectedBoxes == 9) {
                ResultDialog resultDialog = new ResultDialog(MainActivity.this, "Match Draw", MainActivity.this);
                resultDialog.setCancelable(false);
                resultDialog.show();
            } else {
                changePlayerTurn(1);
                totalSelectedBoxes++;
            }
        }
    }

    private void changePlayerTurn(int currentPlayerTurn) {
        playerTurn = currentPlayerTurn;
        if (playerTurn == 1) {
            binding.playerOneLayout.setBackgroundResource(R.drawable.transparent_image);
            binding.playerTwoLayout.setBackgroundResource(R.drawable.transparent_image);
        } else {
            binding.playerTwoLayout.setBackgroundResource(R.drawable.transparent_image);
            binding.playerOneLayout.setBackgroundResource(R.drawable.transparent_image);
        }
    }

    private boolean checkResults() {
        boolean response = false;
        for (int i = 0; i < combinationList.size(); i++) {
            final int[] combination = combinationList.get(i);

            if (boxPositions[combination[0]] == playerTurn && boxPositions[combination[1]] == playerTurn &&
                    boxPositions[combination[2]] == playerTurn) {
                response = true;
            }
        }
        return response;
    }

    private boolean isBoxSelectable(int boxPosition) {
        boolean response = false;
        if (boxPositions[boxPosition] == 0) {
            response = true;
        }
        return response;
    }

    public void restartMatch() {
        // todo change animation
        if (!(animationCount == animationArray.length)) {
            animationCount++;
        } else {
            animationCount = 0;
        }
        animationChange = (AnimationDrawable) getResources().getDrawable(animationArray[animationCount]);
        imageView.setImageDrawable(animationChange);
        animationChange.start();

        boxPositions = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0}; //9 zero
        playerTurn = 1;
        totalSelectedBoxes = 1;

        binding.image1.setImageResource(R.drawable.transparent_image);
        binding.image2.setImageResource(R.drawable.transparent_image);
        binding.image3.setImageResource(R.drawable.transparent_image);
        binding.image4.setImageResource(R.drawable.transparent_image);
        binding.image5.setImageResource(R.drawable.transparent_image);
        binding.image6.setImageResource(R.drawable.transparent_image);
        binding.image7.setImageResource(R.drawable.transparent_image);
        binding.image8.setImageResource(R.drawable.transparent_image);
        binding.image9.setImageResource(R.drawable.transparent_image);
    }


}