package de.felixletsplays.CredentialManager.Connection;

/**
 * Build a {@link #de.felixletsplays.CredentialManager.Connection.Connection()}
 *
 * @author Felix
 */
public class ConnectionBuilder {

    private String ID;

    private String remotehost;
    private String user;

    private String keyPath;

    private String args;

    public String getID() {
        return ID;
    }

    public String getRemotehost() {
        return remotehost;
    }

    public String getUser() {
        return user;
    }

    public String getKeyPath() {
        return keyPath;
    }

    public String getArgs() {
        return args;
    }

    public ConnectionBuilder withID(String id) {
        this.ID = id;
        return this;
    }
    
    public ConnectionBuilder withRemotehost(String remotehost) {
        this.remotehost = remotehost;
        return this;
    }

    public ConnectionBuilder withUser(String user) {
        this.user = user;
        return this;
    }

    public ConnectionBuilder withKeyPath(String keyPath) {
        this.keyPath = keyPath;
        return this;
    }

    public ConnectionBuilder withArgs(String args) {
        this.args = args;
        return this;
    }

    public Connection create() {
        return new Connection(this);
    }

}
