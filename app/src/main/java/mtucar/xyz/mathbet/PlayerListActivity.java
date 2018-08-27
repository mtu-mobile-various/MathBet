package mtucar.xyz.mathbet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import mtucar.xyz.mathbet.classes.Player;
import mtucar.xyz.mathbet.data.PlayerData;

public class PlayerListActivity extends AppCompatActivity {

    ListView listView;
    List<Player> playerList = PlayerData.playerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_list);

        listView = findViewById(R.id.list_view_player);
        ArrayAdapter<Player> adapter =
                new ArrayAdapter<Player>(this,android.R.layout.simple_list_item_1,playerList);
        listView.setAdapter(adapter);


    }
}