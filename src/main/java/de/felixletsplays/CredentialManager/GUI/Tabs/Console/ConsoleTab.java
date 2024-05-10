package de.felixletsplays.CredentialManager.GUI.Tabs.Console;

import de.felixletsplays.CredentialManager.GUI.MainWindow;
import de.felixletsplays.CredentialManager.GUI.Tabs.Console.Events.SubmitButtonPressEvent;
import de.felixletsplays.CredentialManager.GUI.Utils.TextAreaOutputStream;

import java.io.PrintStream;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * The console tab as an extracted class
 *
 * @author Felix
 * @since CM-GUI
 */
public class ConsoleTab extends JPanel {

    private JTextArea text;

    public JTextField command;
    private JButton submit;

    public ConsoleTab(MainWindow w) {
        text = new JTextArea();
        text.setEditable(false);

        w.ps = new PrintStream(new TextAreaOutputStream(text));
        System.setOut(w.ps);
        System.out.println("- - -\tWelcome\t- - -");
        
        command = new JTextField();
        command.setEnabled(true);
        command.setEditable(true);
        command.addActionListener(new SubmitButtonPressEvent(command));
        
        submit = new JButton("Send to remote");
        submit.addActionListener(new SubmitButtonPressEvent(command));

        flush();

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setName("console");
    }

    public void flush() {
        this.removeAll();
        this.add(text);
        this.add(new JSeparator());
        this.add(command);
        this.add(submit);
    }
}
