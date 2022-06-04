package com.tempter.edms_rest_api.controller;

import com.tempter.edms_rest_api.dto.UserDTO;
import com.tempter.edms_rest_api.dto.UserDocDTO;
import com.tempter.edms_rest_api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;


    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

    @GetMapping("/officers")
    public ResponseEntity<List<UserDTO>> getOfficers() {
        return ResponseEntity.ok().body(userService.getAllOfficerUsers());
    }

    @GetMapping("/department-users")
    public ResponseEntity<List<UserDTO>> getByDepartment(String department) {
        return ResponseEntity.ok().body(userService.getUserByDepartment(department));
    }
    @GetMapping("/")
    public ResponseEntity<UserDocDTO> getUserById(int userId) {
        return ResponseEntity.ok().body(userService.getUserById(userId));
    }

}
