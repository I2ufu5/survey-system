package kolokwium.Services.modelServices;

import kolokwium.Repositories.AnswersDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnswersService {

    @Autowired
    AnswersDAO answersDAO;


}
