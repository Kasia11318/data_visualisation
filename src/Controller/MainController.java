package Controller;

import Model.Project;

public class MainController {

    private DataController dataController;
    private Project project;

    public MainController() {
        dataController = new DataController();
    }

    public DataController getDataController() {
        return dataController;
    }

    public void setDataController(DataController dataController) {
        this.dataController = dataController;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
