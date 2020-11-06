package it.polito.ezgas.allTestUser;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exception.InvalidUserException;
import it.polito.ezgas.entity.User;
import it.polito.ezgas.repository.UserRepository;
import it.polito.ezgas.service.impl.UserServiceimpl;

class TestDecreaseUserReputation {
	UserRepository userRepository;
	UserServiceimpl us;
	
	@BeforeEach
	void setUp() throws Exception {
		userRepository = mock(UserRepository.class);
		us = new UserServiceimpl();	
		us.addUserRepository(userRepository);
	}

	@Test
	void testDecreaseUserReputation1() throws InvalidUserException {
		User u1 = new User("davide","battaglia","mail@gmail.com",3);
		Integer ex = u1.getReputation();
		when(userRepository.findOne(anyInt())).thenReturn(u1);

		Integer reput = us.decreaseUserReputation(5);
		assertEquals(reput, ex-1);
		
	}
	
	@Test
	public void testDecreaseUserReputation2() throws InvalidUserException {
		assertThrows(InvalidUserException.class, () -> { us.decreaseUserReputation(null);			
		});	
	}
	
	@Test
	public void testDecreaseUserReputation3() throws InvalidUserException {
		assertThrows(InvalidUserException.class, () -> { us.decreaseUserReputation(-1);			
		});	
	}
	
	
	@Test
	void testDecreaseUserReputation4() throws InvalidUserException {
		User u1 = new User("davide","battaglia","mail@gmail.com",3);
		Integer ex = u1.getReputation();
		when(userRepository.findOne(anyInt())).thenReturn(null);
		
		Integer reput = us.decreaseUserReputation(5);
		assertNull(reput);
		
	}
}
