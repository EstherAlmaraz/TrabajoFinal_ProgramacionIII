package model;

public interface QuestionCreator {
    
    public Question createQuestion(String topic) throws GeminiQuestionCreatorException;
    public String getQuestionCreatorDescription();

}
