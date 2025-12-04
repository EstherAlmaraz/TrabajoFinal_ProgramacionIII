package view;

import controller.Controller;

public abstract class BaseView {
    protected Controller controller; //VER SI ES MEJOR PRIVATE

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void init() {
        showMenu();
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
