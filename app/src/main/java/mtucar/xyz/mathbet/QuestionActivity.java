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

import java.util.List;
import java.util.Random;

import mtucar.xyz.mathbet.model.Player;
import mtucar.xyz.mathbet.model.Question;
import mtucar.xyz.mathbet.model.QuestionDivide;
import mtucar.xyz.mathbet.model.QuestionMinus;
import mtucar.xyz.mathbet.model.QuestionMultiply;
import mtucar.xyz.mathbet.model.QuestionPlus;
import mtucar.xyz.mathbet.model.QuestionSet;
import mtucar.xyz.mathbet.data.PlayerData;
import mtucar.xyz.mathbet.database.DataSource;

public class QuestionActivity extends AppCompatActivity {

    private static int counter = 1;
    TextView txtViewFirst;
    TextView txtViewSecond;
    TextView txtViewOperation;
    TextView txtViewTime;
    TextView txtViewQuestion;
    Button btnAnswer1;
    Button btnAnswer2;
    Button btnAnswer3;
    Button btnAnswer4;
    Button btnAnswer5;
    Button btnAnswer6;
    QuestionSet qSet;
    Question question;
    int betMoney;
    ProgressBar progressBar;
    CountDownTimer countDownTimer;
    List<Player> playerList;
    double percentage;
    String result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        txtViewFirst = findViewById(R.id.firstNumber);
        txtViewSecond = findViewById(R.id.secondNumber);
        txtViewOperation = findViewById(R.id.calculationSign);
        txtViewTime = findViewById(R.id.time_text);
        txtViewQuestion = findViewById(R.id.question_text);
        progressBar = findViewById(R.id.progressBar);
        btnAnswer1 = findViewById(R.id.answer1);
        btnAnswer2 = findViewById(R.id.answer2);
        btnAnswer3 = findViewById(R.id.answer3);
        btnAnswer4 = findViewById(R.id.answer4);
        btnAnswer5 = findViewById(R.id.answer5);
        btnAnswer6 = findViewById(R.id.answer6);



        playerList = PlayerData.playerList;

        qSet = getIntent().getParcelableExtra("questionSet");
        betMoney = getIntent().getIntExtra("betMoney",0);
//        percentage = qSet.getPercentage();

        progressBar.setMax(qSet.getNumberOfQuestions());
        progressBar.setProgress(1);
        txtViewQuestion.setText(counter + "/" + qSet.getNumberOfQuestions());

        createQuestion();

        countDownTimer = new CountDownTimer(qSet.getTime() * 1000, 1000) {

            public void onTick(long millisUntilFinished) {
                txtViewTime.setText("" + millisUntilFinished / 1000);
            }

            public void onFinish() {
                Toast.makeText(QuestionActivity.this, "Time is up!", Toast.LENGTH_SHORT).show();
            }
        }.start();


        btnAnswer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((Integer.parseInt(btnAnswer1.getText().toString()) == question.getAnswer()) && (qSet.getNumberOfQuestions() > counter)) {
                    buttonContinueFuncs();
                } else if ((Integer.parseInt(btnAnswer1.getText().toString()) == question.getAnswer()) && (qSet.getNumberOfQuestions() == counter )) {
                   buttonWinFuncs();
                } else {
                    buttonLooseFuncs();
                }
            }
        });

        btnAnswer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((Integer.parseInt(btnAnswer2.getText().toString()) == question.getAnswer()) && (qSet.getNumberOfQuestions() > counter)) {
                    buttonContinueFuncs();
                } else if ((Integer.parseInt(btnAnswer2.getText().toString()) == question.getAnswer()) && (qSet.getNumberOfQuestions() == counter)) {
                    buttonWinFuncs();
                } else {
                    buttonLooseFuncs();
                }
            }
        });
        btnAnswer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((Integer.parseInt(btnAnswer3.getText().toString()) == question.getAnswer()) && (qSet.getNumberOfQuestions() > counter)) {
                    buttonContinueFuncs();
                } else if ((Integer.parseInt(btnAnswer3.getText().toString()) == question.getAnswer()) && (qSet.getNumberOfQuestions() == counter)) {
                    buttonWinFuncs();
                } else {
                    buttonLooseFuncs();
                }
            }
        });

        btnAnswer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((Integer.parseInt(btnAnswer4.getText().toString()) == question.getAnswer()) && (qSet.getNumberOfQuestions() > counter)) {
                    buttonContinueFuncs();
                } else if ((Integer.parseInt(btnAnswer4.getText().toString()) == question.getAnswer()) && (qSet.getNumberOfQuestions() == counter)) {
                    buttonWinFuncs();
                } else {
                    buttonLooseFuncs();
                }
            }
        });

        btnAnswer5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((Integer.parseInt(btnAnswer5.getText().toString()) == question.getAnswer()) && (qSet.getNumberOfQuestions() > counter)) {
                    buttonContinueFuncs();
                } else if ((Integer.parseInt(btnAnswer5.getText().toString()) == question.getAnswer()) && (qSet.getNumberOfQuestions() == counter)) {
                    buttonWinFuncs();
                } else {
                    buttonLooseFuncs();
                }
            }
        });

        btnAnswer6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((Integer.parseInt(btnAnswer6.getText().toString()) == question.getAnswer()) && (qSet.getNumberOfQuestions() > counter)) {
                    buttonContinueFuncs();
                } else if ((Integer.parseInt(btnAnswer6.getText().toString()) == question.getAnswer()) && (qSet.getNumberOfQuestions() == counter)) {
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
            txtViewOperation.setText("+");
        } else if (queType == 1) {
            question = new QuestionMultiply(qSet.isHard());
            txtViewOperation.setText("x");
        } else if (queType == 2) {
            question = new QuestionMinus(qSet.isHard());
            txtViewOperation.setText("-");
        } else {
            question = new QuestionDivide(qSet.isHard());
            txtViewOperation.setText("/");
        }

        txtViewFirst.setText(String.valueOf(question.getFirstNumber()));
        txtViewSecond.setText(String.valueOf(question.getSecondNumber()));
        btnAnswer1.setText(String.valueOf(question.getOtherChoices()[5]));
        btnAnswer2.setText(String.valueOf(question.getOtherChoices()[0]));
        btnAnswer3.setText(String.valueOf(question.getOtherChoices()[1]));
        btnAnswer4.setText(String.valueOf(question.getOtherChoices()[2]));
        btnAnswer5.setText(String.valueOf(question.getOtherChoices()[3]));
        btnAnswer6.setText(String.valueOf(question.getOtherChoices()[4]));

    }

    private void buttonContinueFuncs(){
        createQuestion();
        ++counter;
        progressBar.setProgress(counter);
        txtViewQuestion.setText(counter+"/"+qSet.getNumberOfQuestions());
    }

    private void buttonWinFuncs(){
        result = "You Win!";
        Toast.makeText(QuestionActivity.this, "Well Done!", Toast.LENGTH_LONG).show();
        counter=1;
        countDownTimer.cancel();
        Intent intent = new Intent(QuestionActivity.this, ResultActivity.class);
        intent.putExtra("betMoney", betMoney);
        intent.putExtra("result", result);
        startActivity(intent);
    }

    private void buttonLooseFuncs(){
        result = "You Loose!";
        counter=1;
        betMoney = -1 * betMoney;
        countDownTimer.cancel();
        Intent intent = new Intent(QuestionActivity.this, ResultActivity.class);
        intent.putExtra("betMoney", betMoney);
        intent.putExtra("result", result);
        startActivity(intent);
    }


}


