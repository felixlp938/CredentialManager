package de.felixletsplays.CredentialManager.GUI.Tabs.Connections;

import de.felixletsplays.CredentialManager.Connection.Connection;
import de.felixletsplays.CredentialManager.Connection.Utils;
import de.felixletsplays.CredentialManager.GUI.MainWindow;
import de.felixletsplays.CredentialManager.GUI.Tabs.Connections.Events.DoubleClickConnect;

import java.io.IOException;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 * The connections class as an extraced class
 *
 * @author Felix
 * @since CM-GUI
 */
public class ConnectionsTab extends JPanel {

    public JTable connection;
    public DefaultTableModel model;

    public final String[] colNames = {"ID", "Username", "Remotehost"};

    public ConnectionsTab(MainWindow w) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setName("connections");

        model = new DefaultTableModel();
        connection = new JTable(model);
        
        connection.addMouseListener(new DoubleClickConnect(w));
    }

    public void flush() {
        try {
            this.removeAll();
            connection.removeAll();
            model.setRowCount(0);
            model.getDataVector().removeAllElements();
            model.fireTableDataChanged();
            
            model.setColumnIdentifiers(colNames);
            connection.getTableHeader().setReorderingAllowed(false);
            
            for (Connection c : new Utils().listAllConnections()) {
                model.addRow(new Object[]{c.getID(), c.getUser(), c.getRemotehost()});
            }
            
            connection.setDefaultEditor(Object.class, null);
            connection.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            
            this.add(new JScrollPane(connection));
        } catch (IOException ex) {
            Logger.getLogger(ConnectionsTab.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
