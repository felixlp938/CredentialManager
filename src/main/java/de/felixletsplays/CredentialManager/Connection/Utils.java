package de.felixletsplays.CredentialManager.Connection;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.Properties;

/**
 * Use to save, load and modify config files
 *
 * @author Felix
 */
public class Utils {

    /**
     * Path for the connection files
     *
     * Example: On Linux the path is: /home/username/.cm
     */
    public static final Path CONFIGDIR = Paths.get(System.getProperty("user.home"), ".cm");

    /**
     * Get the connection file by an ID
     *
     * @param id ID of the connection file
     * @return The connection file
     */
    private File getConnectionFileByID(String id) {
        return Paths.get(CONFIGDIR.toString(), id).toFile();
    }

    /**
     * Create and save a config file
     *
     * @param c The connection
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void createConnectionConfig(Connection c) throws FileNotFoundException, IOException {
        Properties p = new Properties();
        p.put("id", c.getID());
        p.put("remote", c.getRemotehost());
        p.put("user", c.getUser());
        p.put("key", c.getKeyPath());
        p.put("args", c.getArgs());
        p.store(new BufferedOutputStream(new FileOutputStream(getConnectionFileByID(c.getID()))), "SSH-Connection " + c.getID());
    }

    /**
     * Read a connection by the given id
     *
     * @param id ID of the connection file
     * @return The connection as {@link Connection}
     * @throws FileNotFoundException
     * @throws IOException
     */
    public Connection readConnectionConfig(String id) throws FileNotFoundException, IOException {
        Properties p = new Properties();
        p.load(new BufferedInputStream(new FileInputStream(getConnectionFileByID(id))));

        Connection c = new Connection(new ConnectionBuilder()
                .withID(p.getProperty("id"))
                .withRemotehost(p.getProperty("remote"))
                .withUser(p.getProperty("user"))
                .withKeyPath(p.getProperty("key"))
                .withArgs(p.getProperty("args")));

        return c;
    }
    
    /**
     * Delete a connection file
     * 
     * @param id ID of the connection file
     * @throws IOException 
     */
    public void deleteConnectionConfig(String id) throws IOException {
        if (getConnectionFileByID(id).exists()) {
            getConnectionFileByID(id).delete();
            System.out.println("Deleted connection with id " + id);
        } else {
            System.out.println("This connection does not exists!");
        }
    }
    
    /**
     * Update a connection file by deleting and recreating it
     * 
     * @param c The connection as {@link Connection}
     * @throws IOException 
     */
    public void updateConfigfile(Connection c) throws IOException {
        deleteConnectionConfig(c.getID());
        createConnectionConfig(c);
    }

}
