package controller;

import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import model.Model;
import model.Option;
import model.Question;
import model.IRepositoryException;
import model.QuestionBackupIOException;
import view.BaseView;

public class Controller {
    private Model model;
    private BaseView view;


    public Controller(Model model, BaseView view) {
        this.model = model;
        this.view = view;
    }

    public void start() {
        view.init();
    }

    public void end() {
        // Implementation of end method
    }

    public void addQuestion(String statement, String topics, List<Option> options, String author, UUID id) throws IRepositoryException {
        Question question = new Question(statement, new HashSet<>(), options, author, id);
        question.setTopicsWithFormat(topics);
        model.addQuestion(question);
    }
    public void addOption(Option o, List<Option> options) {
        model.addOption(o, options);
    }

    public List<Question> getAllQuestions() throws IRepositoryException {
        return model.getAllQuestions();
    }

    public List<Question> getQuestionsByTopic(String topic) throws IRepositoryException {
        return model.getQuestionsByTopic(topic);
    }
    public List<Question> getAllQuestionsOrderedByDate() throws IRepositoryException {
        return model.getAllQuestionsOrderedByDate();
    }
    public void modifyQuestion(int atributo, Question question, String nuevoValor) throws IRepositoryException {
        model.modifyQuestion(atributo, question, nuevoValor);
    }
    public void modifyOptions(Question question, List<Option> newOptions) throws IRepositoryException {
        model.modifyOptions(question, newOptions);
    }
    public void deleteQuestion(Question question) throws IRepositoryException {
        model.deleteQuestion(question);
    }
    public void importQuestionsFromJSON() throws QuestionBackupIOException, IRepositoryException {
        model.importQuestionsFromJSON();
    }
    public void exportQuestionsToJSON() throws QuestionBackupIOException, IRepositoryException {
        model.exportQuestionsToJSON();
    }
}