package model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import com.google.gson.annotations.Expose;

public class Question {
    @Expose
    private UUID id; //Question identifier
    @Expose
    private String author; //Human or AIModel
    @Expose
    private HashSet<String> topics; //One or more topics
    @Expose
    private String statement; //Question statement
    @Expose
    private List<Option> options; //4 options
    //Aquí no para evitar errores 
    private final LocalDate creationDate=LocalDate.now(); //Date of creation

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

    public LocalDate getCreationDate() {
        return creationDate;
    }

    
}
