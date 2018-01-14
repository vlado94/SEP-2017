package isok.isok.resolve;

import java.util.Set;

import org.keycloak.representations.AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/resolve")
@CrossOrigin(origins = "http://localhost:4200")
public class ResolveController {
	
	
	@Autowired
	AccessToken accessToken;
	
	@GetMapping
	private boolean getRole() {
		
		Set<String> roles = accessToken.getRealmAccess().getRoles();
		for(String role : roles) {
			if(role.equals(Roles.role_seller) || role.equals(Roles.role_price_management))
				return true;
		}
		return false;
		
	}
	
	
	@GetMapping("/price_management")
	private boolean getRolePriceManagement() {
		
		Set<String> roles = accessToken.getRealmAccess().getRoles();
		for(String role : roles) {
			if(role.equals(Roles.role_price_management))
				return true;
		}
		return false;
		
	}

}
