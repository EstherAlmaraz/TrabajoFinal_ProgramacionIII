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
    public Model(){
        this.repository = new BinaryRepository();
        this.backupHandler = new JSONQuestionBackupIO();
        this.questionCreators = new ArrayList<>();
    }

    public void addQuestion(Question q) throws IRepositoryException {
        repository.addQuestion(q);
    }
    public void addOption(Option o, List<Option> options) {
        options.add(o);
    }

    public List<Question> getAllQuestionsOrderedByDate() throws IRepositoryException {
        List<Question> questionsOrderedByDate = repository.getAllQuestions();
        questionsOrderedByDate.sort(Comparator.comparing(Question::getCreationDate)); //Se ordenan por fecha de creación
        return questionsOrderedByDate;
    }

    public List<Question> getAllQuestions() throws IRepositoryException {
        return repository.getAllQuestions();
    }

    public List<Question> getQuestionsByTopic(String topic) throws IRepositoryException {
        List<Question> byTopicQuestions = new ArrayList<>();
        for(Question q : repository.getAllQuestions()){
            if(q.getTopics().contains(topic)){
                byTopicQuestions.add(q);
            }
        }
        return byTopicQuestions;
    }

    public void modifyQuestion(int atributo, Question question, String nuevoValor) throws IRepositoryException {
        
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

    public void modifyOptions(Question question, List<Option> newOptions) throws IRepositoryException {
        question.setOptions(newOptions);
        repository.modifyQuestion(question);
    }
    public void deleteQuestion(Question question) throws IRepositoryException {
        repository.removeQuestion(question);
    }
    public void importQuestionsFromJSON() throws QuestionBackupIOException, IRepositoryException {
        List<Question> importedQuestions = backupHandler.importQuestions();
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
    public void exportQuestionsToJSON() throws QuestionBackupIOException, IRepositoryException {
        List<Question> exportedQuestions = repository.getAllQuestions();
        backupHandler.exportQuestions(exportedQuestions);
    }
    public void save() throws IRepositoryException {
        repository.save();
    }

    public void load() throws IRepositoryException {
        repository.load();
    }

    public HashSet<String> getAvailableTopics() {
        HashSet<String> topics = new HashSet<>();
        try {
            for (Question q : repository.getAllQuestions()) {
                topics.addAll(q.getTopics());
            }
        } catch (IRepositoryException e) {
            System.err.println("Error retrieving topics: " + e.getMessage());
        }
        return topics;
    }
    public int getMaxQuestions(String topic) {
        int maxQuestions = 0;
        try {
            for (Question q : repository.getAllQuestions()) {
                if (topic.equals("TODOS") || q.getTopics().contains(topic)) {
                    maxQuestions++;
                }
            }
        } catch (IRepositoryException e) {
            System.err.println("Error counting questions: " + e.getMessage());
        }
        return maxQuestions;
    }
}
