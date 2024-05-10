package de.felixletsplays.CredentialManager.GUI.Tabs.Connections.Events;

import de.felixletsplays.CredentialManager.Connection.Connection;
import de.felixletsplays.CredentialManager.Connection.Utils;
import de.felixletsplays.CredentialManager.GUI.MainWindow;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.io.IOException;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JTable;

/**
 * Handle interacting with the JTable
 *
 * @author Felix
 */
public class DoubleClickConnect implements MouseListener {

    private MainWindow window;
    
    public DoubleClickConnect(MainWindow w) {
        window = w;
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        JTable table = (JTable) e.getSource();
    
        Point p = e.getPoint();
        int row = table.rowAtPoint(p);
        
        if (e.getClickCount() == 2 && table.getSelectedRow() != -1) {
            try {
                String id = table.getValueAt(row, 0).toString();
                
                Connection c = new Utils().readConnectionConfig(id);
                c.connect();
                
                window.console.setVisible(true);
                
            } catch (IOException | InterruptedException ex) {
                Logger.getLogger(DoubleClickConnect.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
