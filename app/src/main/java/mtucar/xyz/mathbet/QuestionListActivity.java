package mtucar.xyz.mathbet;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import mtucar.xyz.mathbet.classes.Player;
import mtucar.xyz.mathbet.classes.QuestionSet;
import mtucar.xyz.mathbet.data.PlayerData;

public class QuestionListActivity extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_list);

        listView = findViewById(R.id.question_list);
        QuestionSet[] questionSet = new QuestionSet[5];
        for(int i=0; i<5; ++i)
            questionSet[i] = new QuestionSet();

        ArrayAdapter<QuestionSet> adapter =
                new ArrayAdapter<QuestionSet>(this, android.R.layout.simple_list_item_1, questionSet);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(itemClickListener);
    }

    private AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            // ListView Clicked item value
            QuestionSet qSet = (QuestionSet) listView.getItemAtPosition(position);
            Intent intent = new Intent(QuestionListActivity.this, QuestionActivity.class);
            intent.putExtra("questionSet", (Parcelable) qSet);
            startActivity(intent);
        }
    };
}
