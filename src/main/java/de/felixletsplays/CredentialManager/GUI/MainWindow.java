package de.felixletsplays.CredentialManager.GUI;

import de.felixletsplays.CredentialManager.GUI.Events.TabsChangeEvent;
import de.felixletsplays.CredentialManager.GUI.Tabs.About.AboutTab;
import de.felixletsplays.CredentialManager.GUI.Tabs.Connections.ConnectionsTab;
import de.felixletsplays.CredentialManager.GUI.Tabs.Create.CreateTab;
import de.felixletsplays.CredentialManager.GUI.Tabs.Edit.EditTab;
import de.felixletsplays.CredentialManager.GUI.Tabs.View.ViewTab;

import java.awt.Dimension;

import java.io.InputStream;
import java.io.PrintStream;

import javax.swing.JFrame;
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

    public final InputStream backupOfSysin = System.in;
    public PrintStream ps;
    
    public JTabbedPane tabs;

    public ConnectionsTab allConnections = new ConnectionsTab(this);
    public AboutTab about = new AboutTab();
    
    public CreateTab create = new CreateTab();
    public ViewTab view = new ViewTab();
    public EditTab edit = new EditTab();

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
        
        tabs.addTab("All connections", allConnections);
        tabs.addTab("View", view);
        tabs.addTab("Edit", edit);
        tabs.addTab("Create", create);
        tabs.addTab("About", about);

        //Icon
        //this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icon.png")));
        
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
     * Rebuild the all connections tab
     */
    public void rebuildConnectionsTab() {
        allConnections.flush();
    }

    /**
     * Rebuild the about tab
     */
    public void rebuildAboutTab() {
        about.flush();
    }
    
    /**
     * Rebuild the create tab
     */
    public void rebuildCreateTab() {
        create.flush();
    }
    
    /**
     * Rebuild the view tab
     */
    public void rebuildViewTab() {
        view.flush();
    }
    
    /**
     * Rebuild the edit tab
     */
    public void rebuildEditTab() {
        edit.flush();
    }

}
