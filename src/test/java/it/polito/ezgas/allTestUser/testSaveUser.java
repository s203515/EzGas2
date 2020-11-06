package it.polito.ezgas.allTestUser;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.*;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.polito.ezgas.entity.User;
import it.polito.ezgas.dto.UserDto;
import it.polito.ezgas.repository.UserRepository;
import it.polito.ezgas.service.impl.UserServiceimpl;

class TestSaveUser {
	UserRepository userRepository;
	UserServiceimpl us;
	User u1 ;
	
	@BeforeEach
	void setUp() throws Exception {
		userRepository = mock(UserRepository.class);
		us = new UserServiceimpl();
		u1 = new User("davide","battaglia","mail@gmail.com",3);
		us.addUserRepository(userRepository);
		when(userRepository.save(any(User.class))).thenReturn(u1);		
	}

	@Test
	void testSaveUser1() { //id = NULL E I CHECK PASSSATI
		UserDto dto1 = new UserDto(null,"davide","battaglia","mail@gmail.com",3);
		UserDto dto2 = us.saveUser(dto1);
//		assertNull(dto2);
		assertTrue(dto1.getUserName().equals(dto2.getUserName()));
		assertTrue(dto1.getPassword().equals(dto2.getPassword()));
		assertTrue(dto1.getEmail().equals(dto2.getEmail()));
		assertEquals(dto1.getReputation(),dto2.getReputation());
	}
	
	@Test
	void testSaveUser2() {
		when(userRepository.findOne(anyInt())).thenReturn(u1);
		
		UserDto dto1 = new UserDto(5,"davide","battaglia","mail@gmail.com",3);
		UserDto dto2 = us.saveUser(dto1);
		
		assertTrue(dto1.getUserName().equals(dto2.getUserName()));
		assertTrue(dto1.getPassword().equals(dto2.getPassword()));
		assertTrue(dto1.getEmail().equals(dto2.getEmail()));
		assertEquals(dto1.getReputation(),dto2.getReputation());
	}
	
	@Test
	void testSaveUser3() {
		when(userRepository.save(any(User.class))).thenReturn(null);		
		UserDto dto1 = new UserDto(5,"davide","battaglia","mail@gmail.com",3);
		UserDto dto2 = us.saveUser(dto1);
		
		assertNull(dto2);
	}
	
	// A TURNO DEVO ANNULLARE I VARI CAMPI

	@Test
	void testSaveUser4() { //id = NULL E I CHECK NON PASSSATI
		when(userRepository.findOne(anyInt())).thenReturn(u1);
		
		UserDto dto1 = new UserDto(1,"davide","CICCIO","mail2@gmail.com",3);
		UserDto dto2 = us.saveUser(dto1);
//		assertNull(dto2);
		assertTrue(u1.getUserName().equals(dto2.getUserName()));
		assertTrue(u1.getPassword().equals(dto2.getPassword()));
		assertTrue(u1.getEmail().equals(dto2.getEmail()));
		assertEquals(u1.getReputation(),dto2.getReputation());
		
	}
}


	
