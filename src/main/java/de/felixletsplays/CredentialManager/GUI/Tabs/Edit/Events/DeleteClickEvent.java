package de.felixletsplays.CredentialManager.GUI.Tabs.Edit.Events;

import de.felixletsplays.CredentialManager.Connection.Utils;
import de.felixletsplays.CredentialManager.GUI.Tabs.Edit.EditTab;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.IOException;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

/*
 * Delete an id
 * 
 * @author Felix
 */
public class DeleteClickEvent implements ActionListener {

    private EditTab tab;
    
    public DeleteClickEvent(EditTab t) {
        this.tab = t;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            new Utils().deleteConnectionConfig(tab.idSelect.getItemAt(tab.idSelect.getSelectedIndex()));
        } catch (IOException ex) {
            Logger.getLogger(DeleteClickEvent.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            JOptionPane.showMessageDialog(tab, "Deleted connection with id " + tab.idSelect.getItemAt(tab.idSelect.getSelectedIndex()), "Delete", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
}
