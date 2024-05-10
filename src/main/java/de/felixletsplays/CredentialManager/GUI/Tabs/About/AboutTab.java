package de.felixletsplays.CredentialManager.GUI.Tabs.About;

import java.io.IOException;
import java.io.InputStream;

import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Class to handle the about tab
 *
 * @author Felix
 */
public class AboutTab extends JPanel {

    private String licensetext;
    
    private JTextArea ta;
    
    public AboutTab() {
        this.ta = new JTextArea("LICENSE");
        ta.setEditable(false);
        try {
            this.setName("about");
            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            
            InputStream fileIn = getClass().getResourceAsStream("/LICENSE");
            licensetext = new String(fileIn.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException ex) {
            Logger.getLogger(AboutTab.class.getName()).log(Level.SEVERE, null, ex);
            licensetext = "Something went wrong!\nThis software is licensed under the GPL-3 (see: https://www.gnu.org/licenses/gpl-3.0.txt)";
        }
    }

    public void flush() {
        this.removeAll();
        this.add(new JLabel("About the CredentialManager", JLabel.CENTER){
            @Override
            public void setAlignmentX(float alignmentX) {
                super.setAlignmentX(CENTER_ALIGNMENT);
            }
        });
        this.add(new JLabel("GitHub: https://github.com/felixlp938/CredentialManager", JLabel.CENTER){
            @Override
            public void setAlignmentX(float alignmentX) {
                super.setAlignmentX(CENTER_ALIGNMENT);
            }
        });
        this.add(new JLabel("Licensed under GPL-3", JLabel.CENTER){
            @Override
            public void setAlignmentX(float alignmentX) {
                super.setAlignmentX(CENTER_ALIGNMENT);
            }
        });
        ta.setText(licensetext);
        ta.setCaretPosition(0);
        this.add(new JScrollPane(ta));
    }
}
