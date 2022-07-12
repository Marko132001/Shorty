package hr.assecosee.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCrypt;

import hr.assecosee.shorty.User;
import hr.assecosee.shorty.UserRepository;

@ExtendWith(MockitoExtension.class)
class LoginServiceTest {

	@Mock
	private UserRepository userRepository;
	private LoginService loginService;
	
	
	@BeforeEach
	void setUp() {
		
		loginService = new LoginService(userRepository);
	}
	
	@Test
	void testAuthenticate() {
		
		User user = new User("Marko", "tjiwrfmnlo");
		
		String originalPassword = user.getPassword();
		
		user.setPassword(BCrypt.hashpw(originalPassword, BCrypt.gensalt(10)));
		
		when(userRepository.findById(user.getUserName())).thenReturn(Optional.of(user));
		
		user.setPassword(originalPassword);
		
		boolean retval = loginService.authenticate(user);
		
		assertTrue(retval);
		
	}

}
