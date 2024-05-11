package de.felixletsplays.CredentialManager.GUI.Events;

import de.felixletsplays.CredentialManager.GUI.MainWindow;

import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * This class is used to handle the event of clicking tabs. Because
 * of performance, we rebuild the whole ui
 *
 * @author Felix
 * @since CM-GUI
 */
public class TabsChangeEvent implements ChangeListener {

    private MainWindow mainwindow;

    public TabsChangeEvent(MainWindow w) {
        this.mainwindow = w;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        JTabbedPane choosen = (JTabbedPane) e.getSource();

        if (choosen.getComponentAt(choosen.getSelectedIndex()).getName() == null) {
            return;
        }
              
        if (choosen.getComponentAt(choosen.getSelectedIndex()).getName().equalsIgnoreCase("connections")){
            mainwindow.about.removeAll();
            mainwindow.rebuildConnectionsTab();
        }
        
        if (choosen.getComponentAt(choosen.getSelectedIndex()).getName().equalsIgnoreCase("about")){
            mainwindow.allConnections.removeAll();
            mainwindow.rebuildAboutTab();
        }
    }

}
