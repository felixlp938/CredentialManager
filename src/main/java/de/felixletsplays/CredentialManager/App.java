package de.felixletsplays.CredentialManager;

import java.util.Scanner;

import javax.swing.JOptionPane;

/**
 * Main-Class for the app
 *
 * @author Felix
 */
public class App {

    /**
     * If this boolean is {@code false}, we close the app
     */
    public static boolean run = true;

    /**
     *
     */
    public static Commands cmd = new Commands();

    /**
     * main method
     *
     * @param args args can be used to start a GUI (simply run with 'gui')
     */
    public static void main(String[] args) {
        if (args.length != 0 && args[0].equalsIgnoreCase("gui")) {
            //run gui here
            JOptionPane.showMessageDialog(null, "Sadly, this is not implemented!", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            //run cli here
            System.out.println("""
                               --- WELCOME, %u ---
                               For help type: ?
                               To exit type: ^ or press CTRL + C
                               
                               Website: https://felix-lets-plays.de/oss/cm
                               """.replace("%u", System.getProperty("user.name").toUpperCase()));

            Scanner input = new Scanner(System.in);
            String command = "";

            while (run) {

                //CLI looks like this: > [COMMAND/SSH connection entry]
                System.out.print("> ");
                command = input.nextLine();

                if (command != null) {
                    switch (command.toLowerCase()) {
                        case "?" ->
                            cmd.displayHelp();
                        case "^" ->
                            run = false;
                        case "new" ->
                            
                    }
                }
            }

            System.out.println("Bye");
        }
    }
}
