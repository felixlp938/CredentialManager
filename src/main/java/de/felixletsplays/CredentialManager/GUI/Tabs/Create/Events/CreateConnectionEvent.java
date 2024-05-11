package de.felixletsplays.CredentialManager.GUI.Tabs.Create.Events;

import de.felixletsplays.CredentialManager.Connection.ConnectionBuilder;
import de.felixletsplays.CredentialManager.Connection.Utils;
import de.felixletsplays.CredentialManager.GUI.Tabs.Create.CreateTab;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.IOException;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

/**
 * Handle the create button click
 *
 * @author Felix
 */
public class CreateConnectionEvent implements ActionListener {

    private CreateTab tab;

    public CreateConnectionEvent(CreateTab t) {
        this.tab = t;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            new Utils().createConnectionConfig(
                    new ConnectionBuilder()
                            .withID(tab.id.getText())
                            .withUser(tab.user.getText())
                            .withRemotehost(tab.remotehost.getText())
                            .withKeyPath(tab.key.getText())
                            .withArgs(tab.args.getText())
                            .create());
        } catch (IOException ex) {
            Logger.getLogger(CreateConnectionEvent.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            JOptionPane.showMessageDialog(tab, "Connection created!", "Done", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
