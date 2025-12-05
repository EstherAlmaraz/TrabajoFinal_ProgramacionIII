package model;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


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
    @Override
    public void save() throws IRepositoryException{ //Puede que tenga que quitar IOException
        Path p=Paths.get(System.getProperty("user.home") + File.separator+"questions_backup.json");
        try(
        FileOutputStream fos=new FileOutputStream(p.toFile());
        BufferedOutputStream bos=new BufferedOutputStream(fos);
        ObjectOutputStream oos=new ObjectOutputStream(bos);
        ){
        oos.writeObject(questions);} 
        catch (IOException e) {
            throw new IRepositoryException("Error saving questions to binary file", e);
        }

    }

    @Override
    public void load() throws IRepositoryException {
        Path p=Paths.get(System.getProperty("user.home") + File.separator+"questions_backup.json");
        try(
        FileInputStream fis=new FileInputStream(p.toFile());
        BufferedInputStream bis=new BufferedInputStream(fis);
        ObjectInputStream ois=new ObjectInputStream(bis);
        ){
        questions = (ArrayList<Question>) ois.readObject();
        } 
        catch (ClassNotFoundException e) {
            throw new IRepositoryException("Error missing required class (Question or Option)", e);
        }
        catch (IOException e) {
            throw new IRepositoryException("Error loading questions from binary file (permissions, disk problem, or file damage). Check if the file exists and is accessible.", e);
        }
    }
}
