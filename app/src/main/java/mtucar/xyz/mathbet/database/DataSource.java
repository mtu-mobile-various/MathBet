package mtucar.xyz.mathbet.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
}
