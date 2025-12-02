package controller;

import model.Model;
import view.BaseView;

public class Controller {
    private Model model;
    private BaseView view;

    Controller(Model model, BaseView view) {
        this.model = model;
        this.view = view;
    }

    public void start() {
        // Implementation of start method
    }

    public void end() {
        // Implementation of end method
    }
}