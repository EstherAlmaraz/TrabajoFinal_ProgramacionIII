package model;

import java.io.Serializable;

public class Option implements Serializable{
    private String text;//Answer text
    private String rationale;//Why it is right/wrong
    private boolean correct; //Is this option correct?
    private static final long serialVersionUID = 1L;

    public Option(String text, String rationale, boolean correct) {
        this.text = text;
        this.rationale = rationale;
        this.correct = correct;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getRationale() {
        return rationale;
    }

    public void setRationale(String rationale) {
        this.rationale = rationale;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }
    
}
