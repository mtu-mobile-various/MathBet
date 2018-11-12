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
    TextView tvPlayerMoney;
    DataSource mDataSource;
    String name;
    SharedPreferences preferences;
    List<Player> playerList = PlayerData.playerList;
    public static final String PREFERENCES = "mtucar.xyz.mathbet_preferences";
    public static final String NAME = "player_name_preference";
    public static final String COUNTER = "counter";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDataSource = new DataSource(this);
        mDataSource.open();
        mDataSource.seedDB(playerList);

        tvPlayerMoney = findViewById(R.id.tvPlayerMoney);
        tvPlayerMoney.setText("$"+String.valueOf(mDataSource.getUserMoney()));

        createUserText();
        checkName();
        initializeCounter();

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

        preferences = getSharedPreferences(PREFERENCES, MODE_PRIVATE);
        mDataSource.changeName(preferences.getString(NAME,"Player"));
        createUserText();
    }

    private void createUserText(){
        tvPlayerName = findViewById(R.id.tvUserName);
        checkName();
        tvPlayerName.setText(name);
    }

    private void checkName(){
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        name = preferences.getString(NAME, "Player");
    }

    private void initializeCounter(){
        preferences = getSharedPreferences(MainActivity.PREFERENCES,MODE_PRIVATE);
        if(!preferences.contains(COUNTER)) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt(COUNTER, 0);
            editor.commit();
        }
    }


}
