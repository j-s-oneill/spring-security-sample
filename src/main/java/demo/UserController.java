/*
 * Copyright 2002-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package demo;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/admin/user")
public class UserController {
	private final UserDetailsManager manager;

	public UserController(UserDetailsManager manager) {
		this.manager = manager;
	}

	@PostMapping("/role")
	public String roles(@RequestParam String username, @RequestParam String[] roles) {
		UserDetails userDetails = manager.loadUserByUsername(username);
		UserDetails updated = User.withUserDetails(userDetails)
				.roles(roles)
				.build();
		manager.updateUser(updated);
		return "redirect:/";
	}
}
