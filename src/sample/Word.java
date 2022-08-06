package sample;

public class Word {
    private String word_target;
    private String word_explain;

    public Word() {

    }

    public Word(String target, String explain) {
        word_target = target;
        word_explain = explain;
    }

    String getWord_target() {

        return this.word_target;
    }

    void setWord_target(String word_target) {

        this.word_target = word_target;
    }

    String getWord_explain() {

        return this.word_explain;
    }

    void setWord_explain(String word_explain) {

        this.word_explain = word_explain;
    }
}