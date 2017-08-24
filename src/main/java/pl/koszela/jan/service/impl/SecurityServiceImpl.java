package pl.koszela.jan.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import pl.koszela.jan.service.SecurityService;

/**
 * Created on 24.08.2017.
 *
 * @author Jan Koszela
 */
public class SecurityServiceImpl implements SecurityService {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private UserDetailsService userDetailsService;

  private static final Logger LOGGER = LoggerFactory.getLogger(SecurityServiceImpl.class);

  @Override
  public String findLoggedInUsername() {
    Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();

    if (userDetails instanceof UserDetails) {
      return ((UserDetails) userDetails).getUsername();
    }

    return null;
  }

  @Override
  public void autologin(String username, String password) {
    UserDetails userDetails = userDetailsService.loadUserByUsername(username);
    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
        userDetails, password);

    authenticationManager.authenticate(authenticationToken);

    if (authenticationToken.isAuthenticated()) {
      SecurityContextHolder.getContext().setAuthentication(authenticationToken);
      LOGGER.debug(String.format("Auto login %s successfully!", username));
    }

  }
}
