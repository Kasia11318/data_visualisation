package View;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class ApplicationWindow extends JFrame {

    private String windowTitle;
    private Dimension windowSize;

    public ApplicationWindow(String windowTitle) {
        this.windowTitle = windowTitle;
        this.windowSize = new Dimension(700, 500);
    }

    public String getWindowTitle() {

        return windowTitle;
    }

    public void setWindowTitle(String windowTitle) {

        this.windowTitle = windowTitle;
    }

    public Dimension getWindowSize() {

        return windowSize;
    }

    public void setWindowSize(Dimension windowSize) {
        this.windowSize = windowSize;
    }

    public void run() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle(windowTitle);
        this.setSize(this.getWindowSize());
        this.centeringWindow();

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JButton createProjectButton = new JButton("Create project", createImageIcon("images/create.png"));
        createProjectButton.setBorder(new LineBorder(Color.black, 2));

        this.add(createProjectButton, gbc);
        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {

            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
        this.setSize(getWindowSize());
        this.setVisible(true);
    }

    public void runStartScreen() {
        JFrame jFrame = new JFrame("NEW PROJECT");
        jFrame.setSize(new Dimension(400, 300));
    }

    private void centeringWindow() {
        // place window in the center
        Dimension center = new Dimension(
                (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth()) / 2,
                (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight()) / 2);
        this.setLocation(
                (int) (center.getWidth() - (windowSize.getWidth() / 2)),
                (int) (center.getHeight() - (windowSize.getHeight() / 2)));
    }

    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = ApplicationWindow.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
}
