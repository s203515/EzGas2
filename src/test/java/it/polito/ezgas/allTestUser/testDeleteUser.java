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

import exception.InvalidUserException;
import it.polito.ezgas.entity.User;
import it.polito.ezgas.dto.UserDto;
import it.polito.ezgas.repository.UserRepository;
import it.polito.ezgas.service.impl.UserServiceimpl;
class TestDeleteUser {
	UserRepository userRepository;
	UserServiceimpl us;
	@BeforeEach
	void setUp() throws Exception {
		userRepository = mock(UserRepository.class);
		us = new UserServiceimpl();	
		us.addUserRepository(userRepository);
		
	}

	@Test
	void testDeleteUser1() throws InvalidUserException {
		User u1 = new User("davide","battaglia","mail@gmail.com",3);
		when(userRepository.findOne(anyInt())).thenReturn(u1);
		
		Boolean b = us.deleteUser(5);
		assertTrue(b);		
	}
	
	@Test
	public void testDeleteUser2() throws InvalidUserException {
		assertThrows(InvalidUserException.class, () -> { us.getUserById(null);});	
	}
	
	@Test
	public void testDeleteUser3() throws InvalidUserException {
		assertThrows(InvalidUserException.class, () -> { us.getUserById(-1);});	
	}
	
	@Test
	void testDeleteUser4() throws InvalidUserException {
		User u1 = new User("davide","battaglia","mail@gmail.com",3);
		when(userRepository.findOne(anyInt())).thenReturn(null);
		
		Boolean b = us.deleteUser(5);
		assertFalse(b);		
	}
}
