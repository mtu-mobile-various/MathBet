package mtucar.xyz.mathbet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import mtucar.xyz.mathbet.classes.QuestionDivide;
import mtucar.xyz.mathbet.classes.QuestionMinus;
import mtucar.xyz.mathbet.classes.QuestionMultiply;
import mtucar.xyz.mathbet.classes.QuestionPlus;

public class QuestionActivity extends AppCompatActivity {

    TextView textViewFirst;
    TextView textViewSecond;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button button6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        QuestionPlus questionPlus = new QuestionPlus(true);

        textViewFirst = findViewById(R.id.firstNumber);
        textViewSecond = findViewById(R.id.secondNumber);
        button1 = findViewById(R.id.answer1);
        button2 = findViewById(R.id.answer2);
        button3 = findViewById(R.id.answer3);
        button4 = findViewById(R.id.answer4);
        button5 = findViewById(R.id.answer5);
        button6 = findViewById(R.id.answer6);

        textViewFirst.setText(String.valueOf(questionPlus.getFirstNumber()));
        textViewSecond.setText(String.valueOf(questionPlus.getSecondNumber()));
        button1.setText(String.valueOf(questionPlus.getOtherChoices()[5]));
        button2.setText(String.valueOf(questionPlus.getOtherChoices()[0]));
        button3.setText(String.valueOf(questionPlus.getOtherChoices()[1]));
        button4.setText(String.valueOf(questionPlus.getOtherChoices()[2]));
        button5.setText(String.valueOf(questionPlus.getOtherChoices()[3]));
        button6.setText(String.valueOf(questionPlus.getOtherChoices()[4]));
    }


}
