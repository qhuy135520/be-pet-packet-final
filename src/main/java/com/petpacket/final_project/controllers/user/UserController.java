package com.petpacket.final_project.controllers.user;

import java.util.List;

import com.petpacket.final_project.dto.request.user.UpgradeRequestDto;
import com.petpacket.final_project.dto.response.ErrorResponse;
import com.petpacket.final_project.dto.response.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.petpacket.final_project.entities.user.User;
import com.petpacket.final_project.services.user.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("")
	public ResponseEntity<List<User>> getAllUser() {
		List<User> listOfUsers = userService.getAllUser();
		return ResponseEntity.ok(listOfUsers);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getUserById(@PathVariable Integer id) {
		User user = userService.getUserById(id);
		if (user != null) {
			SuccessResponse successResponse = new SuccessResponse();
			successResponse.setMessage("Fetch successfully");
			successResponse.setData(user);
			successResponse.setStatusCode(HttpStatus.OK.value());
			return ResponseEntity.ok(successResponse);
		} else {
			ErrorResponse errorResponse = new ErrorResponse("UserId does not exist", "Not found",
					HttpStatus.NOT_FOUND.value());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
		}
	}

	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user) {
		User savedUser = userService.saveUser(user);
		return ResponseEntity.ok(savedUser);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateUser(@PathVariable Integer id, @RequestBody User user) {
		User existUser = userService.updateUser(id, user);
		SuccessResponse successResponse = new SuccessResponse();
		successResponse.setData(existUser);
		successResponse.setMessage("Updated successfully");
		successResponse.setStatusCode(HttpStatus.OK.value());
		return ResponseEntity.ok(successResponse);
	}

	@PostMapping("/upgrade-user/{userId}")
	public ResponseEntity<?> upgradeUser(@PathVariable Integer userId, @RequestBody UpgradeRequestDto upgradeRequest) {
		userService.upgradeUserCreateRequest(upgradeRequest);
		SuccessResponse successResponse = new SuccessResponse();
		successResponse.setData("");
		successResponse.setMessage("successful");
		successResponse.setStatusCode(HttpStatus.OK.value());
		
		return ResponseEntity.ok(successResponse);
	}

}
