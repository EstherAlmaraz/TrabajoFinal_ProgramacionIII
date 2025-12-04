package controller;

import java.time.LocalDate;
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

    public void addQuestion(String statement, String topics, List<Option> options, String author, UUID id, LocalDate creationDate) {
        Question question = new Question(statement, new HashSet<>(), options, author, id, creationDate);
        question.setTopicsWithFormat(topics);
        model.addQuestion(question);
    }

    public List<Question> getAllQuestions() {
        return model.getAllQuestions();
    }

    public List<Question> getQuestionsByTopic(String topic) {
        return model.getQuestionsByTopic(topic);
    }
    public List<Question> getAllQuestionsOrderedByDate() {
        return model.getAllQuestionsOrderedByDate();
    }
    public void modifyQuestion(int atributo, Question question, String nuevoValor) {
        model.modifyQuestion(atributo, question, nuevoValor);
    }
    public void modifyOptions(Question question, List<Option> newOptions) {
        model.modifyOptions(question, newOptions);
    }
    public void deleteQuestion(Question question) {
        model.deleteQuestion(question);
    }
    public void importQuestionsFromJSON() {
        model.importQuestionsFromJSON();
    }
    public void exportQuestionsToJSON() {
        model.exportQuestionsToJSON();
    }
}