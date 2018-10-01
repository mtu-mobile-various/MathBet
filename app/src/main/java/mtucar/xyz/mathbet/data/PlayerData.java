package mtucar.xyz.mathbet.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mtucar.xyz.mathbet.model.Player;

public class PlayerData {
    public static List<Player> playerList;
    public static Map<Integer, Player> playerMap;

    static {
        playerList = new ArrayList<>();
        playerMap = new HashMap<>();

        addPlayer(new Player(1,"Donald Krump", 1000000, 5 ));
        addPlayer(new Player(2,"Paris Hiltom", 1000000, 5 ));
        addPlayer(new Player(3,"Bill Gate", 1000000, 5 ));
        addPlayer(new Player(4,"Marc Tutanberg", 100000, 5 ));
        addPlayer(new Player(5,"Player", 1000, 5 ));

    }

    private static void addPlayer(Player player) {
        playerList.add(player);
        playerMap.put(player.getId(), player);
    }

}
