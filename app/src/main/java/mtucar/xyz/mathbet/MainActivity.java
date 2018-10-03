package mtucar.xyz.mathbet;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import mtucar.xyz.mathbet.model.Player;
import mtucar.xyz.mathbet.data.PlayerData;
import mtucar.xyz.mathbet.database.DataSource;

public class MainActivity extends AppCompatActivity {

    Button buttonBet;
    Button buttonPlayer;
    Button btnOptions;
    TextView tvPlayerName;
    DataSource mDataSource;
    String name;
    List<Player> playerList = PlayerData.playerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDataSource = new DataSource(this);
        mDataSource.open();
        mDataSource.seedDB(playerList);

        createUserText();

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

        btnOptions = findViewById(R.id.buttonOptions);
        btnOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
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

        createUserText();
    }

    private void createUserText(){
        tvPlayerName = findViewById(R.id.tvUserName);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        name = preferences.getString("player_name_preference", "Player");
        tvPlayerName.setText(name);
    }
}
