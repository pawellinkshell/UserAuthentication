package pl.koszela.jan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.koszela.jan.model.Role;

/**
 * Created on 24.08.2017.
 *
 * @author Jan Koszela
 */
public interface RoleRepository extends JpaRepository<Role, Long> {

}