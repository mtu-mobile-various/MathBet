package mtucar.xyz.mathbet.model;

import android.content.ContentValues;
import android.support.annotation.NonNull;

import mtucar.xyz.mathbet.database.PlayerTable;

public class Player implements Comparable<Player> {

    private int id;
    private String name;
    private int money;


    public Player(){}

    public Player(int id, String name, int money) {
        this.id = id;
        this.name = name;
        this.money = money;

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

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }




    /*
    toValues()
    it returns a ContentValues object to write it
    to the database
    @return ContentValues
     */
    public ContentValues toValues(){
        ContentValues values = new ContentValues(3);

        values.put(PlayerTable.COLUMN_ID, id);
        values.put(PlayerTable.COLUMN_NAME, name);
        values.put(PlayerTable.COLUMN_MONEY, money);
        return values;
    }
    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", money=" + money + '}';
    }

    @Override
    public int compareTo(@NonNull Player o) {
        int otherMoney = o.getMoney();

        return otherMoney - this.money;
    }
}
