package pl.koszela.jan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.koszela.jan.model.User;

/**
 * Created on 24.08.2017.
 *
 * @author Jan Koszela
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  User findByUsername(String username);

}
