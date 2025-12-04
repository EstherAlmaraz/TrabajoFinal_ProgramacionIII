package model;

import java.io.IOException;

public class QuestionBackupIOException extends IOException {
    public QuestionBackupIOException(String message) {
        super(message);
    }
    public QuestionBackupIOException(String message, Throwable cause) {
        super(message, cause);
    }
    public QuestionBackupIOException() { }
}
