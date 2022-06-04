package com.tempter.edms_rest_api.service.impl;

import com.tempter.edms_rest_api.controllerAdvice.customException.EntityNotFoundException;
import com.tempter.edms_rest_api.dao.UserDAO;
import com.tempter.edms_rest_api.dto.UserDTO;
import com.tempter.edms_rest_api.dto.UserDocDTO;
import com.tempter.edms_rest_api.entity.User;
import com.tempter.edms_rest_api.entity.enums.ERole;
import com.tempter.edms_rest_api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final ModelMapper modelMapper;

    private final UserDAO userDAO;

    @Override
    public UserDocDTO getUserById(int userId) {
        return modelMapper
                .map(userDAO.findById(userId).orElseThrow(() ->
                        new EntityNotFoundException("User not found by id: " + userId)),UserDocDTO.class);
    }

    @Override
    public List<UserDTO> getUserByDepartment(String department) {
        return Arrays.asList(modelMapper.map(userDAO.findByDepartment(department),UserDTO[].class));
    }

    @Override
    public List<UserDTO> getAllOfficerUsers() {
        return Arrays.asList(modelMapper.map(userDAO.findByRole(ERole.OFFICER),UserDTO[].class));
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return Arrays.asList(modelMapper.map(userDAO.findAll(),UserDTO[].class));
    }

    @Override
    public void createUser(String username, String password, String email) {
        User user = new User();
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(password);

        userDAO.save(user);

    }

    @Override
    public UserDTO findUserByUsername(String username) {
        User user = userDAO.findByUsername(username).orElseThrow(() -> new EntityNotFoundException("User not found by username: "+ username));
        return modelMapper.map(user,UserDTO.class);
    }

    @Override
    public UserDTO findUserByEmail(String email) {
        User user = userDAO.findByEmail(email).orElseThrow(() -> new EntityNotFoundException("User not found by email: "+ email));
        return modelMapper.map(user,UserDTO.class);
    }

}
