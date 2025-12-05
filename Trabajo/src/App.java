import controller.Controller;
import model.Model;
import view.BaseView;
import view.InteractiveView;

public class App {
    public static void main(String[] args) throws Exception {
        Model model = new Model();
        BaseView view = new InteractiveView();
        Controller controller = new Controller(model, view);
        view.setController(controller);
        controller.start();
    }
}
