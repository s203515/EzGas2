package it.polito.ezgas.converter;

import it.polito.ezgas.dto.LoginDto;
import it.polito.ezgas.entity.User;

public class LoginConverter {
	
	public static LoginDto toLoginDto(User user) {
		LoginDto loginDto = new LoginDto(
					user.getUserId(),
					user.getUserName(),
					"token",
					user.getEmail(),
					user.getReputation()
		);
		loginDto.setAdmin(user.getAdmin());
		return loginDto;
	}

}