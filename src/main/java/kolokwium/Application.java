package kolokwium;

import kolokwium.Model.*;
import kolokwium.Repo.*;
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
            UsersDAO usersDAO,
            QuestionsDAO questionsDAO,
            AnswersDAO answersDAO,
            RoleDAO roleDao,
            ResponseDAO responseDAO){

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        return (args) -> {
            roleDao.save(new Role("admin"));
            roleDao.save(new Role("user"));

            usersDAO.save(new User(145203,"Kowalski", passwordEncoder.encode("haslo"),roleDao.findRoleByName("admin")));
            usersDAO.save(new User(132940, "Malinowski","ABCD",roleDao.findRoleByName("user")));
            usersDAO.save(new User(153421,"Duda","qwerty", roleDao.findRoleByName("user")));

            questionsDAO.save(new Question("Pytanie1"));
            questionsDAO.save(new Question("Pytanie2"));

            answersDAO.save(new Answer("Odpowiedz1", questionsDAO.findQuestionsByQuestionId(1L),true));
            answersDAO.save(new Answer("Odpowiedz2", questionsDAO.findQuestionsByQuestionId(1L),false));
            answersDAO.save(new Answer("Odpowiedz3", questionsDAO.findQuestionsByQuestionId(1L),false));

            answersDAO.save(new Answer("Odpowiedz1", questionsDAO.findQuestionsByQuestionId(2L),false));
            answersDAO.save(new Answer("Odpowiedz2", questionsDAO.findQuestionsByQuestionId(2L),false));
            answersDAO.save(new Answer("Odpowiedz3", questionsDAO.findQuestionsByQuestionId(2L),true));

            responseDAO.save(new Response(
                    usersDAO.findUserByAlbumNumber(145203),
                    questionsDAO.findQuestionsByQuestionId(1L),
                    answersDAO.findAnswersByQuestion_QuestionId(1L).get(1)
            ));

            responseDAO.save(new Response(
                    usersDAO.findUserByAlbumNumber(145203),
                    questionsDAO.findQuestionsByQuestionId(2L),
                    answersDAO.findAnswersByQuestion_QuestionId(1L).get(2)
            ));

        };
    }

}
