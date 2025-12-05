package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;


public class Question implements Serializable{

    private UUID id; //Question identifier
    private String author; //Human or AIModel
    private HashSet<String> topics; //One or more topics
    private String statement; //Question statement
    private List<Option> options; //4 options
    private final String creationDate=LocalDate.now().toString(); //Date of creation
    private static final long serialVersionUID = 1L;
    
    public Question(){ }

    public Question(String statement, HashSet<String> topics, List<Option> options, String author, UUID id) {
        this.statement = statement;
        this.topics = topics;
        this.options = options;
        this.author = author;
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public HashSet<String> getTopics() {
        return topics;
    }

    public String getTopicsAsString() {
        return String.join(", ", topics);
    }

    public void setTopicsWithFormat(String topics) {
        HashSet<String> topicsSet = new HashSet<>();
        for (String topic : topics.split(",")) {
            topicsSet.add(topic.trim().toUpperCase());
        }
        this.topics = topicsSet;
    }


    public void setTopics(HashSet<String> topics) {
        this.topics = topics;
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public List<Option> getOptions() {
        return options;
    }
    

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    public String getCreationDate() {
        return creationDate.toString();
    }

    
}
