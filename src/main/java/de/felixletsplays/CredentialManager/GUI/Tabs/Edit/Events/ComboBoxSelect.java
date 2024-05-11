package de.felixletsplays.CredentialManager.GUI.Tabs.Edit.Events;

import de.felixletsplays.CredentialManager.Connection.Utils;
import de.felixletsplays.CredentialManager.GUI.Tabs.Edit.EditTab;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.IOException;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Combo box select event
 *
 * @author Felix
 */
public class ComboBoxSelect implements ActionListener {

    private EditTab tab;

    public ComboBoxSelect(EditTab t) {
        this.tab = t;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String selected = tab.idSelect.getItemAt(tab.idSelect.getSelectedIndex());
            if (!selected.equalsIgnoreCase("Select an ID") && selected != null) {
                tab.setConnection(new Utils().readConnectionConfig(selected));
                tab.update.setEnabled(true);
                tab.delete.setEnabled(true);
            }
        } catch (IOException ex) {
            Logger.getLogger(ComboBoxSelect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
