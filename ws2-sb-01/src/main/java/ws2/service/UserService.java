package ws2.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import ws2.model.User;

public interface UserService extends UserDetailsService {

	void create(User user);

}
