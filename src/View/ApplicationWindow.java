package View;

import Controller.MainController;
import Model.Project;
import com.sun.deploy.panel.NodeBorder;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;

public class ApplicationWindow extends JFrame {

    private String windowTitle;
    private Dimension windowSize;
    private boolean isCreated;

    public ApplicationWindow(String windowTitle) {
        this.windowTitle = windowTitle;
        this.windowSize = new Dimension(1000, 800);
        this.isCreated = false;
    }

    public boolean isCreated() {
        return isCreated;
    }

    public void setCreated(boolean created) {
        isCreated = created;
    }

    public String getWindowTitle() {

        return windowTitle;
    }

    public void setWindowTitle(String windowTitle) {

        this.windowTitle = windowTitle;
    }

    private Dimension getWindowSize() {

        return windowSize;
    }

    public void setWindowSize(Dimension windowSize) {
        this.windowSize = windowSize;
    }

    public void run(MainController mainController) {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle(windowTitle);
        this.setSize(this.getWindowSize());
        this.centeringWindow(this);

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JButton createProjectButton = new JButton("Create project", createImageIcon("images/create.png"));
        createProjectButton.setBorder(new LineBorder(Color.black, 2));
        createProjectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                runStartScreen(mainController);
            }
        });

        if (this.isCreated) {
            JPanel projectPanel = new JPanel();
            projectPanel.setBorder(BorderFactory.createTitledBorder("Project"));

            this.add(projectPanel);
        }

        createProjectButton.setPreferredSize(new Dimension(300, 100));

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

    private void runStartScreen(MainController mainController) {
        JFrame jFrame = new JFrame("NEW PROJECT");
        Box boxLabel = Box.createVerticalBox();
        Box box = Box.createVerticalBox();
        JPanel jPanel1 = new JPanel();
        JPanel jPanel2 = new JPanel();
        JPanel jPanel3 = new JPanel();
        JLabel fileTypesLabel = new JLabel("File types: ", SwingConstants.CENTER);
        JLabel savedPathLabel = new JLabel("Saved path: ", SwingConstants.CENTER);
        JLabel isDefaultSeriesNamesLabel = new JLabel("Deafult series names: ", SwingConstants.CENTER);
        JLabel projectNameLabel = new JLabel("Project name: ");
        JLabel isDefaultDimensionNamesLabel = new JLabel("Default dimension names: ", SwingConstants.CENTER);
        JTextField savedPathField = new JTextField();
        JTextField projectNameField = new JTextField();
        JButton selectSavePath = new JButton("Select");
        JButton create = new JButton("Create");
        JCheckBox isDefalultSeriesNames = new JCheckBox();
        JCheckBox isDefaultDimensionNames = new JCheckBox();

        jPanel1.setName("Create new project");
        jPanel2.setName("SavedPath");
        jPanel3.setName("Create");

        savedPathField.setPreferredSize(new Dimension(250, 30));
        projectNameField.setPreferredSize(new Dimension(250,30));

        Dimension startScreenSize = new Dimension(700, 610);
        jFrame.setSize(startScreenSize);
        this.centeringWindow(jFrame);
        jFrame.setLayout(new GridLayout(3,1));
        jFrame.setDefaultCloseOperation(HIDE_ON_CLOSE);
        jFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
            }
        });

        DefaultComboBoxModel fileTypes = new DefaultComboBoxModel();
        fileTypes.addElement(".txt");
        fileTypes.addElement("to do...");
        JComboBox fileTypesComboBox = new JComboBox(fileTypes);
        fileTypesComboBox.setName("File types");

        selectSavePath.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

                if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION ) {
                    savedPathField.setText(chooser.getSelectedFile().getPath());
                }
            }
        });

        create.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String projectName = projectNameField.getText();
                String savedPath = savedPathField.getText();
                String defaultFilesTypes = fileTypesComboBox.getSelectedItem().toString();
                boolean defaultDataSeriesNames = isDefalultSeriesNames.isSelected();
                boolean defaultDimensionNames = isDefaultDimensionNames.isSelected();

                Project project = onClickCreateButton(projectName, savedPath, defaultFilesTypes, defaultDataSeriesNames, defaultDimensionNames);
                mainController.setProject(project);
                jFrame.dispose();
                setCreated(true);
                getContentPane().removeAll();
                getContentPane().repaint();
                JOptionPane.showMessageDialog(null, "Project was successfully created!");
            }
        });

        boxLabel.add(Box.createVerticalStrut(20));
        boxLabel.add(fileTypesLabel);
        boxLabel.add(Box.createVerticalStrut(25));
        boxLabel.add(savedPathLabel);
        boxLabel.add(Box.createVerticalStrut(25));
        boxLabel.add(projectNameLabel);
        boxLabel.add(Box.createVerticalStrut(25));
        boxLabel.add(isDefaultSeriesNamesLabel);
        boxLabel.add(Box.createVerticalStrut(25));
        boxLabel.add(isDefaultDimensionNamesLabel);

        box.add(Box.createVerticalStrut(10));
        box.add(fileTypesComboBox);
        box.add(Box.createVerticalStrut(10));

        jPanel2.add(savedPathField);
        jPanel2.add(selectSavePath);

        box.add(jPanel2);
        box.add(Box.createVerticalStrut(15));
        box.add(projectNameField);
        box.add(Box.createVerticalStrut(15));
        box.add(isDefalultSeriesNames);
        box.add(Box.createVerticalStrut(15));
        box.add(isDefaultDimensionNames);

        jPanel3.setBackground(Color.GRAY);
        jPanel3.setBorder(new NodeBorder(Color.DARK_GRAY, 3));

        jPanel1.add(boxLabel);
        jPanel1.add(box);
        jPanel3.add(create);
        jFrame.add(jPanel1);
        jFrame.add(Box.createVerticalStrut(15));
        jFrame.add(jPanel3);
        jFrame.setVisible(true);
    }

    private void centeringWindow(JFrame jFrame) {
        // place window in the center
        Dimension center = new Dimension(
                (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth()) / 2,
                (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight()) / 2);
        jFrame.setLocation(
                (int) (center.getWidth() - (jFrame.getSize().getWidth() / 2)),
                (int) (center.getHeight() - (jFrame.getSize().getHeight() / 2)));
    }

    private static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = ApplicationWindow.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    private Project onClickCreateButton (
            String projectName,
            String pathSavedFile,
            String defaultOpenFilesType,
            boolean defaultDataSeriesNames,
            boolean defaultDimensionNames
    ) {
        Project project = new Project();
        project.newProject(projectName, pathSavedFile, defaultOpenFilesType, defaultDataSeriesNames, defaultDimensionNames);

        return project;
    }
}
