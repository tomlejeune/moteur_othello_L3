package engine;

import java.io.Serializable;

/**
 * EnumRule describes the different available Rules for the Game.
 *
 * @version 1.0
 */
public enum EnumRule implements Serializable {
    OTHELLO("OTHELLO"),
    REVERSI("REVERSI");

    /**
     * Rule in String
     */
    private String rule = "";

    /**
     * Constructs a EnumRule with a string
     *
     * @param rule Rule wanted
     */
    EnumRule(String rule) {
        if(rule != "OTHELLO" && rule != "REVERSI") {
            System.out.println("Erreur de règle : La règle indiquée n'existe pas");
        }

        else {
            this.rule = rule;
        }
    }

    /**
     * String version of a Rule.
     *
     * @return String version of a Rule
     */
    public String toString() {
        return this.rule;
    }
}
