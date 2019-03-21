package kolokwium.Repo;

import kolokwium.Model.Response;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponseDAO extends JpaRepository<Response,Long> {

}
