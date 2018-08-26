package mtucar.xyz.mathbet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;

import mtucar.xyz.mathbet.classes.Question;
import mtucar.xyz.mathbet.classes.QuestionDivide;
import mtucar.xyz.mathbet.classes.QuestionMinus;
import mtucar.xyz.mathbet.classes.QuestionMultiply;
import mtucar.xyz.mathbet.classes.QuestionPlus;
import mtucar.xyz.mathbet.classes.QuestionSet;

public class QuestionActivity extends AppCompatActivity {

    TextView textViewFirst;
    TextView textViewSecond;
    TextView textViewOperation;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button button6;
    QuestionSet qSet;
    Question question;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        textViewFirst = findViewById(R.id.firstNumber);
        textViewSecond = findViewById(R.id.secondNumber);
        textViewOperation = findViewById(R.id.calculationSign);
        button1 = findViewById(R.id.answer1);
        button2 = findViewById(R.id.answer2);
        button3 = findViewById(R.id.answer3);
        button4 = findViewById(R.id.answer4);
        button5 = findViewById(R.id.answer5);
        button6 = findViewById(R.id.answer6);


        qSet = getIntent().getParcelableExtra("questionSet");

        createQuestion();

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Integer.parseInt(button1.getText().toString())==question.getAnswer()){
                    createQuestion();
                }else{Intent intent = new Intent(QuestionActivity.this, MainActivity.class);
                    startActivity(intent);}
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Integer.parseInt(button2.getText().toString())==question.getAnswer()){
                    createQuestion();
                }else{Intent intent = new Intent(QuestionActivity.this, MainActivity.class);
                    startActivity(intent);}
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Integer.parseInt(button3.getText().toString())==question.getAnswer()){
                    createQuestion();
                }else{Intent intent = new Intent(QuestionActivity.this, MainActivity.class);
                    startActivity(intent);}
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Integer.parseInt(button4.getText().toString())==question.getAnswer()){
                    createQuestion();
                }else{Intent intent = new Intent(QuestionActivity.this, MainActivity.class);
                    startActivity(intent);}
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Integer.parseInt(button5.getText().toString())==question.getAnswer()){
                    createQuestion();
                }else{Intent intent = new Intent(QuestionActivity.this, MainActivity.class);
                    startActivity(intent);}
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Integer.parseInt(button6.getText().toString())==question.getAnswer()){
                    createQuestion();
                }else{Intent intent = new Intent(QuestionActivity.this, MainActivity.class);
                    startActivity(intent);}
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

}
