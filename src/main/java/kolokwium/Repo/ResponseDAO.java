package kolokwium.Repo;

import kolokwium.Model.Response;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

@Repository
public interface ResponseDAO extends JpaRepository<Response,Long> {
    Set<Response> findAllByUser_AlbumNumber(Integer albumNumber);
}
