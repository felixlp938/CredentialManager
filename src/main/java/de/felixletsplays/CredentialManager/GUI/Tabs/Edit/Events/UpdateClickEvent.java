package de.felixletsplays.CredentialManager.GUI.Tabs.Edit.Events;

import de.felixletsplays.CredentialManager.Connection.Utils;
import de.felixletsplays.CredentialManager.GUI.Tabs.Edit.EditTab;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.IOException;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

/**
 * Update an id
 * 
 * @author Felix
 */
public class UpdateClickEvent implements ActionListener {

    private EditTab tab;
    
    public UpdateClickEvent(EditTab t) {
        this.tab = t;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            new Utils().updateConfigfile(tab.getUpdatedConnection());
        } catch (IOException ex) {
            Logger.getLogger(UpdateClickEvent.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            JOptionPane.showMessageDialog(tab, "Updated connection", "Update", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
}
