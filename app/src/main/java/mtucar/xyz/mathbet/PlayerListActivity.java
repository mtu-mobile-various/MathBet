package mtucar.xyz.mathbet;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_list);

        mDataSource = new DataSource(this);
        playerList = mDataSource.getAllPlayers();

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
        mDataSource.open();
    }
}
