package engine;

/**
 * State describes the different states in a Game.
 *
 * @version 1.0
 */
public enum State {
    INIT("INIT"),
    PLAY("PLAY"),
    END("END");

    /**
     * State in String
     */
    private String state = "";

    /**
     * Constructs a State with a String
     *
     * @param state State wanted
     */
    State(String state) {
        if(state != "INIT" && state != "PLAY" && state != "END") {
            System.out.println("Erreur d'état : L'état indiqué n'existe pas");
        }

        else {
            this.state = state;
        }

    }

    /**
     * String version of a State.
     *
     * @return String version of a State
     */
    public String toString() {
        return this.state;
    }
}