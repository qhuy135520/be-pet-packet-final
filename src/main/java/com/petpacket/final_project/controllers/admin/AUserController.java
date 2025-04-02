package com.petpacket.final_project.controllers.admin;

import com.petpacket.final_project.dto.response.ErrorResponse;
import com.petpacket.final_project.dto.response.SuccessResponse;
import com.petpacket.final_project.entities.user.User;
import com.petpacket.final_project.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/users")
public class AUserController {

    @Autowired
    private UserService userService;
    @GetMapping
    public ResponseEntity<?> getAllUser() {
        List<User> listOfUsers = userService.getAllUser();
        SuccessResponse successResponse = new SuccessResponse();
        successResponse.setMessage("Fetch list of users successfully");
        successResponse.setData(listOfUsers);
        successResponse.setStatusCode(HttpStatus.OK.value());
        return ResponseEntity.ok(successResponse);
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
    public ResponseEntity<?> createUser(@RequestBody User user) {
        User savedUser = userService.saveUser(user);
        SuccessResponse successResponse = new SuccessResponse();
        successResponse.setMessage("Create user successfully");
        successResponse.setData(savedUser);
        successResponse.setStatusCode(HttpStatus.OK.value());
        return ResponseEntity.ok(savedUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Integer id, @RequestBody User user) {
        User updatedUser = userService.updateUser(id, user);
        SuccessResponse successResponse = new SuccessResponse();
        successResponse.setData(updatedUser);
        successResponse.setMessage("Updated user successfully");
        successResponse.setStatusCode(HttpStatus.OK.value());
        return ResponseEntity.ok(successResponse);
    }

    @PutMapping("/block/{id}")
    public ResponseEntity<?> blockUser(@PathVariable Integer id){
        User updatedUser = userService.blockUser(id);
        SuccessResponse successResponse = new SuccessResponse();
        successResponse.setData(updatedUser);
        successResponse.setMessage("Updated user successfully");
        successResponse.setStatusCode(HttpStatus.OK.value());
        return ResponseEntity.ok(successResponse);
    }

    @PutMapping("/unblock/{id}")
    public ResponseEntity<?> unBlockUser(@PathVariable Integer id){
        User updatedUser = userService.unBlockUser(id);
        SuccessResponse successResponse = new SuccessResponse();
        successResponse.setData(updatedUser);
        successResponse.setMessage("Updated user successfully");
        successResponse.setStatusCode(HttpStatus.OK.value());
        return ResponseEntity.ok(successResponse);
    }
}

