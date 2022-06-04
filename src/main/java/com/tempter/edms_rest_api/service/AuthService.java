package com.tempter.edms_rest_api.service;

import com.tempter.edms_rest_api.dto.AuthRequest;
import com.tempter.edms_rest_api.dto.UserDTO;

public interface AuthService {

    UserDTO login(AuthRequest authRequest);

    UserDTO register(AuthRequest authRequest);
}
