package Model;

import java.util.Vector;

public class Project {

    private Data data;
    private String defaultOpenFilesType;
    private String pathSavedFile;
    private String projectName;
    private Vector<Integer> unaccountedColumn;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getDefaultOpenFilesType() {
        return defaultOpenFilesType;
    }

    public void setDefaultOpenFilesType(String defaultOpenFilesType) {
        this.defaultOpenFilesType = defaultOpenFilesType;
    }

    public String getPathSavedFile() {
        return pathSavedFile;
    }

    public void setPathSavedFile(String pathSavedFile) {
        this.pathSavedFile = pathSavedFile;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Vector<Integer> getUnaccountedColumn() {
        return unaccountedColumn;
    }

    public void setUnaccountedColumn(Vector<Integer> unaccountedColumn) {
        this.unaccountedColumn = unaccountedColumn;
    }
}
