package kolokwium.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Answers {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idAnswers;

    private String text;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idQuestions",nullable = false)
    @JsonIgnore
    private Questions questions;


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

    public Questions getQuestions() {
        return questions;
    }

    public void setQuestions(Questions questions) {
        this.questions = questions;
    }
}
