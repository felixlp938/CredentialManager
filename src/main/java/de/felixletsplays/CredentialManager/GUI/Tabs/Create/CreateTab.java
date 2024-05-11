package de.felixletsplays.CredentialManager.GUI.Tabs.Create;

import de.felixletsplays.CredentialManager.GUI.Tabs.Create.Events.CreateConnectionEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

/**
 * The create tab in the main window
 *
 * @author Felix
 */
public class CreateTab extends JPanel {

    public JTextField id;
    public JTextField user;
    public JTextField remotehost;
    public JTextField key;
    public JTextField args;

    private JButton submit;

    public CreateTab() {
        this.setName("create");
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        id = new JTextField("ID");
        user = new JTextField("User");
        remotehost = new JTextField("Remotehost");
        key = new JTextField("Path to keyfile");
        args = new JTextField("ARGS");

        submit = new JButton("Click to create");
        submit.addActionListener(new CreateConnectionEvent(this));
        submit.setAlignmentX(CENTER_ALIGNMENT);
    }

    public void flush() {
        this.removeAll();
        this.add(id);
        this.add(user);
        this.add(remotehost);
        this.add(key);
        this.add(new JLabel("Hint: If no path to a key file is given, password auth will be used!"));
        this.add(args);
        this.add(new JLabel("Hint: ARGS are optional"));
        this.add(new JSeparator());
        this.add(submit);
        this.add(new JSeparator());
    }
}
