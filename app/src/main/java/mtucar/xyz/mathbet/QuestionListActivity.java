package mtucar.xyz.mathbet;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import mtucar.xyz.mathbet.database.DataSource;
import mtucar.xyz.mathbet.database.QSetAdapter;
import mtucar.xyz.mathbet.model.QuestionSet;

public class QuestionListActivity extends AppCompatActivity {

    SeekBar seekBar;
    TextView tvPercentage;
    TextView tvBetMoney;
    TextView tvPlayerMoney;
    ImageButton btnRefresh;
    ListView listView;
    int userMoney;
    int betMoney;
    int betFinalMoney;
    int seekBarProgress;
    DataSource mDataSource;
    QuestionSet[] questionSet;
    int numberOfQuestionSet = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_list);

        tvBetMoney = findViewById(R.id.tvPriceMoney);

        //Get the user initialMoney from Database
        mDataSource = new DataSource(this);
        userMoney = mDataSource.getUserMoney();

        //show player money
        tvPlayerMoney = findViewById(R.id.tvPlayerMoney);
        tvPlayerMoney.setText("$"+String.valueOf(mDataSource.getUserMoney()));

        //seekBar to change the amount of the initialMoney
        tvPercentage = findViewById(R.id.tvPercentage);
        seekBar = findViewById(R.id.seekBar);
        betMoney = (int) (userMoney * 0.5);


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvPercentage.setText("%"+String.valueOf(progress));
                seekBarProgress = progress;
                betMoney = userMoney * progress / 100;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        listView = findViewById(R.id.question_list);
        createBetList();

        btnRefresh = findViewById(R.id.btnRefresh);
        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createBetList();
            }
        });

    }

    private AdapterView.OnItemLongClickListener itemLongClickListener = new AdapterView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            // ListView Clicked item value
            QuestionSet qSet = (QuestionSet) listView.getItemAtPosition(position);
            betFinalMoney = Integer.valueOf((int) (qSet.getPercentage()* betMoney));
            Intent intent = new Intent(QuestionListActivity.this, QuestionActivity.class);
            intent.putExtra("questionSet", (Parcelable) qSet);
            intent.putExtra("betMoney", betFinalMoney);
            startActivity(intent);
            return true;
        }
    };


    private AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            QuestionSet object = (QuestionSet) listView.getItemAtPosition(position);
            betFinalMoney = Integer.valueOf((int) (object.getPercentage()* betMoney));
            tvBetMoney.setText("You Get: $"+String.valueOf(betFinalMoney));
        }
    };

    private void createBetList(){
        questionSet = new QuestionSet[numberOfQuestionSet];
        for(int i=0; i<numberOfQuestionSet; ++i)
            questionSet[i] = new QuestionSet();

        QSetAdapter qSetAdapter = new QSetAdapter(this, questionSet);
        listView.setAdapter(qSetAdapter);

        listView.setOnItemLongClickListener(itemLongClickListener);
        listView.setOnItemClickListener(itemClickListener);
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
