package it.polito.ezgas.allTestUser;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.*;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;

import it.polito.ezgas.dto.IdPw;
import it.polito.ezgas.dto.LoginDto;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exception.InvalidLoginDataException;
import it.polito.ezgas.entity.User;
import it.polito.ezgas.dto.UserDto;
import it.polito.ezgas.repository.UserRepository;
import it.polito.ezgas.service.impl.UserServiceimpl;
class TestLogin {
	UserRepository userRepository;
	UserServiceimpl us;
	@BeforeEach
	void setUp() throws Exception {
		userRepository = mock(UserRepository.class);
		us = new UserServiceimpl();	
		us.addUserRepository(userRepository);
		
	}

	@Test
	void testLogin1() throws InvalidLoginDataException{
		User u1 = new User("davide","battaglia","mail@gmail.com",3);
		List<User> users = new ArrayList<User>();
		users.add(u1);
		
		when(userRepository.findAll()).thenReturn(users);
		
		IdPw credentials = new IdPw("mail@gmail.com","battaglia");		
		LoginDto log = us.login(credentials);
		
		assertTrue(u1.getUserName().equals(log.getUserName()));
		assertTrue(u1.getEmail().equals(log.getEmail()));
		assertEquals(u1.getReputation(),log.getReputation());
	}
	
	@Test
	public void testLogin2() throws InvalidLoginDataException{
		User u1 = new User("davide","battaglia","mail@gmail.com",3);
		List<User> users = new ArrayList<User>();
		users.add(u1);
		when(userRepository.findAll()).thenReturn(users);
		
		assertThrows(InvalidLoginDataException.class, () -> { us.login(new IdPw("nome","pass"));			
		});	
	}
	
}
