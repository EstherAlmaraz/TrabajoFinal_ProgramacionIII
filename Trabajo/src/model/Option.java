package model;

public class Option {
    private String text;//Answer text
    private String rationale;//Why it is right/wrong
    private boolean correct; //Is this option correct?

    public Option(String text, String rationale, boolean correct) {
        this.text = text;
        this.rationale = rationale;
        this.correct = correct;
    }
}
