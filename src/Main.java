import Controller.MainController;
import View.ApplicationWindow;

public class Main {

    public static void main(String[] args) {
        MainController mainController = new MainController();
        ApplicationWindow window = new ApplicationWindow("Data visualisation");
        window.run(mainController);
    }
}
