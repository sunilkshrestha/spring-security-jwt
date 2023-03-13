package com.springsecurity.jwt.springsecurityjwt.controller;


import com.springsecurity.jwt.springsecurityjwt.model.JwtRequest;
import com.springsecurity.jwt.springsecurityjwt.model.JwtResponse;
import com.springsecurity.jwt.springsecurityjwt.service.CustomUserDetailService;
import com.springsecurity.jwt.springsecurityjwt.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class JwtController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Autowired
    private JwtUtil jwtUtil;

    @RequestMapping(value = "/generateToken", method = RequestMethod.POST)
    public ResponseEntity<JwtResponse> generateToken(@RequestBody JwtRequest jwtRequest){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
        UserDetails userDetails = customUserDetailService.loadUserByUsername(jwtRequest.getUsername());
        String jwtToken = jwtUtil.generateToken(userDetails);

        JwtResponse jwtResponse = new JwtResponse(jwtToken);
        return new ResponseEntity<JwtResponse>(jwtResponse, HttpStatus.OK);

    }
}
