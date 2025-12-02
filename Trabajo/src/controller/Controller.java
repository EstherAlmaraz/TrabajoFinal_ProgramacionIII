package controller;

import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import model.Model;
import model.Option;
import model.Question;
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

    public void addQuestion(String statement, String topics, List<Option> options, String author, UUID id) {
       HashSet<String> topicsSet = new HashSet<>();
       for (String topic : topics.split(",")) {
           topicsSet.add(topic.trim());
       }
       Question question = new Question(statement, topicsSet, options, author, id);
       model.addQuestion(question);
    }
}