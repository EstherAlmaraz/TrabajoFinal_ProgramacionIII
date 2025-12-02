package model;

import java.util.List;

public class JSONQuestionBackupIO implements QuestionBackupIO {
    @Override
    public void exportQuestions(List<Question> questions) throws QuestionBackupIOException {
        // Implementation code here
    }

    @Override
    public List<Question> importQuestions() throws QuestionBackupIOException {
        // Implementation code here
        return null;
    }

    @Override
    public String getBackupIODescription() throws QuestionBackupIOException {
        // Implementation code here
        return null;
    }
}
