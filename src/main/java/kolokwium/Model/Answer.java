package kolokwium.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idAnswers;

    private String text;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idQuestions",nullable = false)
    @JsonIgnore
    private Question question;


    public Integer getIdAnswers() {
        return idAnswers;
    }

    public void setIdAnswers(Integer idAnswers) {
        this.idAnswers = idAnswers;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Answer(String text, Question question) {
        this.text = text;
        this.question = question;
    }
}
