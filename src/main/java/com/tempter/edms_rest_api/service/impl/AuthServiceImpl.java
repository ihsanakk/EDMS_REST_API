package com.tempter.edms_rest_api.service.impl;

import com.tempter.edms_rest_api.controllerAdvice.customException.AuthException;
import com.tempter.edms_rest_api.dao.UserDAO;
import com.tempter.edms_rest_api.dto.AuthRequest;
import com.tempter.edms_rest_api.dto.UserDTO;
import com.tempter.edms_rest_api.entity.User;
import com.tempter.edms_rest_api.entity.enums.ERole;
import com.tempter.edms_rest_api.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final UserDAO userDAO;
    private final ModelMapper modelMapper;

    @Override
    public UserDTO login(AuthRequest authRequest) {
        if (authRequest.getUsername()==null) {
            throw new AuthException("Username must be present");
        }
        else if (authRequest.getPassword()==null) {
            throw new AuthException("Password must be present");
        }
        else {
            User user = userDAO.findByUsername(authRequest.getUsername()).orElseThrow(() -> new AuthException("This username does not exist!"));
            if (user.getPassword().equals(authRequest.getPassword()))
                return modelMapper.map(user,UserDTO.class);
            else
                throw new AuthException("Incorrect password");
        }
    }

    @Override
    public UserDTO register(AuthRequest authRequest) {
        if (authRequest.getUsername()==null) {
            throw new AuthException("Username must be present");
        }
        else if (authRequest.getEmail()==null) {
            throw new AuthException("Email must be present");
        }
        else if (authRequest.getPassword()==null) {
            throw new AuthException("Password must be present");
        }
        else if (userDAO.existsByUsername(authRequest.getUsername())) {
            throw new AuthException("This username is already exist");
        }
        else if (userDAO.existsByEmail(authRequest.getEmail())){
            throw new AuthException("This email is already exist");
        }
        else {
            User user = new User();
            user.setUsername(authRequest.getUsername());
            user.setEmail(authRequest.getEmail());
            user.setPassword(authRequest.getPassword());
            user.setName(authRequest.getName());
            user.setLastname(authRequest.getLastname());
            user.setDepartment(authRequest.getDepartment());
            user.setRole(ERole.USER);
            userDAO.save(user);
            return modelMapper.map(user,UserDTO.class);
        }
    }
}
