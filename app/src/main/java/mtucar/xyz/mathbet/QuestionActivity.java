package mtucar.xyz.mathbet;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import mtucar.xyz.mathbet.classes.Player;
import mtucar.xyz.mathbet.classes.Question;
import mtucar.xyz.mathbet.classes.QuestionDivide;
import mtucar.xyz.mathbet.classes.QuestionMinus;
import mtucar.xyz.mathbet.classes.QuestionMultiply;
import mtucar.xyz.mathbet.classes.QuestionPlus;
import mtucar.xyz.mathbet.classes.QuestionSet;
import mtucar.xyz.mathbet.data.PlayerData;

public class QuestionActivity extends AppCompatActivity {

    private static int counter = 1;
    TextView textViewFirst;
    TextView textViewSecond;
    TextView textViewOperation;
    TextView textViewTime;
    TextView textViewQuestion;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button button6;
    QuestionSet qSet;
    Question question;
    ProgressBar progressBar;
    CountDownTimer countDownTimer;
    List<Player> playerList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        textViewFirst = findViewById(R.id.firstNumber);
        textViewSecond = findViewById(R.id.secondNumber);
        textViewOperation = findViewById(R.id.calculationSign);
        textViewTime = findViewById(R.id.time_text);
        textViewQuestion = findViewById(R.id.question_text);
        progressBar = findViewById(R.id.progressBar);
        button1 = findViewById(R.id.answer1);
        button2 = findViewById(R.id.answer2);
        button3 = findViewById(R.id.answer3);
        button4 = findViewById(R.id.answer4);
        button5 = findViewById(R.id.answer5);
        button6 = findViewById(R.id.answer6);


        playerList = PlayerData.playerList;

        qSet = getIntent().getParcelableExtra("questionSet");
        progressBar.setMax(qSet.getNumberOfQuestions());
        progressBar.setProgress(1);
        textViewQuestion.setText(counter + "/" + qSet.getNumberOfQuestions());

        createQuestion();

        countDownTimer = new CountDownTimer(qSet.getTime() * 1000, 1000) {

            public void onTick(long millisUntilFinished) {
                textViewTime.setText("" + millisUntilFinished / 1000);
            }

            public void onFinish() {
                Toast.makeText(QuestionActivity.this, "Time is up!", Toast.LENGTH_SHORT).show();
            }
        }.start();


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((Integer.parseInt(button1.getText().toString()) == question.getAnswer()) && (qSet.getNumberOfQuestions() > counter)) {
                    buttonContinueFuncs();
                } else if ((Integer.parseInt(button1.getText().toString()) == question.getAnswer()) && (qSet.getNumberOfQuestions() == counter )) {
                   buttonWinFuncs();
                } else {
                    buttonLooseFuncs();
                }
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((Integer.parseInt(button2.getText().toString()) == question.getAnswer()) && (qSet.getNumberOfQuestions() > counter)) {
                    buttonContinueFuncs();
                } else if ((Integer.parseInt(button2.getText().toString()) == question.getAnswer()) && (qSet.getNumberOfQuestions() == counter)) {
                    buttonWinFuncs();
                } else {
                    buttonLooseFuncs();
                }
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((Integer.parseInt(button3.getText().toString()) == question.getAnswer()) && (qSet.getNumberOfQuestions() > counter)) {
                    buttonContinueFuncs();
                } else if ((Integer.parseInt(button3.getText().toString()) == question.getAnswer()) && (qSet.getNumberOfQuestions() == counter)) {
                    buttonWinFuncs();
                } else {
                    buttonLooseFuncs();
                }
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((Integer.parseInt(button4.getText().toString()) == question.getAnswer()) && (qSet.getNumberOfQuestions() > counter)) {
                    buttonContinueFuncs();
                } else if ((Integer.parseInt(button4.getText().toString()) == question.getAnswer()) && (qSet.getNumberOfQuestions() == counter)) {
                    buttonWinFuncs();
                } else {
                    buttonLooseFuncs();
                }
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((Integer.parseInt(button5.getText().toString()) == question.getAnswer()) && (qSet.getNumberOfQuestions() > counter)) {
                    buttonContinueFuncs();
                } else if ((Integer.parseInt(button5.getText().toString()) == question.getAnswer()) && (qSet.getNumberOfQuestions() == counter)) {
                    buttonWinFuncs();
                } else {
                    buttonLooseFuncs();
                }
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((Integer.parseInt(button6.getText().toString()) == question.getAnswer()) && (qSet.getNumberOfQuestions() > counter)) {
                    buttonContinueFuncs();
                } else if ((Integer.parseInt(button6.getText().toString()) == question.getAnswer()) && (qSet.getNumberOfQuestions() == counter)) {
                    buttonWinFuncs();
                } else {
                    buttonLooseFuncs();
                }
            }
        });

    }

    private void createQuestion(){
        Random random = new Random();
        int queType = random.nextInt(4);

        if (queType == 0) {
            question = new QuestionPlus(qSet.isHard());
            textViewOperation.setText("+");
        } else if (queType == 1) {
            question = new QuestionMultiply(qSet.isHard());
            textViewOperation.setText("x");
        } else if (queType == 2) {
            question = new QuestionMinus(qSet.isHard());
            textViewOperation.setText("-");
        } else {
            question = new QuestionDivide(qSet.isHard());
            textViewOperation.setText("/");
        }

        textViewFirst.setText(String.valueOf(question.getFirstNumber()));
        textViewSecond.setText(String.valueOf(question.getSecondNumber()));
        button1.setText(String.valueOf(question.getOtherChoices()[5]));
        button2.setText(String.valueOf(question.getOtherChoices()[0]));
        button3.setText(String.valueOf(question.getOtherChoices()[1]));
        button4.setText(String.valueOf(question.getOtherChoices()[2]));
        button5.setText(String.valueOf(question.getOtherChoices()[3]));
        button6.setText(String.valueOf(question.getOtherChoices()[4]));

    }

    private void buttonContinueFuncs(){
        createQuestion();
        ++counter;
        progressBar.setProgress(counter);
        textViewQuestion.setText(counter+"/"+qSet.getNumberOfQuestions());
    }

    private void buttonWinFuncs(){
        Toast.makeText(QuestionActivity.this, "Well Done!", Toast.LENGTH_LONG).show();
        counter=1;
        countDownTimer.cancel();
        playerList.get(4).setMoney((long) ((playerList.get(4).getMoney()) * (1 + qSet.getPercentage())));

        Intent intent = new Intent(QuestionActivity.this, MainActivity.class);
        startActivity(intent);
    }

    private void buttonLooseFuncs(){
        counter=1;
        countDownTimer.cancel();
        playerList.get(4).setMoney((long) ((playerList.get(4).getMoney()) * (1 - qSet.getPercentage())));
        Intent intent = new Intent(QuestionActivity.this, MainActivity.class);
        startActivity(intent);}
    }


