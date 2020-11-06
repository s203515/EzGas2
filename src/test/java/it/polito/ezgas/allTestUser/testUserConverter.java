package it.polito.ezgas.allTestUser;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import it.polito.ezgas.converter.UserConverter;
import it.polito.ezgas.dto.UserDto;
import it.polito.ezgas.entity.User;

class TestUserConverter {

	@Test
	void testUserConverterFromDto() {
		User u = new User("test","test","test@gmail.com",0);
		UserDto uDto = new UserDto(1,"test","test","test@gmail.com",0);
		User converted = UserConverter.toUser(uDto);
		
		assertEquals(u.getEmail(),converted.getEmail());
		assertEquals(u.getUserName(),converted.getUserName());
		assertEquals(u.getPassword(),converted.getPassword());
		assertEquals(u.getReputation(),converted.getReputation());
	}
	@Test
	void testUserConverterToDto() {
		User u = new User("test","test","test@gmail.com",0);
		UserDto uDto = new UserDto(1,"test","test","test@gmail.com",0);
		UserDto converted = UserConverter.toUserDto(u);
		
		assertEquals(uDto.getEmail(),converted.getEmail());
		assertEquals(uDto.getUserName(),converted.getUserName());
		assertEquals(uDto.getPassword(),converted.getPassword());
		assertEquals(uDto.getReputation(),converted.getReputation());
	}
}
