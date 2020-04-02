package com.example.braintrainerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
//    All the global variables being declared here...
    Button startGame, playAgain;
    TextView equation, response, timer, scoreCard, scoreDisplay;
    int[] arr;
    Button[] buttons;
    int a, b, total = 0, correct = 0;
    int maxRange = 25;
    int TimeForPlay = 30;
    ConstraintLayout gamerId, playAgainId;
//    The function that handle when the game is being started..
    public void startGame(View view){
        startGame.setVisibility(View.INVISIBLE);
        gamerId.setVisibility(View.VISIBLE);
        playAgainId.setVisibility(View.INVISIBLE);
        PlayAgain(view);
    }
//    This function allows user to quit at the moment
    public void quitnow(View view){
        finish();
        System.exit(0);
    }
//    This is the part that control main functioning of the app
    public void PlayAgain(View view){
        correct = 0;
        total = 0;
        timer.setText("30s");
        updateScoreCard();
        response.setText("");
        playAgainId.setVisibility(View.INVISIBLE);
        gamerId.setVisibility(View.VISIBLE);
        new CountDownTimer(TimeForPlay*1000+10, 1000) {
            @Override
            public void onTick(long l) {
                timer.setText(String.valueOf(l/1000) + 's');
            }
            @Override
            public void onFinish() {
                response.setText("Done !!");
                gamerId.setVisibility(View.INVISIBLE);
                playAgainId.setVisibility(View.VISIBLE);
                scoreDisplay.setText("You Scored: " + correct);
            }
        }.start();
    }
//    This function is used to set the question value in the app !!
    public void setQuestion(){
        Random rand = new Random();
        a = rand.nextInt(maxRange)+1;
        b = rand.nextInt(maxRange)+1;
        int res = rand.nextInt(4);
        equation.setText(a +"+"+ b);
        for(int i=0; i<4; i++){
            if(i == res)
                arr[i] = a+b;
            else {
                int answer = rand.nextInt(2*maxRange)+1;
                while(answer == a+b)
                    answer = rand.nextInt(2*maxRange)+1;
                arr[i] = answer;
            }
        }
        for(int i=0; i<4; i++)
            buttons[i].setText(String.valueOf(arr[i]));
    }
//    This function update the score card for the app
    public void updateScoreCard(){
        scoreCard.setText(correct+"/"+total);
    }
//    This is the solver part which check for the correctness of answer
    public void solver(View view) {
        int value = a + b;
        int val = arr[Integer.parseInt(view.getTag().toString())];
        if (val == value){
            response.setText("Correct :)");
            correct++;
        }else{
            response.setText("Wrong :(");
        }
        total++;
        updateScoreCard();
        setQuestion();
    }
//    This is the method that is automatically processed when the app is being started...
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // my creation start from here !!
        startGame = findViewById(R.id.startGame);
        equation = findViewById(R.id.question);
        timer = findViewById(R.id.timer);
        scoreCard = findViewById(R.id.scoreCard);
        buttons = new Button[4];
        arr = new int[4];
        buttons[0] = findViewById(R.id.button2);
        buttons[1] = findViewById(R.id.button3);
        buttons[2] = findViewById(R.id.button4);
        buttons[3] = findViewById(R.id.button5);
        response = findViewById(R.id.response);
        playAgain = findViewById(R.id.playAgain);
        playAgainId = findViewById(R.id.playId);
        gamerId = findViewById(R.id.gamerid);
        scoreDisplay = findViewById(R.id.scoreDisplay);
        setQuestion();
        //Adding the condition to the app
        startGame.setVisibility(View.VISIBLE);
        playAgainId.setVisibility(View.INVISIBLE);
        gamerId.setVisibility(View.INVISIBLE);
        // My creativity ends here
    }
}
