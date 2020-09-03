package com.tmn.ecommerce;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tmn.ecommerce.dto.UserDto;
import com.tmn.ecommerce.entity.Role;
import com.tmn.ecommerce.service.UserService;

@SpringBootTest
class EcommerceApplicationTests {

	@Autowired
	UserService userService;
	
	@Test
	void contextLoads() {
	}

	@Test
	void addData() {
		try {
			UserDto admin = new UserDto();
			admin.setUsername("ADMIN");
			admin.setEmail("ADMIN@gmail.com");
			admin.setPassword("ADMIN");
			Role role1 = new Role();
			//admin.setRoles(Arrays.asList(role1));
			userService.saveUser(admin);
			
			UserDto client = new UserDto();
			client.setUsername("CLIENT");
			client.setEmail("CLIENT@gmail.com");
			client.setPassword("CLIENT");
			Role role2 = new Role();
			//client.setRoles(Arrays.asList(role2));
			userService.saveUser(client);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	public static void main(String[] args) {
		String name = "C:\\fakepath\\ui.jpg";
		System.out.println(name.substring(12));
	}
}
