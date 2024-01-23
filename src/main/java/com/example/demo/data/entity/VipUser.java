package com.example.demo.data.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class VipUser {

	@Id
	private Integer id;
	private String name;
	private Integer age;
	private Boolean isAdult;
}
