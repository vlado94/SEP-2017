package com.insurance.internal.resolve;

import java.util.Set;

import org.keycloak.representations.AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resolve")
@CrossOrigin(origins = "http://localhost:4500")
public class ResolveController {

	@Autowired
	AccessToken accessToken;

	@GetMapping
	private boolean getRole() {

		Set<String> roles = accessToken.getRealmAccess().getRoles();
		for (String role : roles) {
			if (role.equals(Roles.role_seller))
				return true;
		}
		return false;

	}
}
