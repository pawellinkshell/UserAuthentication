package pl.koszela.jan.service;

import pl.koszela.jan.model.User;

/**
 * Created on 24.08.2017.
 *
 * @author Jan Koszela
 */
public interface UserService {

  void save(User user);

  User findByUsername(String username);

}
