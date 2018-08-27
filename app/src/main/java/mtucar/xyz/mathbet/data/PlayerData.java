package mtucar.xyz.mathbet.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mtucar.xyz.mathbet.classes.Player;

public class PlayerData {
    public static List<Player> playerList;
    public static Map<Integer, Player> playerMap;

    static {
        playerList = new ArrayList<>();
        playerMap = new HashMap<>();

        addPlayer(new Player(1,"Donald Krump", 1000000, 5 ));
        addPlayer(new Player(2,"Paris Milton", 1000000, 5 ));
        addPlayer(new Player(3,"Bill Windows", 1000000, 5 ));
        addPlayer(new Player(4,"Marc Tutanberg", 1000000, 5 ));
        addPlayer(new Player(5,"Player", 100, 5 ));



    }

    private static void addPlayer(Player player) {
        playerList.add(player);
        playerMap.put(player.getId(), player);
    }
}
