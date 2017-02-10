package Model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

public class Project {

    private String projectName;

    private Data data;

    private Vector<Integer> unaccountedColumn;

    private String pathSavedFile;

    private String defaultOpenFilesType;

    private boolean defaultDataSeriesNames;

    private boolean defaultDimensionNames;

    public Project() {

    }

    public void setData(Data data) {
        this.data = data;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setUnaccountedColumn(Vector<Integer> unaccountedColumn) {
        this.unaccountedColumn = unaccountedColumn;
    }

    public void setPathSavedFile(String pathSavedFile) {
        this.pathSavedFile = pathSavedFile;
    }

    public void setDefaultOpenFilesType(String defaultOpenFilesType) {
        this.defaultOpenFilesType = defaultOpenFilesType;
    }

    public void setDefaultDataSeriesNames(boolean defaultDataSeriesNames) {
        this.defaultDataSeriesNames = defaultDataSeriesNames;
    }

    public void setDefaultDimensionNames(boolean defaultDimensionNames) {
        this.defaultDimensionNames = defaultDimensionNames;
    }

    public Data getData() {
        return data;
    }

    public String getProjectName() {
        return projectName;
    }

    public Vector<Integer> getUnaccountedColumn() {
        return unaccountedColumn;
    }

    public String getPathSavedFile() {
        return pathSavedFile;
    }

    public String getDefaultOpenFilesType() {
        return defaultOpenFilesType;
    }

    public boolean isDefaultDataSeriesNames() {
        return defaultDataSeriesNames;
    }

    public boolean isDefaultDimensionNames() {
        return defaultDimensionNames;
    }

    public void loadProject() {
        // TODO: code
    }

    public void newProject(String projectName, String pathSavedFile, String defaultOpenFilesType,
                           boolean defaultDataSeriesNames, boolean defaultDimensionNames) {
        this.projectName = projectName;
        this.pathSavedFile = pathSavedFile;
        this.defaultOpenFilesType = defaultOpenFilesType;
        this.defaultDataSeriesNames = defaultDataSeriesNames;
        this.defaultDimensionNames = defaultDimensionNames;
    }

    public void saveProject() throws IOException {
        StringBuilder sb = new StringBuilder();

        File file = new File(pathSavedFile);

        if (!file.exists()) {
            file.createNewFile();
        }

        FileWriter fileWriter = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        // project properties
        sb.append(projectName).append(",").append(defaultOpenFilesType).append(",").append(defaultDataSeriesNames)
                .append(",").append(defaultDimensionNames);
        bufferedWriter.write(sb.toString());
        bufferedWriter.newLine();

        // clearing StringBuilder
        sb.setLength(0);

        // unaccounted columns
        for (Integer column : unaccountedColumn) {
            sb.append(column).append(",");
        }
        if (unaccountedColumn.isEmpty()) {
            sb.append("-1"); // no unaccounted columns
        }
        bufferedWriter.write(sb.toString());
        bufferedWriter.newLine();

        bufferedWriter.close();
    }
}