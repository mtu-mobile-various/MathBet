package mtucar.xyz.mathbet.database;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import mtucar.xyz.mathbet.R;
import mtucar.xyz.mathbet.model.Player;

public class PlayerAdapter extends ArrayAdapter {
    List<Player> mPlayerList;
    LayoutInflater mLayoutInflater;

    public PlayerAdapter(@NonNull Context context, @NonNull List objects) {
        super(context, R.layout.activity_player_item, objects);
        mPlayerList = objects;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.activity_player_item, parent, false);
        }

        TextView tvName = convertView.findViewById(R.id.tvPlayerName);
        TextView tvMoney = convertView.findViewById(R.id.tvPlayerMoney);
        Player player = mPlayerList.get(position);

        int pos = position+1;
        tvName.setText(pos+"-"+player.getName());
        tvMoney.setText(String.valueOf(player.getMoney()));

        return convertView;
    }
}
