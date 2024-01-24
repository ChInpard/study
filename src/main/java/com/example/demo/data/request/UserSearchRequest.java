package com.example.demo.data.request;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class UserSearchRequest {
	
	private Integer id;
    private String name;
    private Integer age;
    
}
