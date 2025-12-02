package model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

public class Question {
    private UUID id; //Question identifier
    private String author; //Human or AIModel
    private HashSet<String> topics; //One or more topics
    private String statement; //Question statement
    private List<Option> options; //4 options
    private LocalDate creationDate; //Date of creation

    public Question(String statement, HashSet<String> topics, List<Option> options, String author, UUID id, LocalDate creationDate) {
        this.statement = statement;
        this.topics = topics;
        this.options = options;
        this.author = author;
        this.id = id;
        this.creationDate = creationDate;
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

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }
    
}
