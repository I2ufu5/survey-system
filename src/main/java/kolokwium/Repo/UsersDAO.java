package kolokwium.Repo;

import kolokwium.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersDAO extends JpaRepository<User,Long> {
    User findUserByAlbumNumber(Integer albumNumber);
}
