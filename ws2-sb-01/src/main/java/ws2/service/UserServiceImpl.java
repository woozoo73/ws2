package ws2.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ws2.model.User;
import ws2.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.debug("loadUserByUsername");
	
		User user = userRepository.findOne(username);

		if (user == null) {
			throw new UsernameNotFoundException("Not found user: username='" + username + "'");
		}

		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole());
		authorities.add(authority);

		return new org.springframework.security.core.userdetails.User(user.getId(), user.getPassword(), authorities);
	}

	@Override
	public void create(User user) {
		userRepository.save(user);
	}

}
