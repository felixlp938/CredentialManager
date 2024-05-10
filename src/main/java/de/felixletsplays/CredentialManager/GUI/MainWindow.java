package de.felixletsplays.CredentialManager.GUI;

import de.felixletsplays.CredentialManager.GUI.Events.TabsChangeEvent;
import de.felixletsplays.CredentialManager.GUI.Tabs.Console.ConsoleTab;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Main class for the main window
 *
 * @author Felix
 */
public class MainWindow extends JFrame {

    public JTabbedPane tabs;

    public ConsoleTab console = new ConsoleTab();
    public JPanel allConnections;
    public JPanel about;

    public MainWindow() {
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) {
        }

        this.tabs = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
        tabs.addChangeListener(new TabsChangeEvent(this));
        
        this.allConnections = new JPanel();
        allConnections.setLayout(new BoxLayout(allConnections, BoxLayout.Y_AXIS));
        allConnections.setName("connections");

        this.about = new JPanel();
        about.setLayout(new BoxLayout(about, BoxLayout.Y_AXIS));
        about.setName("about");
        
        tabs.addTab("All connections", allConnections);
        tabs.addTab("Console", console);
        tabs.addTab("About", about);
        
        console.setEnabled(false);

        this.add(tabs);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("SSH-Connection Manager");
        this.setSize(new Dimension(1024, 512));
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        this.setType(Type.UTILITY);
        this.setVisible(true);
    }
    
    /**
     * Rebuild the console tab
     */
    public void rebuildConsoleTab() {
        console.flush();
    }
    
    /**
     * Rebuild the all connections tab
     */
    public void rebuildConnectionsTab() {
    }

    /**
     * Rebuild the about tab
     */
    public void rebuildAboutTab() {
    }

}
