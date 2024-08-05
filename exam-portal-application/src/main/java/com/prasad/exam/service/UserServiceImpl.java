package com.prasad.exam.service;

import java.util.Set;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.prasad.exam.dto.ResponseDto;
import com.prasad.exam.helper.UserFoundException;
import com.prasad.exam.model.User;
import com.prasad.exam.model.UserRole;
import com.prasad.exam.repository.RoleRepository;
import com.prasad.exam.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private Logger logger;

	@Override
	public User createUser(User user, Set<UserRole> userRoles) throws Exception {

		User local = userRepository.findByUserName(user.getUsername());
		if(local !=null) {

			System.out.println("User is Already There ..!!");
			throw new UserFoundException();

		}else {
			// User Create

			for(UserRole ur: userRoles) {

				roleRepository.save(ur.getRole());
			}

			user.getUserRole().addAll(userRoles);
			local = this.userRepository.save(user);
		}

		return local;
	}

	@Override
	public ResponseEntity<ResponseDto> getUserByuserName(String userName) {
		ResponseDto res = new ResponseDto();
		User user = new User();
		try {
			if (userName == null) {
				res.setSttausCode(400);
				res.setResult(false);
				res.setMessage("USER IS NOT FOUND");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
			}else {
				user = userRepository.findByUserName(user.getUsername());

				res.setSttausCode(200);
				res.setResult(true);
				res.setMessage("USER IS FOUND");
				res.setUser(user);
				return ResponseEntity.status(HttpStatus.OK).body(res);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("INTERNAL SERVER ERROR"+e.getMessage());
			res.setSttausCode(500);
			res.setResult(false);
			res.setMessage(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
		}
	}

	@Override
	public String deleteUserById(long id) {

		userRepository.deleteById(id);

		return "USER DELETED BY THIS ID : " + id;
	}

}
