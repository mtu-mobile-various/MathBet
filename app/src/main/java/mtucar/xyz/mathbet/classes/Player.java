package mtucar.xyz.mathbet.classes;

import android.content.ContentValues;

import mtucar.xyz.mathbet.database.PlayerTable;

public class Player {

    private int id;
    private String name;
    private long money;
    private int luckFactor;

    public Player(){}

    public Player(int id, String name, long money, int luckFactor) {
        this.id = id;
        this.name = name;
        this.money = money;
        this.luckFactor = luckFactor;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    public double getLuckFactor() {
        return luckFactor;
    }

    public void setLuckFactor(int luckFactor) {
        this.luckFactor = luckFactor;
    }

    public ContentValues toValues(){
        ContentValues values = new ContentValues(4);

        values.put(PlayerTable.COLUMN_ID, id);
        values.put(PlayerTable.COLUMN_NAME, name);
        values.put(PlayerTable.COLUMN_MONEY, money);
        values.put(PlayerTable.COLUMN_LUCK, luckFactor);
        return values;
    }
    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", money=" + money +
                ", luckFactor=" + luckFactor +
                '}';
    }
}
