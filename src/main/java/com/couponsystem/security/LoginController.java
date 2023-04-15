package com.couponsystem.security;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.couponsystem.beans.LoginForm;
import com.couponsystem.beans.LoginResponse;
import com.couponsystem.exceptions.LogException;
import com.couponsystem.exceptions.NotFoundException;
import com.couponsystem.service.LoginService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LoginController {
    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        super();
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginForm loginForm) throws NotFoundException {

        HttpHeaders responseHeaders = new HttpHeaders();

        try {
            LoginResponse loginResponse = loginService.login(loginForm);
            responseHeaders.set("CouponSystem_Header", loginResponse.getToken());
            return ResponseEntity.ok().headers(responseHeaders).body(loginResponse);
//			return ResponseEntity.ok().body(loginResponse);

        } catch (LogException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }
}
