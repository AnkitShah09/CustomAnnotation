package com.example.DTO;

import java.util.Date;

import com.example.customAnnotation.CustomPatternMatcher;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

	// I have written regex in below 3 fields.
	@CustomPatternMatcher("^[a-zA-Z\\s]+")
	public String userName;

	@CustomPatternMatcher("^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+")
	public String email;

	@CustomPatternMatcher("^[0-9]{10}$")
	public String phoneNumber;

	// I haven't written Any regex for birthDate
	public Date birthDate;
}
