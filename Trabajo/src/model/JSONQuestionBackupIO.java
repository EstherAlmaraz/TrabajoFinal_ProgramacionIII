package model;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;

public class JSONQuestionBackupIO implements QuestionBackupIO {
    @Override
    public void exportQuestions(List<Question> questions) throws QuestionBackupIOException {
        Gson gson = new Gson();
        String ruta=System.getProperty("user.home") + File.separator+"questions_backup.json";
        try {
            String json = gson.toJson(questions);
            Files.write(Paths.get(ruta), json.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new QuestionBackupIOException("Error al exportar preguntas a JSON", e);
        }
    }

    @Override
    public List<Question> importQuestions() throws QuestionBackupIOException {
        Gson gson = new Gson();
        String ruta=System.getProperty("user.home") + File.separator+"questions_backup.json";
        try {
            String content = Files.readString(Paths.get(ruta), StandardCharsets.UTF_8);
            Question[] questions = gson.fromJson(content, Question[].class);
            return Arrays.asList(questions);
        } catch (IOException e) {
            throw new QuestionBackupIOException("Error al importar preguntas desde JSON", e);
        }
    }

    @Override
    public String getBackupIODescription() throws QuestionBackupIOException {
        // Implementation code here
        return null;
    }
}
