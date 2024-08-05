package com.prasad.exam.security;
//package com.prasad.exam.service;
//
//import java.util.Collection;
//import java.util.HashSet;
//import java.util.Set;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import com.prasad.exam.model.Authority;
//import com.prasad.exam.model.User;
//import com.prasad.exam.model.UserRole;
//
//public class MyUserDetails implements UserDetails{
//
//	@Autowired
//	private UserRole userRoles;
//	
//	private String userName;
//	private String password;
//	
//	public MyUserDetails(User user) {
//		this.userName=user.getUserName();
//		this.password=user.getPassword();
//	}
//	
//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		
//		Set<Authority> authorities = new HashSet<>();
//		
//		((Iterable<Authority>) userRoles).forEach(userRole -> {
//			authorities.add(new Authority(((UserRole) userRoles).getRole().getRoleName()));
//		});
//		
//		return authorities;
//	}
//	@Override
//	public String getPassword() {
//		// TODO Auto-generated method stub
//		return password;
//	}
//
//	@Override
//	public String getUsername() {
//		// TODO Auto-generated method stub
//		return userName;
//	}
//
//	@Override
//	public boolean isAccountNonExpired() {
//		// TODO Auto-generated method stub
//		return true;
//	}
//
//	@Override
//	public boolean isAccountNonLocked() {
//		// TODO Auto-generated method stub
//		return true;
//	}
//
//	@Override
//	public boolean isCredentialsNonExpired() {
//		// TODO Auto-generated method stub
//		return true;
//	}
//
//	@Override
//	public boolean isEnabled() {
//		// TODO Auto-generated method stub
//		return true;
//	}
//
//}
