package model;

import java.util.HashSet;
import java.util.List;
import java.util.UUID;

public class Question {
    private UUID id; //Question identifier
    private String author; //Human or AIModel
    private HashSet<String> topics; //One or more topics
    private String statement; //Question statement
    private List<Option> options; //4 options

    public Question(String statement, HashSet<String> topics, List<Option> options, String author, UUID id) {
        this.statement = statement;
        this.topics = topics;
        this.options = options;
        this.author = author;
        this.id = id;
    }
}
