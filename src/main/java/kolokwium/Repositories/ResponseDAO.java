package kolokwium.Repositories;

import kolokwium.Model.Response;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ResponseDAO extends JpaRepository<Response,Long> {
    Set<Response> findAllByUser_Email(String albumNumber);
}
