package model;

import java.util.ArrayList;

public class Model{
    private IRepository repository;
    private QuestionBackupIO backupHandler;
    private ArrayList<QuestionCreator> questionCreators;

    public Model(IRepository repository, QuestionBackupIO backupHandler, ArrayList<QuestionCreator> questionCreators) {
        this.repository = repository;
        this.backupHandler = backupHandler;
        this.questionCreators = questionCreators;
    }

    public void addQuestion(Question q){
        repository.addQuestion(q);
    }
}
