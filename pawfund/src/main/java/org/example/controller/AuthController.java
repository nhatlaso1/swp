package org.example.controller;

import org.example.data.request.LoginRequest;
import org.example.data.response.LoginResponse;
import org.example.data.response.ResponseData;
import org.example.utils.JwtTokenProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;

    public AuthController(AuthenticationManager authenticationManager, JwtTokenProvider tokenProvider) {
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseData<LoginResponse>> authenticateUser(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String token = tokenProvider.generateToken(authentication);

        var roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        LoginResponse loginResponse = new LoginResponse(userDetails.getUsername(), roles.get(0), token);
        ResponseData<LoginResponse> responseData = new ResponseData<>("LOGIN_SUCCESSFUL", "Login successful", loginResponse);

        return ResponseEntity.ok(responseData);
    }

    @PostMapping("/logout")
    public ResponseEntity<ResponseData<String>> logoutUser() {
        SecurityContextHolder.clearContext();
        ResponseData<String> responseData = new ResponseData<>("LOGOUT_SUCCESS", "Logout successful", null);

        return ResponseEntity.ok(responseData);
    }
}
