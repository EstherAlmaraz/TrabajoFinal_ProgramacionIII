package model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

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
        Question addedQuestion = repository.addQuestion(q);
    }

    public List<Question> getAllQuestionsOrderedByDate(){
        List<Question> questionsOrderedByDate = repository.getAllQuestions();
        questionsOrderedByDate.sort(Comparator.comparing(Question::getCreationDate)); //Se ordenan por fecha de creación
        return questionsOrderedByDate;
    }

    public List<Question> getAllQuestions(){
        return repository.getAllQuestions();
    }

    public List<Question> getQuestionsByTopic(String topic){
        List<Question> byTopicQuestions = new ArrayList<>();
        for(Question q : repository.getAllQuestions()){
            if(q.getTopics().contains(topic)){
                byTopicQuestions.add(q);
            }
        }
        return byTopicQuestions;
    }

    public void modifyQuestion(int atributo, Question question, String nuevoValor){
        
        switch(atributo){
            case 1: //Autor
                question.setAuthor(nuevoValor);
                break;
            case 2://temas
                question.setTopicsWithFormat(nuevoValor);
                break;
            case 3://enunciado
                question.setStatement(nuevoValor);
                break;
            default:
                throw new IllegalArgumentException("Atributo no válido");
                
        }
        repository.modifyQuestion(question);
    }

    public void modifyOptions(Question question, List<Option> newOptions){
        question.setOptions(newOptions);
        repository.modifyQuestion(question);
    }
    public void deleteQuestion(Question question){
        repository.removeQuestion(question);
    }
    public void importQuestionsFromJSON(){
        List<Question> importedQuestions = backupHandler.importFromJSON();
        HashSet<UUID> existingQuestionIds = new HashSet<>();
        for(Question q : repository.getAllQuestions()){
            existingQuestionIds.add(q.getId());
        }
        for(Question q : importedQuestions){
            if(!existingQuestionIds.contains(q.getId())){
                repository.addQuestion(q);
            }
        }
    }
    public void exportQuestionsToJSON(){
        List<Question> exportedQuestions = repository.getAllQuestions();
        backupHandler.exportToJSON(exportedQuestions);
    }
}
