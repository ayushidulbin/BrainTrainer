package com.example.ayushi.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button startButton;
    TextView resultTextView;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    TextView sumTextView;
    TextView timerTextView;
    TextView pointerTextView;
    Button playAgainButton;
    RelativeLayout gameRelativeLayout;
    ArrayList<Integer> answer=new ArrayList<Integer>();
    int locationOfCorrectAnswers;
    int score=0;
    int numberOfQues=0;

    public void playAgain(View view){

        score=0;
        numberOfQues=0;
        pointerTextView.setText("0/0");
        timerTextView.setText("0s");
        resultTextView.setText("");
        playAgainButton.setVisibility(View.INVISIBLE);
        generateQuestion();
        new CountDownTimer(30100,1000){

            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(String.valueOf(millisUntilFinished/1000)+"s");
            }

            @Override
            public void onFinish() {
                playAgainButton.setVisibility(View.VISIBLE);
                timerTextView.setText("0s");
                resultTextView.setText("Your Score:"+Integer.toString(score)+"/"+Integer.toString(numberOfQues));

            }
        }.start();
    }


    public void generateQuestion(){

        Random rand=new Random();
        int a=rand.nextInt(21);
        int b=rand.nextInt(21);
        sumTextView.setText(Integer.toString(a)+"+"+Integer.toString(b));
        locationOfCorrectAnswers=rand.nextInt(4);
        int incorrectAnswer;
        answer.clear();
        for(int i=0;i<4;i++){
            if(i==locationOfCorrectAnswers){
                answer.add(a+b);
            }else{
                incorrectAnswer=rand.nextInt(41);
                while(incorrectAnswer==a+b){
                    incorrectAnswer=rand.nextInt(41);
                }
                answer.add(incorrectAnswer);
            }
        }
        button0.setText(Integer.toString(answer.get(0)));
        button1.setText(Integer.toString(answer.get(1)));
        button2.setText(Integer.toString(answer.get(2)));
        button3.setText(Integer.toString(answer.get(3)));

    }

    public void chooseAnswer(View view){
        if(view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswers))){
            score++;
            resultTextView.setText("Correct");
        }else{
            resultTextView.setText("Wrong");
        }
        numberOfQues++;
        pointerTextView.setText(Integer.toString(score)+"/"+Integer.toString(numberOfQues));
        generateQuestion();

    }

    public void start(View view){
        startButton.setVisibility(View.INVISIBLE);
        gameRelativeLayout.setVisibility(View.VISIBLE);
        playAgain(findViewById(R.id.playAgainButton));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startButton=(Button)findViewById(R.id.startButton);
        sumTextView=(TextView)findViewById(R.id.sumTextView);
         button0=(Button)findViewById(R.id.button0);
         button1=(Button)findViewById(R.id.button1);
         button2=(Button)findViewById(R.id.button2);
         button3=(Button)findViewById(R.id.button3);

        resultTextView=(TextView)findViewById(R.id.resultTextView);
        pointerTextView=(TextView)findViewById(R.id.pointerTextView);
        timerTextView=(TextView)findViewById(R.id.timerTextView);
        playAgainButton=(Button)findViewById(R.id.playAgainButton);
        gameRelativeLayout=(RelativeLayout)findViewById(R.id.gameRelativeLayout) ;




    }
}
