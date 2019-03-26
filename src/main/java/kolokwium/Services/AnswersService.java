package kolokwium.Services;

import kolokwium.Model.Answer;
import kolokwium.Repo.AnswersDAO;
import kolokwium.Repo.ResponseDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswersService {

    @Autowired
    AnswersDAO answersDAO;


}
