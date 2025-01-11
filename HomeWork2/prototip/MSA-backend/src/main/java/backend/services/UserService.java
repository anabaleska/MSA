package backend.services;


import backend.model.enumerations.Role;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    void register(String username, String password, String repeatPassword, String email , Role role);
}
