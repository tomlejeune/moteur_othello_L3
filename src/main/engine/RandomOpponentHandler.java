package engine;

import java.util.ArrayList;

/**
 * RandomOpponentHandler handles the search of a random opponent for a Game.
 *
 * @version 1.0
 */
public class RandomOpponentHandler {

    /**
     * Returns an opponent or places the given Player into the waiting list if no opponents are found.
     *
     * @param player Player which searches a random opponent
     * @param opponents List of opponents
     * @return A Player if one is found, null if not
     */
    static HumanPlayer getRandomOppoment(HumanPlayer player, ArrayList<HumanPlayer> opponents) {
        HumanPlayer jrs = null;

        if(!opponents.contains(player)) {
            if(!opponents.isEmpty()) {
                jrs = opponents.get(0);
                opponents.remove(0);
            }

            else {
                opponents.add(player);
                System.out.println("Nous vous ajontons en liste d'attente");
            }
        }

        return jrs;
    }

    /**
     * Removes the given Player from the given list of opponents
     *
     * @param player Player which wants to be removed from the given list
     * @param opponents List of opponents
     */
    static void removeFromRandomOppoment(HumanPlayer player, ArrayList<HumanPlayer> opponents) {
        if(opponents.contains(player)) {
            for(int i = 0 ; i < opponents.size() ; i++) {
                if(player == opponents.get(i)) {
                    opponents.remove(i);
                    break;
                }
            }
        }
    }
}
