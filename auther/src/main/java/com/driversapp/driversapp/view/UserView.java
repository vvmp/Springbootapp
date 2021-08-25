package com.driversapp.driversapp.view;

import java.util.Set;



import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserView {

	private Long userId;

	private String uuid;

	private String firstName;

	private String lastName;

	private boolean isVerified;



	private Set<String> roles;
}
