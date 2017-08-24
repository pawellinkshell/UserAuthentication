package pl.koszela.jan.service.impl;

import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.koszela.jan.model.Role;
import pl.koszela.jan.model.User;
import pl.koszela.jan.repository.UserRepository;

/**
 * Created on 24.08.2017.
 *
 * @author Jan Koszela
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Override
  @Transactional(readOnly = true)
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByUsername(username);

    Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
    for (Role role : user.getRoles()) {
      grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
    }

    return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
  }
}
