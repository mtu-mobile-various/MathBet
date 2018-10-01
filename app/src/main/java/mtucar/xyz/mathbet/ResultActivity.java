package mtucar.xyz.mathbet;

import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import mtucar.xyz.mathbet.database.DataSource;

public class ResultActivity extends AppCompatActivity {

    TextView finalMoney;
    double initialMoney;
    DataSource mDataSource;
    int betMoney;
    int finalValue;
    double rise;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        mDataSource = new DataSource(this);
        mDataSource.open();
        initialMoney = Double.valueOf(mDataSource.getUserMoney());


        betMoney = getIntent().getIntExtra("betMoney",0);
        rise = getIntent().getIntExtra("rise",0);
        finalValue = (int)initialMoney +betMoney;



        finalMoney = findViewById(R.id.tvFinalMoney);
        animateTextView((int)initialMoney, finalValue, finalMoney);
        updatePlayerMoney(rise);
    }

    public void animateTextView(int money, int finalValue, final TextView textview) {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(money, finalValue);
        valueAnimator.setDuration(5000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                textview.setText(valueAnimator.getAnimatedValue().toString());
            }
        });
        valueAnimator.start();

    }
    private void updatePlayerMoney(double rise){
        mDataSource = new DataSource(this);
        mDataSource.open();
        mDataSource.updateMoney(rise);
    }
}
