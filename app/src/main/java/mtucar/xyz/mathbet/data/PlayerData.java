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

        addPlayer(new Player(1,"Donald Krump", 1000000));
        addPlayer(new Player(2,"Sergey Putin", 1000000  ));
        addPlayer(new Player(3,"Bill Gate", 1000000 ));
        addPlayer(new Player(4,"Steve Job", 1000000 ));
        addPlayer(new Player(5,"Player", 1000 ));
        addPlayer(new Player(6,"Ronaldo", 100000 ));
        addPlayer(new Player(7,"Lebrom James", 100000 ));
        addPlayer(new Player(8,"Kanye East", 100000 ));
        addPlayer(new Player(9,"King Selman", 100000 ));
        addPlayer(new Player(10,"Kim Jong Tun", 100000 ));
        addPlayer(new Player(11,"Paris Hiltom", 1000 ));
        addPlayer(new Player(12,"Bretney", 1000 ));
        addPlayer(new Player(13,"Gregor Sansa", 100 ));
        addPlayer(new Player(14,"Ivan Desinovich", 100 ));
        addPlayer(new Player(15,"Justin Beaber", 1000 ));
        addPlayer(new Player(16,"Einstein", 1000 ));
        addPlayer(new Player(17,"Newton", 1000 ));
        addPlayer(new Player(18,"Celine Diom", 1000 ));
        addPlayer(new Player(19,"Freud", 1000 ));
        addPlayer(new Player(20,"Mari Curie", 1000 ));
    }

    private static void addPlayer(Player player) {
        playerList.add(player);
        playerMap.put(player.getId(), player);
    }

}
