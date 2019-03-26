package kolokwium;

import kolokwium.Model.*;
import kolokwium.Repo.*;
import kolokwium.Services.ResponseService;
import kolokwium.Services.UserDetailsService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner DatabaseInit(
            UserDetailsService userDetailsService,
            QuestionsDAO questionsDAO,
            AnswersDAO answersDAO,
            ResponseDAO responseDAO,
            ResponseService responseService){

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        return (args) -> {
//
            userDetailsService.createUser(145203,"Kowalski", "haslo");
            userDetailsService.setAdmin(145203,true);
            userDetailsService.createUser(132940, "Malinowski","ABCD");
            userDetailsService.createUser(153421,"Duda","qwerty");

            questionsDAO.save(new Question("Pytanie1"));
            questionsDAO.save(new Question("Pytanie2"));

            answersDAO.save(new Answer("Odpowiedz1", questionsDAO.findQuestionsByQuestionId(1L),true));
            answersDAO.save(new Answer("Odpowiedz2", questionsDAO.findQuestionsByQuestionId(1L),false));
            answersDAO.save(new Answer("Odpowiedz3", questionsDAO.findQuestionsByQuestionId(1L),false));

            answersDAO.save(new Answer("Odpowiedz1", questionsDAO.findQuestionsByQuestionId(2L),false));
            answersDAO.save(new Answer("Odpowiedz2", questionsDAO.findQuestionsByQuestionId(2L),false));
            answersDAO.save(new Answer("Odpowiedz3", questionsDAO.findQuestionsByQuestionId(2L),true));

            responseDAO.save(new Response(
                    userDetailsService.findByAlbumNumber(145203),
                    questionsDAO.findQuestionsByQuestionId(1L),
                    answersDAO.findAnswersByQuestion_QuestionId(1L).get(0)
            ));

            responseDAO.save(new Response(
                    userDetailsService.findByAlbumNumber(145203),
                    questionsDAO.findQuestionsByQuestionId(2L),
                    answersDAO.findAnswersByQuestion_QuestionId(2L).get(1)
            ));

            responseService.calculateResult(145203);

        };
    }

}
