package View;

import Controller.MainController;
import Model.Data;
import Model.Project;
import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MainMenuBar extends JMenuBar {

    public MainMenuBar(MainController mainController, ApplicationWindow applicationWindow) {
        createMenuFile(mainController, applicationWindow);
    }

    private void createMenuFile(MainController mainController, ApplicationWindow applicationWindow) {
        JMenuItem menuItem;
        JMenu menu = new JMenu("File");
        this.add(menu);

        ImageIcon createIcon = createImageIcon("images/add.png");

        menuItem = new JMenuItem("Create project", createIcon);
        menuItem.setActionCommand("createProject");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                applicationWindow.runStartScreen(mainController);
            }
        });

        menu.add(menuItem);
        menu.addSeparator();

        menuItem = new JMenuItem("Load project");
        menuItem.setActionCommand("loadProject");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                chooser.setFileFilter(new FileNameExtensionFilter("*.mgfk", "mgfk"));

                if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION ) {
                    try {
                        Project project = new Project();
                        project.loadProject(chooser.getSelectedFile().getPath());
                        mainController.setProject(project);
                        applicationWindow.run(mainController);
                    } catch (IOException exception) {
                        JOptionPane.showMessageDialog(null, exception.getMessage());
                    }
                }
            }
        });

        menu.add(menuItem);
        menu.addSeparator();

        menuItem = new JMenuItem("Import data");
        menuItem.setActionCommand("importData");
        if (applicationWindow.isCreated()) {
            menuItem.setEnabled(true);
        } else {
            menuItem.setEnabled(false);
        }
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                chooser.setFileFilter(new FileNameExtensionFilter("*.txt", "txt", "TEXT FILES", "text", "*.csv", "csv"));

                if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION ) {
                    try {
                        Data data = mainController.getDataController().importData(chooser.getSelectedFile().getName());
                        mainController.getProject().setData(data);
                        applicationWindow.run(mainController);
                    } catch (IOException exception) {
                        JOptionPane.showMessageDialog(null, exception.getMessage());
                    }

                }
            }
        });

        menu.add(menuItem);
        menu.addSeparator();

        menuItem = new JMenuItem("Save");
        menuItem.setActionCommand("save");
        if (applicationWindow.isCreated()) {
            menuItem.setEnabled(true);
        } else {
            menuItem.setEnabled(false);
        }
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    mainController.getProject().saveProject();
                } catch (IOException exception) {
                    JOptionPane.showMessageDialog(null, exception.getMessage());
                }
            }
        });

        menu.add(menuItem);
        menu.addSeparator();

        menuItem = new JMenuItem("Quit");
        menuItem.setActionCommand("quit");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(
                        null,
                        "Are you sure?",
                        "Exit",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);

                if(result == 0) {
                    Window activeWindow = FocusManager.getCurrentManager().getActiveWindow();
                    activeWindow.setVisible(false);
                    activeWindow.dispose();
                    System.exit(0);
                }
            }
        });
        menu.add(menuItem);
    }

    /** Returns an ImageIcon, or null if the path was invalid.
     * @param path
     * @return image icon
     */
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
