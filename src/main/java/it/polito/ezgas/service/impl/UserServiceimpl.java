package it.polito.ezgas.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import exception.InvalidLoginDataException;
import exception.InvalidUserException;
import it.polito.ezgas.converter.LoginConverter;
import it.polito.ezgas.converter.UserConverter;
import it.polito.ezgas.dto.IdPw;
import it.polito.ezgas.dto.LoginDto;
import it.polito.ezgas.dto.UserDto;
import it.polito.ezgas.entity.User;
import it.polito.ezgas.repository.UserRepository;
import it.polito.ezgas.service.UserService;

/**
 * Created by softeng on 27/4/2020.
 */
@Service
public class UserServiceimpl implements UserService {
	
	@Autowired
    UserRepository userRepository;

	public void addUserRepository (UserRepository ur) {
		this.userRepository = ur;
	}
	
	/*
	 * Queries the database and returns the UserDto corresponding to the userId
	 * passed as parameter. Throws an exception in case of invalid (negative)
	 * userId. Returns null if no user is found with the given Id
	 */
	@Override
	public UserDto getUserById(Integer userId) throws InvalidUserException {
		if (userId == null || userId.intValue() < 0) {
			throw new InvalidUserException("Invalid user ID");
		}

		User user = userRepository.findOne(userId);
		if (user == null) {
			return null;
		}
		return UserConverter.toUserDto(user);
	}

	/*
     * Stores the user passed as parameter in the application database
     */
	@Override
	public UserDto saveUser(UserDto userDto) {
		UserDto current = null;
		if (userDto.getUserId() == null) { // the user does not exist
			// check all data
			if(checkUserDto(userDto, new String[]{"nullcheck","emailNotAlreadyUsed","rightEmailFormat","passwordRight"})) { 
				User saved = userRepository.save(UserConverter.toUser(userDto));
				current = UserConverter.toUserDto(saved);}
		} else {                           // the user wants to update
			User found = userRepository.findOne(userDto.getUserId());
			if (found != null && checkUserDto(userDto, new String[]{"nullcheck"})) {
				// check password
				found.setUserName(userDto.getUserName());
				if(! userDto.getPassword().equals(found.getPassword())) {
					if(checkUserDto(userDto, new String[] {"passwordRight"})) {
						found.setPassword(userDto.getPassword());
					}
				}
				// check email
				if(!userDto.getEmail().equals(found.getEmail())) {
					if(checkUserDto(userDto, new String[]{"emailNotAlreadyUsed","rightEmailFormat"})){
							found.setEmail(userDto.getEmail());
					}
				}
				User updated = userRepository.save(found);
				current = UserConverter.toUserDto(updated);
			}
		}
		return current;
	}

	/*
	 * Returns an ArrayList with all the Users in the database. Returns an empty
	 * ArrayList if no user is registered in the database
	 */
	@Override
	public List<UserDto> getAllUsers() {
		List<UserDto> userDtos = new ArrayList<UserDto>();
		
		for (User current : userRepository.findAll()) {
			userDtos.add(UserConverter.toUserDto(current));
		}
		return userDtos;
	}

	/*
	 * Deletes the user with the given Id from the database. Throws an exception in
	 * case of invalid (negative) userId Returns true if the user is deleted
	 */
	@Override
	public Boolean deleteUser(Integer userId) throws InvalidUserException {
		if (userId == null || userId.intValue() < 0) {
			throw new InvalidUserException("Invalid user id");
		}
		
		User user = userRepository.findOne(userId);
		if (user == null) {
			return false;
		}
		
		userRepository.delete(userId);
		return true;
	}

	/*
	 * Performs a login in the application
	 */
	@Override
	public LoginDto login(IdPw credentials) throws InvalidLoginDataException {
		LoginDto loginDto = null;
		
		for (User current : userRepository.findAll()) {
			if (current.getEmail().equals(credentials.getUser()) && current.getPassword().equals(credentials.getPw())) {
				loginDto = LoginConverter.toLoginDto(current);
				break;
			}
		}
		
		if (loginDto == null) {
			throw new InvalidLoginDataException("Invalid login data");
		}
		
		return loginDto;
	}

	/*
	 * Increases by 1 the user Reputation (until maximum value equal to 5) Throws an
	 * exception in case of invalid (negative) userId Returns the current value of
	 * user reputation
	 */
	@Override
	public Integer increaseUserReputation(Integer userId) throws InvalidUserException {
		if (userId == null || userId.intValue() < 0) {
			throw new InvalidUserException("Invalid user id");
		}
		
		Integer reputation = null;
		
		User user = userRepository.findOne(userId);
		if (user != null) {
			reputation = user.getReputation();
			if (reputation.intValue() < 5) {
				reputation = new Integer(reputation.intValue() + 1);
			}
			user.setReputation(reputation);
			userRepository.save(user);
		}
		
		return reputation;
	}

	/*
	 * Decreases by 1 the user Reputation (until minimum value equal to -5) Throws
	 * an exception in case of invalid (negative) userId Returns the current value
	 * of user reputation
	 */
	@Override
	public Integer decreaseUserReputation(Integer userId) throws InvalidUserException {
		if (userId == null || userId.intValue() < 0) {
			throw new InvalidUserException("Invalid user id");
		}
		
		Integer reputation = null;
		
		User user = userRepository.findOne(userId);
		if (user != null) {
		    reputation = user.getReputation();
			if (reputation.intValue() > -5) {
				reputation = new Integer(reputation.intValue() - 1);
			}
			user.setReputation(reputation);
			userRepository.save(user);
		}
		
		return reputation;
	}
	
	/**
	 * Check user data transfer object
	 * 
	 * @param userDto
	 * @param checks
	 * @return
	 */
	public Boolean checkUserDto(UserDto userDto, String[] checks) {
		if(checks.length == 0) {
			return false;
		}
		
		for(String check: checks) {
			switch(check) {
				case "nullcheck":
//					if(userDto.getUserId()==null) {
//						return false;
//					}
					if(userDto.getUserName()==null) {
						return false;
					}
					if(userDto.getEmail()==null) {
						return false;
					}
					if(userDto.getPassword()==null) {
						return false;
					}
					break;
				case "emailNotAlreadyUsed": // check if email is already present 
					List<UserDto> userList=getAllUsers();
					for (UserDto user: userList) {
						if(user.getEmail().equals(userDto.getEmail())) {
							return false;
						}
					}	
					break;
				case "passwordRight": // check password length
					if(userDto.getPassword().length()<=2) {
						return false;
					}
					break;
				case "rightEmailFormat": // check email format
					Pattern p = Pattern.compile(".+@.+\\.[a-z]+", Pattern.CASE_INSENSITIVE);
					Matcher m = p.matcher(userDto.getEmail());
					boolean matchFound = m.matches();
					if(!matchFound) {
						return false;
					}
					break;
					//non funzionava. Per adesso ci limitiamo a controllare che sia contenuta la @
//				default:
//					return false;
				}
			}
		return true;
	}
}
