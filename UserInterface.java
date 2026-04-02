import java.awt.BorderLayout;
import java.awt.Dimension;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * This class implements a simple graphical user interface with a
 * text entry area, a text output area and an optional image.
 *
 * @author Michael Kolling and David J. Barnes + D. Bureau + Raphaël Quillaud
 */
public class UserInterface implements ActionListener {
    private GameEngine aEngine;
    private JFrame aMyFrame;
    private JTextField aEntryField;
    private JTextArea aLog;
    private JLabel aImage;

    /**
     * Constructs a UserInterface linked to the given game engine.
     *
     * @param pGameEngine the game engine used to process commands
     */
    public UserInterface(final GameEngine pGameEngine) {
        this.aEngine = pGameEngine;
        this.createGUI();
    }

    /**
     * Prints text in the text area.
     *
     * @param pText the text to display
     */
    public void print(final String pText) {
        this.aLog.append(pText);
        this.aLog.setCaretPosition(this.aLog.getDocument().getLength());
    }

    /**
     * Prints text in the text area followed by a line break.
     *
     * @param pText the text to display
     */
    public void println(final String pText) {
        this.print(pText + "\n");
    }

    /**
     * Displays an image in the interface.
     *
     * @param pImageName the image file name
     */
    public void showImage(final String pImageName) {
        URL vImageURL = this.getClass().getClassLoader().getResource(pImageName);
        if (vImageURL == null) {
            System.out.println("Image not found : " + pImageName);
        }
        else {
            ImageIcon vIcon = new ImageIcon(vImageURL);
            this.aImage.setIcon(vIcon);
            this.aMyFrame.pack();
        }
    }

    /**
     * Enables or disables command input.
     *
     * @param pOnOff true to enable input, false otherwise
     */
    public void enable(final boolean pOnOff) {
        this.aEntryField.setEditable(pOnOff);
        if (pOnOff) {
            this.aEntryField.getCaret().setBlinkRate(500);
            this.aEntryField.addActionListener(this);
        }
        else {
            this.aEntryField.getCaret().setBlinkRate(0);
            this.aEntryField.removeActionListener(this);
        }
    }

    /**
     * Creates the graphical user interface.
     */
    private void createGUI() {
        this.aMyFrame = new JFrame("Henriette's Feast");
        this.aEntryField = new JTextField(34);

        this.aLog = new JTextArea();
        this.aLog.setEditable(false);

        JScrollPane vScroller = new JScrollPane(this.aLog);
        vScroller.setPreferredSize(new Dimension(200, 200));
        vScroller.setMinimumSize(new Dimension(100, 100));

        this.aImage = new JLabel();

        JPanel vPanel = new JPanel();
        vPanel.setLayout(new BorderLayout());
        vPanel.add(this.aImage, BorderLayout.NORTH);
        vPanel.add(vScroller, BorderLayout.CENTER);
        vPanel.add(this.aEntryField, BorderLayout.SOUTH);

        this.aMyFrame.getContentPane().add(vPanel, BorderLayout.CENTER);

        this.aEntryField.addActionListener(this);

        this.aMyFrame.addWindowListener(
                new WindowAdapter() {
                    @Override public void windowClosing(final WindowEvent pE)
                    {
                        System.exit(0);
                    }
                }
        );

        this.aMyFrame.pack();
        this.aMyFrame.setVisible(true);
        this.aEntryField.requestFocus();
    }

    /**
     * Reacts to an action performed in the text field.
     *
     * @param pE the generated action event
     */
    @Override
    public void actionPerformed(final ActionEvent pE) {
        this.processCommand();
    }

    /**
     * Reads the command entered by the user and sends it to the game engine.
     */
    private void processCommand() {
        String vInput = this.aEntryField.getText();
        this.aEntryField.setText("");
        this.aEngine.interpretCommand(vInput);
    }
} // UserInterface