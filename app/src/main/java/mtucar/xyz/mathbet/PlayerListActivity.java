package mtucar.xyz.mathbet;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import mtucar.xyz.mathbet.database.PlayerAdapter;
import mtucar.xyz.mathbet.model.Player;
import mtucar.xyz.mathbet.database.DataSource;

public class PlayerListActivity extends AppCompatActivity {

    ListView listView;
    List<Player> playerList;
    DataSource mDataSource;
    String newName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_list);

        mDataSource = new DataSource(this);
        playerList = mDataSource.getAllPlayers();

        SharedPreferences userDetails = getSharedPreferences("mtucar.xyz.mathbet_preferences", MODE_PRIVATE);
        newName = userDetails.getString("player_name_preference","Player");
        mDataSource.changeName(newName);

        setName();
    }


    private void setName(){
        listView = findViewById(R.id.list_view_player);
        PlayerAdapter adapter = new PlayerAdapter(this, playerList);
        listView.setAdapter(adapter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mDataSource.close();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setName();
        mDataSource.open();
    }
}
