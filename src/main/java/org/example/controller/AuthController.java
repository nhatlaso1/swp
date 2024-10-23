package org.example.controller;

import org.example.data.request.LoginRequest;
import org.example.data.request.RegisterRequest;
import org.example.data.response.LoginResponse;
import org.example.data.response.ResponseData;
import org.example.entities.User;
import org.example.repositories.UserRepository;
import org.example.services.interfaces.IUserService;
import org.example.utils.JwtTokenProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
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
    private final IUserService userService;
    private final UserRepository userRepository;

    public AuthController(AuthenticationManager authenticationManager
            , JwtTokenProvider tokenProvider
            , IUserService userService
            , UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseData<LoginResponse>> authenticate(@RequestBody LoginRequest loginRequest) {
        try {

            User user = userRepository.findByUsername(loginRequest.getUsername()).get();

            if(user.getStatus().equals("Inactive")){
                ResponseData<LoginResponse> responseData = new ResponseData<>("ACCOUNT_INACTIVE"
                        , "Your account inactive. Please contact to admin", null);

                return ResponseEntity.ok(responseData);
            }

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
        } catch (AuthenticationException e) {
            ResponseData<LoginResponse> responseData = new ResponseData<>("LOGIN_FAILED", "Invalid username or password", null);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseData);
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<ResponseData<String>> logout() {
        SecurityContextHolder.clearContext();
        ResponseData<String> responseData = new ResponseData<>("LOGOUT_SUCCESS", "Logout successful", null);

        return ResponseEntity.ok(responseData);
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseData<String>> register(@RequestBody RegisterRequest request) {
        int x = userService.register(request);
        ResponseData<String> responseData = null;

        if(x == -1){
            responseData = new ResponseData<>("USER_ALREADY_EXIST", "Already exist user with this email.", null);
            return ResponseEntity.ok(responseData);
        }

        responseData = new ResponseData<>("REGISTER_SUCCESS", "Register successful", null);
        return ResponseEntity.ok(responseData);
    }
}
