package view;

import controller.Controller;

public abstract class BaseView {
    private Controller controller;

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void init() {
        // Implementation of init method
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void showErrorMessage(String error) {
        System.err.println(error);
    }

    public void end(){
        // Implementation of end method
    }
}
