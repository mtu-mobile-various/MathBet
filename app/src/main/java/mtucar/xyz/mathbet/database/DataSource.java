package mtucar.xyz.mathbet.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
}
