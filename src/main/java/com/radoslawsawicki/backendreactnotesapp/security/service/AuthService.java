package com.radoslawsawicki.backendreactnotesapp.security.service;

import com.radoslawsawicki.backendreactnotesapp.security.dto.LoginDto;
import com.radoslawsawicki.backendreactnotesapp.security.dto.RegisterDto;
import java.util.Map;

public interface AuthService {

    String register(RegisterDto registerDto);

    String login(LoginDto loginDto);

    String updatePasswordByFields(String emailName, Map<String, Object> fields);
}
