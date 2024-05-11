package de.felixletsplays.CredentialManager.GUI.Utils;

import java.io.IOException;
import java.io.OutputStream;

import javax.swing.JTextArea;

/**
 * Class to redirect a stream to a given {@link JTextArea}
 *
 * @author Felix
 * @author Inspired by StackOverflow: https://stackoverflow.com/a/38664810
 */
public class TextAreaOutputStream extends OutputStream {

    private JTextArea area;

    public TextAreaOutputStream(JTextArea ta) {
        this.area = ta;
    }

    @Override
    public void write(int b) throws IOException {
        area.append(String.valueOf((char)b));
        update();
    }

    public void write(String content) {
        area.append(content);
        update();
    }

    private void update() {
        area.setCaretPosition(area.getDocument().getLength());
        area.update(area.getGraphics());

    }

}
