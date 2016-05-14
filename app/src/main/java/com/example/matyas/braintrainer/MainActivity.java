package com.example.matyas.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button startButton, button1, button2, button3, button4, playAgain;
    private TextView secondsLeft, numberView, result, score;
    private int firstNumber, secondNumber, sum, position, totalQuestions,correctQuestions ;
    private GridLayout buttons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = (Button)findViewById(R.id.startButton);

        secondsLeft = (TextView)findViewById(R.id.TimeCounter);
        numberView = (TextView)findViewById(R.id.numberToSum);
        result = (TextView)findViewById(R.id.resultTxtView);
        score = (TextView)findViewById(R.id.score);

        buttons = (GridLayout)findViewById(R.id.GridLayout);

        button1 = (Button)findViewById(R.id.button2);
        button2 = (Button)findViewById(R.id.button3);
        button3 = (Button)findViewById(R.id.button4);
        button4 = (Button)findViewById(R.id.button5);
        playAgain = (Button)findViewById(R.id.playAgainBtn);
    }

    public void generateNewQuiz(){

        firstNumber = (int)(Math.random() * 51);
        secondNumber = (int)(Math.random() * 51);
        position = (int)(Math.random() * 4+1);

        numberView.setText(firstNumber + " + " + secondNumber);


        if (position == 1){
            button1.setText(String.valueOf(firstNumber+secondNumber));
            button2.setText(String.valueOf((int)(Math.random() * 101)));
            button3.setText(String.valueOf((int)(Math.random() * 101)));
            button4.setText(String.valueOf((int)(Math.random() * 101)));
        }else if (position == 2){
            button1.setText(String.valueOf((int)(Math.random() * 101)));
            button2.setText(String.valueOf(firstNumber+secondNumber));
            button3.setText(String.valueOf((int)(Math.random() * 101)));
            button4.setText(String.valueOf((int)(Math.random() * 101)));
        }else if (position == 3){
            button1.setText(String.valueOf((int)(Math.random() * 101)));
            button2.setText(String.valueOf((int)(Math.random() * 101)));
            button3.setText(String.valueOf(firstNumber+secondNumber));
            button4.setText(String.valueOf((int)(Math.random() * 101)));
        }else if (position == 4){
            button1.setText(String.valueOf((int)(Math.random() * 101)));
            button2.setText(String.valueOf((int)(Math.random() * 101)));
            button4.setText(String.valueOf((int)(Math.random() * 101)));
            button3.setText(String.valueOf(firstNumber+secondNumber));
        }


    }
    public void start(View view){
        startButton.setVisibility(View.INVISIBLE);
        secondsLeft.setVisibility(View.VISIBLE);
        numberView.setVisibility(View.VISIBLE);
        buttons.setVisibility(View.VISIBLE);
        score.setVisibility(View.VISIBLE);
        playAgain.setVisibility(View.INVISIBLE);

        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);
        button4.setEnabled(true);

        result.setText("");

        if (totalQuestions != 0) {

            totalQuestions = 0;
            correctQuestions = 0;
            score.setText(correctQuestions + "/" + totalQuestions);

        }
        generateNewQuiz();

        new CountDownTimer(30000 + 100, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                secondsLeft.setText(millisUntilFinished / 1000 + "s");


            }

            @Override
            public void onFinish() {
                Log.d("End","Ended");
                result.setText("Your score: " + correctQuestions + "/" + totalQuestions);
                secondsLeft.setText(0 + "s");
                playAgain.setVisibility(View.VISIBLE);

                button1.setEnabled(false);
                button2.setEnabled(false);
                button3.setEnabled(false);
                button4.setEnabled(false);


            }
        }.start();
    }
    public void checkTheAnswer(View view){
        int pressedPosition = Integer.parseInt(view.getTag().toString());
        if (pressedPosition == position){
            totalQuestions++;
            correctQuestions++;
            result.setText("Correct!");
            result.setVisibility(View.VISIBLE);
            generateNewQuiz();
            score.setText(correctQuestions + "/" + totalQuestions);
        }else {
            totalQuestions++;
            result.setText("Not Correct!");
            result.setVisibility(View.VISIBLE);
            generateNewQuiz();
            score.setText(correctQuestions + "/" + totalQuestions);
        }
    }
}
