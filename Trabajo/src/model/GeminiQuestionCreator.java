package model;

public class GeminiQuestionCreator implements QuestionCreator {
    private String questionCreatorDescription;
    String API_KEY;

    public GeminiQuestionCreator(String API_KEY) {
        this.API_KEY = API_KEY;
    }

    @Override
    public Question createQuestion(String topic) throws GeminiQuestionCreatorException {
        // Implementation code here
        return new Question();
    }

    @Override
    public String getQuestionCreatorDescription() {
        return questionCreatorDescription;
    }
}
