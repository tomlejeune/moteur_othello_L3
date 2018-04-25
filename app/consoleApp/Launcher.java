package consoleApp;

import java.util.Scanner;

import dao.DAOException;

/**
 * Launcher is the entry point of the App.
 *
 * @version 1.0.0
 */
public class Launcher {

    /**
     * Launches the App.
     *
     * @param args
     */
    public static void main(String[] args) throws DAOException {
        Scanner scanner = new Scanner(System.in);

        Choices.choiceHome(scanner);
    }
}
