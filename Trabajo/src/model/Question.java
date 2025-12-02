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
}
