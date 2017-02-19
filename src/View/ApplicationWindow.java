package View;

import Controller.MainController;
import Model.*;
import Model.Point;
import com.sun.deploy.panel.NodeBorder;

import javax.swing.*;
import java.awt.*;
import java.awt.List;
import java.awt.event.*;
import java.util.*;

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
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JMenuBar menuBar = new MainMenuBar(mainController, this);
        menuBar.setBackground(Color.LIGHT_GRAY);

        this.setJMenuBar(menuBar);

        /*
         * when project was created
         */
        if (mainController.getProject() != null) {
            JLabel projectNameLabelL = new JLabel("Project name: ");
            JLabel savedPathLabelL = new JLabel("Saved path: ");
            JLabel filesTypesLabelL = new JLabel("Files types: ");

            JPanel projectManager = new JPanel();
            projectManager.setLayout(new BoxLayout(projectManager, BoxLayout.Y_AXIS));

            if (!mainController.getProject().getData().getData().isEmpty()) {
                JLabel generateGraphLabel = new JLabel("Generate a graph: ");
                JButton twoD = new JButton("2D");
                JButton threeD = new JButton("3D");
                JButton parallel = new JButton("Parallel");

                twoD.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        CoordinateSystem coordinateSystem = new CoordinateSystem();
                        coordinateSystem.setCoordinateSystemType(CoordinateSystem.CoordinateSystemType.TWO_D);

                        java.util.List<java.util.List<Float>> data = mainController.getProject().getData().getData();

                        for (java.util.List<Float> coordinates : data) {
                            Point point = new Point();
                            point.setDimensionals(coordinates);
                            coordinateSystem.addPoint(point);
                        }

                        Diagram diagram = new Diagram(coordinateSystem);
                        diagram.draw();
                        diagram.setVisible(true);
                    }
                });

                threeD.setEnabled(false);
                parallel.setEnabled(false);

                JPanel labels = new JPanel();
                labels.add(generateGraphLabel);

                JPanel buttons = new JPanel();
                buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));
                buttons.add(twoD);
                buttons.add(threeD);
                buttons.add(parallel);

                projectManager.add(labels);
                projectManager.add(buttons);

            }

            JLabel projectNameLabelR = new JLabel(mainController.getProject().getProjectName());
            JLabel savedPathLabelR = new JLabel(mainController.getProject().getPathSavedFile());
            JLabel filesTypesLabelR = new JLabel(mainController.getProject().getDefaultOpenFilesType());

            JPanel projectInfoPanel = new JPanel();
            projectInfoPanel.setLayout(new BoxLayout(projectInfoPanel, BoxLayout.X_AXIS));

            JPanel leftPanel = new JPanel();
            leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
            leftPanel.add(Box.createRigidArea(new Dimension(100, 0)));
            leftPanel.add(projectNameLabelL);
            leftPanel.add(Box.createRigidArea(new Dimension(10, 5)));
            leftPanel.add(savedPathLabelL);
            leftPanel.add(Box.createRigidArea(new Dimension(10, 5)));
            leftPanel.add(filesTypesLabelL);

            JPanel rightPanel = new JPanel();
            rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
            rightPanel.add(projectNameLabelR);
            rightPanel.add(Box.createRigidArea(new Dimension(10, 5)));
            rightPanel.add(savedPathLabelR);
            rightPanel.add(Box.createRigidArea(new Dimension(10, 5)));
            rightPanel.add(filesTypesLabelR);

            projectInfoPanel.add(leftPanel);
            projectInfoPanel.add(rightPanel);

            projectInfoPanel.setBorder(BorderFactory.createTitledBorder("Project info"));
            projectManager.setBorder(BorderFactory.createTitledBorder("Project manager"));

            panel.add(projectInfoPanel);
            panel.add(projectManager);
        }

        JPanel projectInfo = new JPanel();
        this.setLayout(new BorderLayout());
        projectInfo.setBackground(Color.BLUE);

        this.add(panel);
        this.setSize(getWindowSize());
        this.setVisible(true);
    }

    public void runStartScreen(MainController mainController) {
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
        JCheckBox isDefaultSeriesNames = new JCheckBox();
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
                boolean defaultDataSeriesNames = isDefaultSeriesNames.isSelected();
                boolean defaultDimensionNames = isDefaultDimensionNames.isSelected();

                Project project = onClickCreateButton(projectName, savedPath, defaultFilesTypes, defaultDataSeriesNames, defaultDimensionNames);
                mainController.setProject(project);
                jFrame.dispose();
                setCreated(true);
                JOptionPane.showMessageDialog(null, "Project was successfully created!");
                run(mainController);
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
        box.add(isDefaultSeriesNames);
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
