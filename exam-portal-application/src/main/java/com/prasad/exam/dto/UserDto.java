package com.prasad.exam.dto;

import java.sql.Timestamp;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.prasad.exam.model.Role;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class UserDto {

	private Long id;
	
    private String userName;
    
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp tokanStartTime;
    
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp updatedDate;
    
//    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private String lastLoginTime;

    List<Role> roles;
}
