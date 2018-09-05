package mtucar.xyz.mathbet.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

    public static final String DB_FILE_NAME = "players.db";
    public static final int DB_VERSION = 1;

    public DbHelper(Context context) {
        super(context, DB_FILE_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(PlayerTable.SQL_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(PlayerTable.SQL_DELETE);
        onCreate(db);
    }
}
