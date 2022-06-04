package com.tempter.edms_rest_api.service;

import com.tempter.edms_rest_api.dto.UserDTO;
import com.tempter.edms_rest_api.dto.UserDocDTO;

import java.util.List;

public interface UserService {

    UserDocDTO getUserById(int userId);

    List<UserDTO> getUserByDepartment(String department);

    List<UserDTO> getAllOfficerUsers();

    List<UserDTO> getAllUsers();

    void createUser(String username, String password, String email);

    UserDTO findUserByUsername(String username);

    UserDTO findUserByEmail(String email);

}
