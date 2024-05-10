package de.felixletsplays.CredentialManager.Connection;

import java.io.IOException;

/**
 * Class to create a connection object
 *
 * @author Felix
 */
public class Connection {

    private String ID;

    private String remotehost;
    private String user;

    private String keyPath;

    private String args;

    /**
     * Create a connection
     * 
     * @param builder {@link ConnectionBuilder}
     */
    public Connection(ConnectionBuilder builder) {
        this.ID = builder.getID();
        this.remotehost = builder.getRemotehost();
        this.user = builder.getUser();
        this.keyPath = builder.getKeyPath();
        this.args = builder.getArgs();
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getRemotehost() {
        return remotehost;
    }

    public void setRemotehost(String remotehost) {
        this.remotehost = remotehost;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getKeyPath() {
        return keyPath;
    }

    public void setKeyPath(String keyPath) {
        this.keyPath = keyPath;
    }

    public String getArgs() {
        return args;
    }

    public void setArgs(String args) {
        this.args = args;
    }

    /**
     * Create a new process and connect with SSH
     *
     * @throws java.io.IOException
     * @throws java.lang.InterruptedException
     */
    public void connect() throws IOException, InterruptedException {
        String keyfilearg = "";
        if (!getKeyPath().isEmpty() || !getKeyPath().isBlank()) {
            keyfilearg = "-i" + keyPath.trim();
        }

        ProcessBuilder builder = new ProcessBuilder("ssh", this.user + "@" + this.remotehost, keyfilearg, args);
        builder.redirectError(ProcessBuilder.Redirect.INHERIT);
        builder.redirectInput(ProcessBuilder.Redirect.INHERIT);
        builder.redirectOutput(ProcessBuilder.Redirect.INHERIT);
        builder.inheritIO();

        Process sshProcess = builder.start();
        sshProcess.waitFor();
    }
}
