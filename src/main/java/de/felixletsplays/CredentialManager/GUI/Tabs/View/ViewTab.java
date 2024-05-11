package de.felixletsplays.CredentialManager.GUI.Tabs.View;

import de.felixletsplays.CredentialManager.Connection.Connection;
import de.felixletsplays.CredentialManager.Connection.Utils;
import de.felixletsplays.CredentialManager.GUI.Tabs.View.Events.ComboBoxSelect;

import java.io.IOException;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

/**
 * Class to represent a view tab
 *
 * @author Felix
 */
public class ViewTab extends JPanel {

    private JComboBox<String> idSelect;

    private JLabel id;
    private JLabel user;
    private JLabel remote;
    private JLabel key;
    private JLabel args;

    public ViewTab() {
        try {
            this.setName("view");
            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

            id = new JLabel("ID: -/-");
            user = new JLabel("User: -/-");
            remote = new JLabel("Remotehost: -/-");
            key = new JLabel("Keyfile: -/-");
            args = new JLabel("Args: -/-");

            idSelect = new JComboBox<>();
            idSelect.addActionListener(new ComboBoxSelect(this));

            idSelect.addItem("Select an ID");
            //Add ID's to the combo box
            for (Connection c : new Utils().listAllConnections()) {
                idSelect.addItem(c.getID());
            }
            idSelect.setSelectedIndex(0);
        } catch (IOException ex) {
            Logger.getLogger(ViewTab.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void flush() {
        this.removeAll();
        this.add(idSelect);
        this.add(id);
        this.add(user);
        this.add(remote);
        this.add(key);
        this.add(args);
        this.add(new JSeparator());
    }

    public void setView(Connection c) {
        this.id.setText("ID: " + c.getID());
        this.user.setText("User: " + c.getUser());
        this.remote.setText("Remotehost: " + c.getRemotehost());
        this.key.setText("Keyfile: " + c.getKeyPath());
        this.args.setText("Args: " + c.getArgs());
    }
}
