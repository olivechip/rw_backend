package com.olivechip.restaurant_waitlist.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import com.olivechip.restaurant_waitlist.dto.AuthStatusDTO;
import com.olivechip.restaurant_waitlist.dto.LoginRequest;
import com.olivechip.restaurant_waitlist.dto.LoginResponseDTO;
import com.olivechip.restaurant_waitlist.dto.LogoutResponseDTO;
import com.olivechip.restaurant_waitlist.entity.Staff;
import com.olivechip.restaurant_waitlist.repository.StaffRepository;
import com.olivechip.restaurant_waitlist.util.AuthConverter;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private StaffRepository staffRepository;

    @GetMapping("/status")
    public ResponseEntity<AuthStatusDTO> getAuthStatus() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isLoggedIn = false;
        UserDetails userDetails = null;

        if (authentication != null && authentication.isAuthenticated()
                && authentication.getPrincipal() instanceof UserDetails) {
            isLoggedIn = true;
            userDetails = (UserDetails) authentication.getPrincipal();
        }

        return ResponseEntity.ok(AuthConverter.convertToAuthStatusDto(userDetails, isLoggedIn));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(HttpServletRequest request, @RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUsername());
            Optional<Staff> staffOptional = staffRepository.findByUsername(userDetails.getUsername());

            if (staffOptional.isPresent()) {
                Staff staff = staffOptional.get();
                if (staff.getRestaurant() != null) {
                    HttpSession session = request.getSession(true);
                    session.setAttribute("restaurantId", staff.getRestaurant().getId());
                    System.out.println("restaurantId set in session: " + staff.getRestaurant().getId());

                    return ResponseEntity.ok(AuthConverter.convertToLoginResponseDto(staff));
                } else {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new LoginResponseDTO(
                            "Restaurant association not found", null, null));
                }
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new LoginResponseDTO("Staff member not found", null, null));
            }
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new LoginResponseDTO("Invalid credentials", null, null));
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<LogoutResponseDTO> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate();
            SecurityContextHolder.clearContext();
            return ResponseEntity.ok(new LogoutResponseDTO("Logged out successfully"));
        } else {
            return ResponseEntity.ok(new LogoutResponseDTO("No active session to logout from."));
        }
    }
}