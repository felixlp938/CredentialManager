package de.felixletsplays.CredentialManager.GUI.Events;

import de.felixletsplays.CredentialManager.GUI.MainWindow;

import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * This class is used to handle the event of clicking tabs. Because of
 * performance, we rebuild the whole ui
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

        if (choosen.getComponentAt(choosen.getSelectedIndex()).getName().equalsIgnoreCase("connections")) {
            mainwindow.about.removeAll();
            mainwindow.create.removeAll();
            mainwindow.view.removeAll();
            mainwindow.edit.removeAll();
            mainwindow.rebuildConnectionsTab();
        }

        if (choosen.getComponentAt(choosen.getSelectedIndex()).getName().equalsIgnoreCase("create")) {
            mainwindow.about.removeAll();
            mainwindow.allConnections.removeAll();
            mainwindow.view.removeAll();
            mainwindow.edit.removeAll();
            mainwindow.rebuildCreateTab();
        }

        if (choosen.getComponentAt(choosen.getSelectedIndex()).getName().equalsIgnoreCase("view")) {
            mainwindow.about.removeAll();
            mainwindow.allConnections.removeAll();
            mainwindow.create.removeAll();
            mainwindow.edit.removeAll();
            mainwindow.rebuildViewTab();
        }

        if (choosen.getComponentAt(choosen.getSelectedIndex()).getName().equalsIgnoreCase("edit")) {
            mainwindow.about.removeAll();
            mainwindow.allConnections.removeAll();
            mainwindow.create.removeAll();
            mainwindow.view.removeAll();
            mainwindow.rebuildEditTab();
        }

        if (choosen.getComponentAt(choosen.getSelectedIndex()).getName().equalsIgnoreCase("about")) {
            mainwindow.allConnections.removeAll();
            mainwindow.create.removeAll();
            mainwindow.view.removeAll();
            mainwindow.edit.removeAll();
            mainwindow.rebuildAboutTab();
        }
    }

}
