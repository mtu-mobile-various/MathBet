package mtucar.xyz.mathbet;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.List;

import mtucar.xyz.mathbet.database.DataSource;
import mtucar.xyz.mathbet.database.QSetAdapter;
import mtucar.xyz.mathbet.model.Player;
import mtucar.xyz.mathbet.model.QuestionSet;

public class QuestionListActivity extends AppCompatActivity {

    SeekBar seekBar;
    TextView tvMoney;
    ListView listView;
    String userMoney;
    DataSource mDataSource;
    int numberOfQuestionSet = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_list);

        //Get the user money from Database
        mDataSource = new DataSource(this);
        userMoney = mDataSource.getUserMoney();


        //seekBar to change the amount of the money
        tvMoney = findViewById(R.id.tvRiskedMoney);
        seekBar = findViewById(R.id.seekBar);



        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvMoney.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        listView = findViewById(R.id.question_list);
        QuestionSet[] questionSet = new QuestionSet[numberOfQuestionSet];
        for(int i=0; i<numberOfQuestionSet; ++i)
            questionSet[i] = new QuestionSet();

//        ArrayAdapter<QuestionSet> adapter =
//                new ArrayAdapter<QuestionSet>(this, android.R.layout.simple_list_item_1, questionSet);
//
//        listView.setAdapter(adapter);

        QSetAdapter qSetAdapter = new QSetAdapter(this, questionSet);
        listView.setAdapter(qSetAdapter);

        listView.setOnItemLongClickListener(itemClickListener);
    }

    private AdapterView.OnItemLongClickListener itemClickListener = new AdapterView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            // ListView Clicked item value
            QuestionSet qSet = (QuestionSet) listView.getItemAtPosition(position);
            Intent intent = new Intent(QuestionListActivity.this, QuestionActivity.class);
            intent.putExtra("questionSet", (Parcelable) qSet);
            startActivity(intent);
            return true;
        }

//        @Override
//        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//            // ListView Clicked item value
//            QuestionSet qSet = (QuestionSet) listView.getItemAtPosition(position);
//            Intent intent = new Intent(QuestionListActivity.this, QuestionActivity.class);
//            intent.putExtra("questionSet", (Parcelable) qSet);
//            startActivity(intent);
//        }
    };

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
