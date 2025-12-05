package model;

import java.io.IOException;
import java.util.List;

public interface IRepository {
    public Question addQuestion(Question q) throws IRepositoryException;
    public void removeQuestion(Question q) throws IRepositoryException;
    public Question modifyQuestion(Question q) throws IRepositoryException;
    public List<Question> getAllQuestions() throws IRepositoryException;
    public void save() throws IRepositoryException;
    public void load() throws IRepositoryException;
    
}
