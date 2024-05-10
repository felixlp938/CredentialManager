package de.felixletsplays.CredentialManager.GUI.Tabs.Console.Events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.swing.JTextField;

/**
 * Handle the button click to submit
 *
 * @author Felix
 */
public class SubmitButtonPressEvent implements ActionListener {

    private JTextField commandText;

    public SubmitButtonPressEvent(JTextField command) {
        this.commandText = command;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        InputStream oldSysIn = System.in;
        InputStream inputFromCommandField = new ByteArrayInputStream(commandText.getText().getBytes());
        try {
            System.setIn(inputFromCommandField);
        } finally {
            System.setIn(oldSysIn);
            commandText.setText(null);
        }
    }

}
