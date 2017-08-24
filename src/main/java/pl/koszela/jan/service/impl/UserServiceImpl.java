package pl.koszela.jan.service.impl;

import java.util.HashSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pl.koszela.jan.model.User;
import pl.koszela.jan.repository.RoleRepository;
import pl.koszela.jan.repository.UserRepository;
import pl.koszela.jan.service.UserService;

/**
 * Created on 24.08.2017.
 *
 * @author Jan Koszela
 */
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private RoleRepository roleRepository;

  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  @Override
  public void save(User user) {
    user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    user.setRoles(new HashSet<>(roleRepository.findAll()));
    userRepository.save(user);
  }

  @Override
  public User findByUsername(String username) {
    return userRepository.findByUsername(username);
  }
}
