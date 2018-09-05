package mtucar.xyz.mathbet;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import mtucar.xyz.mathbet.database.DataSource;
import mtucar.xyz.mathbet.database.DbHelper;

public class MainActivity extends AppCompatActivity {

    Button buttonBet;
    Button buttonPlayer;
    DataSource mDataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDataSource = new DataSource(this);
        mDataSource.open();

        buttonBet = findViewById(R.id.buttonBet);
        buttonBet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, QuestionListActivity.class);
                startActivity(intent);
            }
        });

        buttonPlayer = findViewById(R.id.buttonPlayer);
        buttonPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PlayerListActivity.class);
                startActivity(intent);
            }
        });
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
