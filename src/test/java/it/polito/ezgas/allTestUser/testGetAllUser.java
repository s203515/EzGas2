package it.polito.ezgas.allTestUser;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.*;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.polito.ezgas.entity.User;
import it.polito.ezgas.dto.UserDto;
import it.polito.ezgas.repository.UserRepository;
import it.polito.ezgas.service.impl.UserServiceimpl;
class TestGetAllUser {
	UserRepository userRepository;
	UserServiceimpl us;
	@BeforeEach
	void setUp() throws Exception {
		userRepository = mock(UserRepository.class);
		us = new UserServiceimpl();	
		us.addUserRepository(userRepository);
		
	}

	@Test
	void testGetAllUser1() {
		User u1 = new User("davide","battaglia","mail@gmail.com",3);
		List<User> users = new ArrayList<User>();
		users.add(u1);
		when(userRepository.findAll()).thenReturn(users);
		
		List<UserDto> rets = us.getAllUsers();
		UserDto u2 = rets.get(0);
		
		assertTrue(u1.getUserName().equals(u2.getUserName()));
		assertTrue(u1.getPassword().equals(u2.getPassword()));
		assertTrue(u1.getEmail().equals(u2.getEmail()));
		assertEquals(u1.getReputation(),u2.getReputation());
	}

}
