package kolokwium.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;

    private String text;

//    @OneToMany(fetch = FetchType.EAGER)
//    @JoinColumn(name = "answerId")
//    @JsonIgnore
//    private List<Answer> answers;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quizId")
    @JsonIgnore
    private Quiz quiz;

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

//    public List<Answer> getAnswers() {
//        return answers;
//    }

    public Question(String text, Quiz quiz) {
        this.text = text;
        this.quiz = quiz;
    }

    public Question(String text) {
        this.text = text;
    }

    public Question() {
    }
}
