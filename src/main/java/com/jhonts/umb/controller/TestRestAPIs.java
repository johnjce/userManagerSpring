package com.jhonts.umb.controller;

import com.jhonts.umb.message.request.SignUpForm;
import com.jhonts.umb.message.response.ResponseMessage;
import com.jhonts.umb.model.Role;
import com.jhonts.umb.model.RoleName;
import com.jhonts.umb.model.User;
import com.jhonts.umb.model.UserPrintable;
import com.jhonts.umb.repository.RoleRepository;
import com.jhonts.umb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class TestRestAPIs {

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@GetMapping("/api/test/users")
	public List<UserPrintable> getUserList() {
		List<UserPrintable> up = new ArrayList<>();
		for (User ul : userRepository.findAll()) {
			up.add(new UserPrintable(ul));
		}
		return up;
	}

	@GetMapping("/api/test/user/{name}")
	public User getUserGivenUsername(@PathVariable String name){
		return userRepository.findByName(name).orElseThrow(
				() -> new UsernameNotFoundException("User Not Found with -> name: " + name));
	}

	@PostMapping("/api/test/userAdd")
	public ResponseEntity<?> addUser(@Valid @RequestBody SignUpForm signUpRequest) {
		Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>)    SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		Boolean administrator = false;

		for (SimpleGrantedAuthority userRole :  authorities) {
			if(userRole.toString().equals("admin")) administrator = true;
		}

		if(!administrator){
			return new ResponseEntity<>(new ResponseMessage("Fail -> Only Administrator can add new users!"),
					HttpStatus.BAD_REQUEST);
		}

		if (userRepository.existsByName(signUpRequest.getName())) {
			return new ResponseEntity<>(new ResponseMessage("Fail -> Username is already taken!"),
					HttpStatus.BAD_REQUEST);
		}

		User user = new User(signUpRequest.getName(),
				encoder.encode(signUpRequest.getPassword()));

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();
		if(strRoles==null) {
			strRoles = new HashSet<>();
			strRoles.add("standard");
		}
		strRoles.forEach(role -> {
			if ("admin".equals(role)) {
				Role adminRole = roleRepository.findByName(RoleName.admin)
						.orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
				roles.add(adminRole);
			} else {
				Role userRole = roleRepository.findByName(RoleName.standard)
						.orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
				roles.add(userRole);
			}
		});

		user.setRoles(roles);
		userRepository.save(user);

		return new ResponseEntity<>(new ResponseMessage("User registered successfully!"), HttpStatus.OK);
	}
}