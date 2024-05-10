package de.felixletsplays.CredentialManager;

import de.felixletsplays.CredentialManager.Connection.Connection;
import de.felixletsplays.CredentialManager.Connection.Utils;

import java.io.IOException;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        //check if config dir exists
        if (!Utils.CONFIGDIR.toFile().exists()) {
            Utils.CONFIGDIR.toFile().mkdir();
        }

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

                if (command != null || !command.isBlank()) {
                    if (command.startsWith("?")) {
                        cmd.displayHelp();
                    } else if (command.startsWith("^")) {
                        run = false;
                    } else if (command.startsWith("new")) {
                        cmd.create(input, command);
                    } else if (command.startsWith("del")) {
                        cmd.delete(input, command);
                    } else if (command.startsWith("mod")) {
                        cmd.modify(input, command);
                    } else if (command.startsWith("view")) {
                        cmd.view(input, command);
                    } else if (command.startsWith("list")) {
                        cmd.list(input, command);
                    } else {
                        try {
                            Connection connectTo = new Utils().readConnectionConfig(command);
                            connectTo.connect();
                        } catch (IOException | InterruptedException ex) {
                            System.out.println("This connection does not exists!");
                            System.err.println(ex.getMessage());
                        }
                    }
                }
            }

            System.out.println("Bye");
        }
    }
}
