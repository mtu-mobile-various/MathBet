package mtucar.xyz.mathbet.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import mtucar.xyz.mathbet.classes.Player;

public class DataSource {
    private Context mContext;
    private SQLiteDatabase mDataBase;
    SQLiteOpenHelper mDbHelper;

    public DataSource(Context Context) {
        this.mContext = Context;
        mDbHelper = new DbHelper(mContext);
        mDataBase = mDbHelper.getWritableDatabase();
    }
    public void open(){
        mDataBase = mDbHelper.getWritableDatabase();
    }

    public void close(){
        mDbHelper.close();
    }

    public Player createPlayer(Player player){
        ContentValues values = player.toValues();
        mDataBase.insert(PlayerTable.TABLE_PLAYERS, null, values);
        return player;
    }

    public long getPlayersCount(){
        return DatabaseUtils.queryNumEntries(mDataBase, PlayerTable.TABLE_PLAYERS);
    }

    public void seedDB(List<Player> playerList){
        long numPlayers = getPlayersCount();

        if(numPlayers==0){
            for(Player player: playerList){
                try {
                    createPlayer(player);
                } catch (SQLiteException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public List<Player> getAllPlayers(){
        List<Player> playerList = new ArrayList<>();
        Cursor cursor = mDataBase.query(PlayerTable.TABLE_PLAYERS,PlayerTable.ALL_COLUMNS,
                null,null,null,null,null);
        while (cursor.moveToNext()){
            Player player = new Player();
            player.setId(cursor.getInt(cursor.getColumnIndex(PlayerTable.COLUMN_ID)));
            player.setName(cursor.getString(cursor.getColumnIndex(PlayerTable.COLUMN_NAME)));
            player.setMoney(cursor.getLong(cursor.getColumnIndex(PlayerTable.COLUMN_MONEY)));
            player.setLuckFactor(cursor.getInt(cursor.getColumnIndex(PlayerTable.COLUMN_LUCK)));
            playerList.add(player);
        }
        return playerList;
    }

    public void updateMoney(double rise){
        rise += 1;
        mDataBase.execSQL("UPDATE " + PlayerTable.TABLE_PLAYERS +
                            " SET " + PlayerTable.COLUMN_MONEY + " = (" + PlayerTable.COLUMN_MONEY + "*" + rise + ") WHERE " +
                            PlayerTable.COLUMN_ID + "=5;");
    }
}
