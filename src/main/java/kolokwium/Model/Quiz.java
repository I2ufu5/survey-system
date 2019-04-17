package kolokwium.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long quizId;

//    @OneToMany(fetch = FetchType.EAGER)
//    @JoinColumn(name = "questionId")
//    @JsonIgnore
//    private Set<Question> question;

//    public Quiz(Set<Question> question) {
//        this.question = question;
//    }


    public Quiz() {
    }
}
