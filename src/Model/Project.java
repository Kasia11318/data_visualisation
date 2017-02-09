package Model;

import java.util.Vector;

public class Project {

    private String projectName;

    private Data data;

    private Vector<Integer> unaccountedColumn;

    public void setData(Data data) {
        this.data = data;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setUnaccountedColumn(Vector<Integer> unaccountedColumn) {
        this.unaccountedColumn = unaccountedColumn;
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
}