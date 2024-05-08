package de.felixletsplays.CredentialManager;

/**
 * Used to parse commands
 *
 * @author Felix
 */
public class Commands {

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

    public void create() {
        System.out.println("Create connection");

    }

    public void delete() {
        System.out.println("Delete connection");
    }

    public void modify() {
        System.out.println("Modify connection");
    }

    public void list() {
        System.out.println("All connections");
    }

    public void view() {
        System.out.println("View connection");
    }
}
