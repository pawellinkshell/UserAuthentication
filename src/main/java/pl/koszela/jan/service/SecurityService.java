package pl.koszela.jan.service;

/**
 * Created on 24.08.2017.
 *
 * @author Jan Koszela
 */
public interface SecurityService {

  String findLoggedInUsername();

  void autologin(String username, String password);

}
