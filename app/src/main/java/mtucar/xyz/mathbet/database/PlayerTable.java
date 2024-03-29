package mtucar.xyz.mathbet.database;

public class PlayerTable {
    public static final String TABLE_PLAYERS = "players";
    public static final String COLUMN_ID = "playerId";
    public static final String COLUMN_NAME = "playerName";
    public static final String COLUMN_MONEY = "playerMoney";


    public static final String[] ALL_COLUMNS =
            {COLUMN_ID, COLUMN_NAME, COLUMN_MONEY};

    /*
    The string for creating SQL DAtabase
     */
    public static final String SQL_CREATE =
            "CREATE TABLE " + TABLE_PLAYERS + "(" +
                    COLUMN_ID + " TEXT PRIMARY KEY, "+
                    COLUMN_NAME + " TEXT, "+
                    COLUMN_MONEY + " INTEGER"+ ");";

    public static final String SQL_DELETE =
            "DROP TABLE " + TABLE_PLAYERS;
}
