package com.prasad.exam.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prasad.exam.dto.LoginResponse;
import com.prasad.exam.dto.ResponseDto;
import com.prasad.exam.dto.UserDto;
import com.prasad.exam.model.ExamUsers;
import com.prasad.exam.model.LoginAudit;
import com.prasad.exam.model.Role;
import com.prasad.exam.repository.ExamUsersRepo;
import com.prasad.exam.repository.LoginAuditRepo;
import com.prasad.exam.repository.RoleRepository;
import com.prasad.exam.security.JwtTokenProvider;

@Service
public class LoginServiceImpl implements LoginService{

	@Autowired
	private Logger logger;

	@Autowired
	PasswordEncoder password;

	@Autowired
	private ExamUsersRepo usersRepo;

	@Autowired
	private RoleRepository roleRepo;

	@Autowired
	private LoginAuditRepo loginAuditRepo;

	@Autowired
	private JwtTokenProvider tokenProvider;


	@Override
	public ResponseEntity<ResponseDto> login(ExamUsers users, HttpServletRequest request) {

		ResponseEntity<ResponseDto> response = null;
		ResponseDto res = new ResponseDto();
		try {

			logger.info("UserName:"+users.getUserName()+""+users.getEmpId() );

			if(users.getEmpId()!=null) {
				users.setEmpId(users.getEmpId());
			}

			response= loginWithUserName(users, res, request);
		} catch (Exception e) {
			e.printStackTrace();
			res.setUserInfo(null);
			res.setResult(false);
			response = ResponseEntity.status(HttpStatus.FORBIDDEN).body(res);
			logger.error("Error while login" + e.getMessage());
		}

		return response;
	}

	// Login With Username
	private ResponseEntity<ResponseDto> loginWithUserName(ExamUsers users, ResponseDto res, HttpServletRequest request){
		ResponseEntity<ResponseDto> response = null;
		try {
			Date lastLoginTime = null;
			LoginResponse logResponse = new LoginResponse();

			List<Role> roleList = getRoleList(users.getUserName(), users.getEmpId());
			System.out.println(users.getUserName());
			System.out.println(users.getEmpId());
			if(roleList !=null && (roleList.size())> 0) {
				users.setEmpId(roleList.get(0).getEmpId());
				users.setUserName(roleList.get(0).getUserName());

				lastLoginTime = saveLoginDetails(users, logResponse, roleList);
				SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy hh:mm:ss");
				authLogin(users, res, logResponse);
				if(res.getJwtToken() !=null) {
					logger.info("letest id " + users.getUserName());
					logger.info("letest tokan " + res.getJwtToken());
					usersRepo.updateToken(users.getUserName(), res.getJwtToken().replace("Bearer ", ""), 1L);

					res.setSttausCode(200);
					res.setReason("USER AUTHORIZED");
					res.setResult(true);

					res.getUserInfo().setLastLoginTime(sdf.format(lastLoginTime));
					res.getUserInfo().setRoles(roleList);
					response = ResponseEntity.status(HttpStatus.OK).body(res);
					logger.info("Login Success");
				}else {
					res.setSttausCode(401);
					res.setReason("USER NOT AUTHORIZED");
					res.setResult(false);
					response = ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(res);
				}
			}else {
				res.setReason("User not found please contact your administrator");
				res.setUserInfo(null);
				res.setResult(false);
				response = ResponseEntity.status(HttpStatus.FORBIDDEN).body(res);	
			}
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error while login" + e.getMessage());
		}		
		return response;
	}

	@Transactional
	private void authLogin(ExamUsers request, ResponseDto res, LoginResponse logResponse) {
		/*
		 * AUTHINTICATE USERNAME AND PASSWORD
		 */
		StringBuffer authToken = null;
		try {
			authToken = new StringBuffer("Bearer ");
			String auth = tokenProvider.generateToken(request);

			if (auth != null) {
				authToken.append(auth);
			}
			ExamUsers login = usersRepo.findByEmpId(request.getEmpId());
			if (login != null) {
				res.setJwtToken(authToken.toString());
				UserDto dto = new UserDto();
				BeanUtils.copyProperties(login, dto);
				res.setUserInfo(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error while auth login" + e.getMessage());
//			res.setMessage(e.getMessage());
//			res.setResult(false);
//			ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
		}
	}


	// SAVE Login Details in Login_Audit Table
	private Date saveLoginDetails(ExamUsers users, LoginResponse logResponse, List<Role> roleList) {
		Date lastLoginTime;
		ExamUsers checkUser = usersRepo.findByEmpId(users.getEmpId());
		if (checkUser == null) {
			//			ExamUsers newUser = new ExamUsers(new Date(),
			//					(users.getPassword()) != null ? password.encode(users.getPassword()) : "", users.getUserName());
			ExamUsers newUser = new ExamUsers(users.getUserName(), (users.getPassword()) != null ? password.encode(users.getPassword()) : "",
					new Date());
			newUser.setLastLoginTime(new Date());
			lastLoginTime = newUser.getLastLoginTime();
			newUser.setEmpId(users.getEmpId());
			roleList.stream().forEach(x -> {
				newUser.setRole(x.getRoleName());
			});

			try {
				ObjectMapper newmapper = new ObjectMapper();
				String newJsonDataOfQues = newmapper.writeValueAsString(logResponse);
				newUser.setLogResponse(newJsonDataOfQues);
			} catch (JsonProcessingException e) {
				logger.error("Error while save login Details" + e.getMessage());
				e.printStackTrace();
			}
			ExamUsers updateduser = usersRepo.save(newUser);
			checkUser = newUser;
		} else {
			lastLoginTime = checkUser.getLastLoginTime();

			roleList.stream().forEach(x -> {
				users.setRole(x.getRoleName());

			});
			ObjectMapper newmapper = new ObjectMapper();
			String newJsonDataOfQues;
			try {
				newJsonDataOfQues = newmapper.writeValueAsString(logResponse);

				usersRepo.updatePassword(new Timestamp(System.currentTimeMillis()),
						(users.getPassword() != null) ? password.encode(users.getPassword()) : "", users.getUserName(),
								users.getRole(), users.getEmpId(), newJsonDataOfQues);
			} catch (JsonProcessingException e) {
				logger.error("Error while save login Details" + e.getMessage());
				e.printStackTrace();
			}

		}

		// SAVE DATA FOR AUDIT IN LoginAudit Table

		LoginAudit audit = new LoginAudit(users.getUserName(), new Date(), checkUser);
		loginAuditRepo.save(audit);
		return lastLoginTime;
	}

	public List<Role> getRoleList(String UserName, String empId) {
		List<Role> roleList = new ArrayList<>();
		try {
			String role = roleRepo.findByUserRole(UserName, empId);
			logger.info("ROLE FIND IN DB: " + role);

			if(role !=null) {
				Role roleInfo = new Role(role, UserName, empId);
				roleList.add(roleInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error while get role from login credentials" + empId + e.getMessage());
		}
		return roleList;
	}


	@Override
	public Boolean invalidateUserToken(String token) {
		// TODO Auto-generated method stub
		return null;
	}

}
