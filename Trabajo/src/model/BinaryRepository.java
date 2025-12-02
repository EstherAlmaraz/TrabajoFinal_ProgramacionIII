package model;

import java.util.ArrayList;

public class BinaryRepository implements IRepository {
    private ArrayList<Question> questions;

    public BinaryRepository() {
        this.questions = new ArrayList<>();
    }

    @Override
    public Question addQuestion(Question q) throws IRepositoryException {
        questions.add(q);
        return q;
    }

    @Override
    public void removeQuestion(Question q) throws IRepositoryException {
        questions.remove(q);
    }

    @Override
    public Question modifyQuestion(Question q) throws IRepositoryException {
        int index = questions.indexOf(q);
        if (index != -1) {
            questions.set(index, q);
            return q;
        }
        throw new IRepositoryException("Question not found");
    }

    @Override
    public List<Question> getAllQuestions() throws IRepositoryException {
        return new ArrayList<>(questions);
    }
}
