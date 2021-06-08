package hu.webuni.logistic.security;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LogisticUserDetailsService implements UserDetailsService{

	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		LogisticUser logisticUser = userRepository	.findById(username)
								 					.orElseThrow(()->new UsernameNotFoundException(username));
		
		
		return new User(username,logisticUser.getPassword(),logisticUser.getRoles()
																		.stream()
																		.map(SimpleGrantedAuthority::new)
																		.collect(Collectors.toList()));
	}
	

}
