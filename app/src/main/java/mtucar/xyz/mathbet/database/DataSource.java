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

import mtucar.xyz.mathbet.model.Player;

public class DataSource {
    private Context mContext;
    private SQLiteDatabase mDataBase;
    SQLiteOpenHelper mDbHelper;

    /*
    Constractor for DataSource
    it creates a context & dbHelper object
    & a Database Object
     */
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

    /*
    Method to populate the DB
    it takes the data as a list and
    insert the data
     */
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

    /*
    Method to populate a list from
    DB data
     */
    public List<Player> getAllPlayers(){
        List<Player> playerList = new ArrayList<>();
        Cursor cursor = mDataBase.query(PlayerTable.TABLE_PLAYERS,PlayerTable.ALL_COLUMNS,
                null,null,null,null,null);
        while (cursor.moveToNext()){
            Player player = new Player();
            player.setId(cursor.getInt(cursor.getColumnIndex(PlayerTable.COLUMN_ID)));
            player.setName(cursor.getString(cursor.getColumnIndex(PlayerTable.COLUMN_NAME)));
            player.setMoney(cursor.getInt(cursor.getColumnIndex(PlayerTable.COLUMN_MONEY)));
            playerList.add(player);
        }
        return playerList;
    }

    public void updateMoney(int rise){
        mDataBase.execSQL("UPDATE " + PlayerTable.TABLE_PLAYERS +
                            " SET " + PlayerTable.COLUMN_MONEY + " = (" + PlayerTable.COLUMN_MONEY + "+" + rise + ") WHERE " +
                            PlayerTable.COLUMN_ID + "=5;");
    }

    public void changeName(String newName){
        mDataBase.execSQL("UPDATE " + PlayerTable.TABLE_PLAYERS +
                " SET " + PlayerTable.COLUMN_NAME + " = '" + newName +"' WHERE " +
                PlayerTable.COLUMN_ID + "=5;");
    }

    public int getUserMoney(){
        int money = 0;
        Cursor cursor = mDataBase.query(PlayerTable.TABLE_PLAYERS,PlayerTable.ALL_COLUMNS,
                PlayerTable.COLUMN_ID+"=?",new String[] { "5" },null,null,null);
        while (cursor.moveToNext()) {
            money = cursor.getInt(cursor.getColumnIndex(PlayerTable.COLUMN_MONEY));
        }
        cursor.close();
        return money;
    }
}
