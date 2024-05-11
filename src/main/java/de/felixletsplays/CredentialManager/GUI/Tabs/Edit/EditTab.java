package de.felixletsplays.CredentialManager.GUI.Tabs.Edit;

import de.felixletsplays.CredentialManager.Connection.Connection;
import de.felixletsplays.CredentialManager.Connection.ConnectionBuilder;
import de.felixletsplays.CredentialManager.Connection.Utils;
import de.felixletsplays.CredentialManager.GUI.Tabs.Edit.Events.ComboBoxSelect;
import de.felixletsplays.CredentialManager.GUI.Tabs.Edit.Events.DeleteClickEvent;
import de.felixletsplays.CredentialManager.GUI.Tabs.Edit.Events.UpdateClickEvent;

import java.io.IOException;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

/**
 * Class of an edit tab
 *
 * @author Felix
 */
public class EditTab extends JPanel {

    public JComboBox<String> idSelect;

    public JTextField id;
    public JTextField user;
    public JTextField remote;
    public JTextField key;
    public JTextField args;

    public JButton delete;
    public JButton update;

    public EditTab() {
        try {
            this.setName("edit");
            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

            delete = new JButton("Delete connection");
            delete.addActionListener(new DeleteClickEvent(this));
            delete.setEnabled(false);

            update = new JButton("Update connection");
            update.addActionListener(new UpdateClickEvent(this));
            update.setEnabled(false);

            id = new JTextField("ID");
            user = new JTextField("Username");
            remote = new JTextField("Remotehost");
            key = new JTextField("Path to keyfile");
            args = new JTextField("Args");

            idSelect = new JComboBox<>();
            idSelect.addActionListener(new ComboBoxSelect(this));
            idSelect.addItem("Select an ID");
            idSelect.setSelectedIndex(0);

            for (Connection c : new Utils().listAllConnections()) {
                idSelect.addItem(c.getID());
            }
        } catch (IOException ex) {
            Logger.getLogger(EditTab.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void flush() {
        this.removeAll();
        this.add(idSelect);
        this.add(new JLabel("Connection ID:"));
        this.add(id);
        this.add(new JLabel("Connection Username:"));
        this.add(user);
        this.add(new JLabel("Connection Remotehost:"));
        this.add(remote);
        this.add(new JLabel("Connection Keyfile Path (empty: password auth will be used):"));
        this.add(key);
        this.add(new JLabel("Connection Args (optional):"));
        this.add(args);
        this.add(new JSeparator());
        this.add(update);
        this.add(delete);
    }

    /**
     * Get the new connection
     *
     * @return The new connection as {@link Connection}
     */
    public Connection getUpdatedConnection() {
        return new ConnectionBuilder()
                .withID(id.getText())
                .withUser(user.getText())
                .withRemotehost(remote.getText())
                .withKeyPath(key.getText())
                .withArgs(args.getText())
                .create();
    }

    /**
     * Set the connection (used as like a blueprint; set the textfields to the
     * already given connection entry)
     *
     * @param c The connection
     */
    public void setConnection(Connection c) {
        id.setText(c.getID());
        user.setText(c.getUser());
        remote.setText(c.getRemotehost());
        key.setText(c.getKeyPath());
        args.setText(c.getArgs());
    }

}
