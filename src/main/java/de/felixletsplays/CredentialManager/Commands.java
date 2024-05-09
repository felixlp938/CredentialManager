package de.felixletsplays.CredentialManager;

import de.felixletsplays.CredentialManager.Connection.Connection;
import de.felixletsplays.CredentialManager.Connection.ConnectionBuilder;
import de.felixletsplays.CredentialManager.Connection.Utils;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Used to parse commands
 *
 * @author Felix
 */
public class Commands {

    /**
     * Instance of the {@link Utils} class
     */
    private Utils cmUtils = new Utils();

    /**
     * Display help
     */
    public void displayHelp() {
        System.out.println("""
                           Help:
                                To create a new connection type: 'new'
                                To delete a connection type: 'del CONNECTION'
                                To modify a connection type: 'mod CONNECTION'
                           
                                To view all connections type: 'list'
                                To view a single connection type: 'view CONNECTION'
                           
                                Connect to a remote by typing simply the name or id of the connection
                           
                                Exit by typing ^ or press CTRL + C
                                
                                Please report bugs to the GitHub!
                                Website: https://felix-lets-plays.de/oss/cm
                           """);
    }

    /**
     * Read the input of the user
     *
     * @param in Scanner input
     * @param message Message to display
     * @return The user's input
     */
    private String readInput(Scanner in, String message) {
        System.out.print(message);
        return in.nextLine();
    }

    /**
     * Interaction to create a connection file
     *
     * @param in {@link Scanner} to allow the user type something
     * @param command the command from the "shell"
     */
    public void create(Scanner in, String command) {
        System.out.println("Create connection");
        String id = readInput(in, "(Enter ID) > ").trim();
        String remote = readInput(in, "(Enter remotehost) > ").trim();
        String user = readInput(in, "(Enter username) > ").trim();
        String key = readInput(in, "(Enter path to keyfile; if empty password auth will be used!) > ").trim();
        String args = readInput(in, "(Enter additional args; optional) > ").trim();

        ConnectionBuilder builder = new ConnectionBuilder();
        builder.withID(id);
        builder.withRemotehost(remote);
        builder.withUser(user);
        builder.withKeyPath(key);
        builder.withArgs(args);

        try {
            cmUtils.createConnectionConfig(new Connection(builder));
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
            System.err.println(ex.toString());
        }
    }

    /**
     * Interaction to delete a connection file
     *
     * @param in {@link Scanner} to allow the user type something
     * @param command the command from the "shell"
     */
    public void delete(Scanner in, String command) {
        String[] cmd = command.split("\\s+");
        if (cmd.length != 2) {
            System.out.println("Please use: del CONNID");
            return;
        }

        System.out.println("Delete connection");

        if (readInput(in, "(Are you sure to delete connection " + cmd[1].trim() + "? [y/n]) > ").equalsIgnoreCase("y")) {
            try {
                cmUtils.deleteConnectionConfig(cmd[1].trim());
            } catch (IOException ex) {
                System.out.println("Error: " + ex.getMessage());
                System.err.println(ex.toString());
            }
        }
    }

    /**
     * Interaction to modify a connection file
     *
     * @param in {@link Scanner} to allow the user type something
     * @param command the command from the "shell"
     */
    public void modify(Scanner in, String command) {
        String[] cmd = command.split("\\s+");
        if (cmd.length != 2) {
            System.out.println("Please use: mod CONNID");
            return;
        }

        System.out.println("Modify connection");

        try {
            Connection conn = cmUtils.readConnectionConfig(cmd[1].trim());

            System.out.println("Please enter your new values. If nothing is provided, the old values will be used!");
            String newId = readInput(in, "(Enter new ID [" + conn.getID() + "]) > ").trim();
            String newRemotehost = readInput(in, "(Enter new remotehost [" + conn.getRemotehost() + "]) > ").trim();
            String newUser = readInput(in, "(Enter new username [" + conn.getUser() + "]) > ").trim();
            String newKey = readInput(in, "(Enter new path to keyfile; if empty, password auth will be used! [" + conn.getKeyPath() + "]) > ").trim();
            String newArgs = readInput(in, "(Enter new args [" + conn.getArgs() + "]) > ").trim();

            if (newId.isBlank() || newId.isEmpty()) {
                newId = conn.getID();
            }

            if (newRemotehost.isBlank() || newRemotehost.isEmpty()) {
                newRemotehost = conn.getRemotehost();
            }

            if (newUser.isBlank() || newUser.isEmpty()) {
                newUser = conn.getUser();
            }

            if (newKey.isBlank() || newKey.isEmpty()) {
                newKey = conn.getKeyPath();
            }

            if (newArgs.isBlank() || newArgs.isEmpty()) {
                newArgs = conn.getArgs();
            }

            cmUtils.deleteConnectionConfig(conn.getID());

            conn.setID(newId);
            conn.setRemotehost(newRemotehost);
            conn.setUser(newUser);
            conn.setKeyPath(newKey);
            conn.setArgs(newArgs);

            cmUtils.createConnectionConfig(conn);
            System.out.println("Updated connection file with id " + conn.getID());

        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
            System.err.println(ex.toString());
        }
    }

    /**
     * Interaction to view a connection file
     *
     * @param in {@link Scanner} to allow the user type something
     * @param command the command from the "shell"
     */
    public void view(Scanner in, String command) {
        String[] cmd = command.split("\\s+");
        if (cmd.length != 2) {
            System.out.println("Please use: view CONNID");
            return;
        }

        try {
            Connection conn = cmUtils.readConnectionConfig(cmd[1].trim());

            System.out.println("""
                               Viewing connection file %id
                               ID: %id
                               Remotehost: %r
                               Username: %u
                               Keyfile path: %k
                               Args: %a
                               """.replace("%id", conn.getID())
                    .replace("%r", conn.getRemotehost())
                    .replace("%u", conn.getUser())
                    .replace("%k", conn.getKeyPath())
                    .replace("%a", conn.getArgs()));
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
            System.err.println(ex.toString());
        }
    }

    /**
     * Interaction to list all connections
     *
     * @param in {@link Scanner} to allow the user type something
     * @param command the command from the "shell"
     */
    public void list(Scanner in, String command) {
        try {
            System.out.println("ID\tSSH-Connection string");
            for (Connection con : cmUtils.listAllConnections()) {
                System.out.println(con.getID() + "\t" + con.getUser()+ "@" + con.getRemotehost());
            }
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
            System.err.println(ex.toString());
        }
    }
}
