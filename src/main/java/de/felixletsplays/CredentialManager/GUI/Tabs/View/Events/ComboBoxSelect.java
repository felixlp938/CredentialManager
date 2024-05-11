package de.felixletsplays.CredentialManager.GUI.Tabs.View.Events;

import de.felixletsplays.CredentialManager.Connection.Utils;
import de.felixletsplays.CredentialManager.GUI.Tabs.View.ViewTab;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.IOException;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JComboBox;

/**
 * ComboBox select
 *
 * @author Felix
 */
public class ComboBoxSelect implements ActionListener {

    public ViewTab tab;

    public ComboBoxSelect(ViewTab t) {
        this.tab = t;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            JComboBox<String> cb = (JComboBox) e.getSource();
            String selected = cb.getItemAt(cb.getSelectedIndex());
            if (!selected.equalsIgnoreCase("Select an ID")) {
                tab.setView(new Utils().readConnectionConfig(selected));
            }
        } catch (IOException ex) {
            Logger.getLogger(ComboBoxSelect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
