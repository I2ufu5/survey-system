package kolokwium.Repo;

import kolokwium.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@Repository
@CrossOrigin
public interface UsersDAO extends JpaRepository<User,Long> {
    User findUserByAlbumNumber(Integer albumNumber);
    boolean existsByAlbumNumber(Integer albumNumber);
    boolean existsByName(String name);
}
