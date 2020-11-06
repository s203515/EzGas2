package it.polito.ezgas.converter;

import it.polito.ezgas.dto.UserDto;
import it.polito.ezgas.entity.User;

/**
 * UserConverter
 */
public class UserConverter {

	/**
	 * Convert a User in UserDto
	 * 
	 * @param user
	 * @return the UserDto of the User
	 */
	public static UserDto toUserDto(User user) {
		UserDto userDto = new UserDto(
				user.getUserId(), 
				user.getUserName(), 
				user.getPassword(), 
				user.getEmail(),
				user.getReputation(), 
				user.getAdmin()
		);
		return userDto;
	}
	
	/**
	 * Convert a UserDto in User
	 * 
	 * @param userDto
	 * @return the user of the UserDto
	 */
	public static User toUser(UserDto userDto) {
		User user = new User(
				userDto.getUserName(),
				userDto.getPassword(),
				userDto.getEmail(),
				new Integer(0)
	    );
		user.setAdmin(userDto.getAdmin());	
		return user;
	}

}
