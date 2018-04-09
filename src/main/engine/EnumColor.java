package engine;

public enum EnumColor {
    BLACK("BLACK"),
    WHITE("WHITE");

    /**
     * Rule in String
     */
    private String color = "";

    /**
     * Constructs a EnumColor with a string
     *
     * @param color Color wanted
     */
    EnumColor(String color) {
        if(color != "OTHELLO" && color != "REVERSI") {
            System.out.println("Erreur de couleur : Cette couleur n'existe pas");
        }

        else {
            this.color = color;
        }
    }

    /**
     * String version of a Rule.
     *
     * @return String version of a Rule
     */
    public String toString() {
        return this.color;
    }
}
