package kolokwium;

import kolokwium.Model.Answer;
import kolokwium.Model.Question;
import kolokwium.Model.Role;
import kolokwium.Model.User;
import kolokwium.Repo.AnswersDAO;
import kolokwium.Repo.QuestionsDAO;
import kolokwium.Repo.RoleDAO;
import kolokwium.Repo.UsersDAO;
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
            RoleDAO roleDao){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        return (args) -> {
            roleDao.save(new Role("admin"));
            roleDao.save(new Role("user"));

            usersDAO.save(new User(145203,"Kowalski", passwordEncoder.encode("haslo"),roleDao.findRoleByName("admin")));
            usersDAO.save(new User(132940, "Malinowski","ABCD",roleDao.findRoleByName("user")));
            usersDAO.save(new User(153421,"Duda","qwerty", roleDao.findRoleByName("user")));

            questionsDAO.save(new Question("Pytanie1"));
            questionsDAO.save(new Question("Pytanie2"));

            if(questionsDAO.findQuestionsById(4L)!= null)
            answersDAO.save(new Answer("Odpowiedz1", questionsDAO.findQuestionsById(4L)));
        };
    }

}
