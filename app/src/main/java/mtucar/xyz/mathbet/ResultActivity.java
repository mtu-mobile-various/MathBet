package mtucar.xyz.mathbet;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import mtucar.xyz.mathbet.database.DataSource;

public class ResultActivity extends AppCompatActivity {

    TextView finalMoney;
    TextView tvLabel;
    Button btnMenu;
    int initialMoney;
    DataSource mDataSource;
    int betMoney;
    int finalValue;
    String result;
    SharedPreferences preferences;
    int counter = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        mDataSource = new DataSource(this);
        mDataSource.open();
        initialMoney = mDataSource.getUserMoney();

        betMoney = getIntent().getExtras().getInt("betMoney",0);
        result = getIntent().getExtras().getString("result","");


        tvLabel = findViewById(R.id.tv_label_result);
        tvLabel.setText(result);

        finalValue = initialMoney + betMoney;

        finalMoney = findViewById(R.id.tvFinalMoney);
        animateTextView(initialMoney, finalValue, finalMoney);
        mDataSource.updateMoney(betMoney);

        btnMenu = findViewById(R.id.btnMainMenu);
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        //Increase SharedPref Count if player wins
        preferences = getSharedPreferences(MainActivity.PREFERENCES,MODE_PRIVATE);
        counter = preferences.getInt(MainActivity.COUNTER,0);
        if (result.equals(QuestionActivity.WINMESSAGE)){
            ++counter;
        }else{
            counter = 0;
        }
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(MainActivity.COUNTER, counter).commit();
        Toast.makeText(this, String.valueOf(counter), Toast.LENGTH_LONG).show();
    }

    public void animateTextView(int money, int finalValue, final TextView textview) {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(money, finalValue);
        valueAnimator.setDuration(2000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                textview.setText("$"+valueAnimator.getAnimatedValue().toString());
            }
        });
        valueAnimator.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mDataSource.close();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mDataSource.open();
    }
}
