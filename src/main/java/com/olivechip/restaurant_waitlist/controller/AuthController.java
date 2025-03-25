package com.olivechip.restaurant_waitlist.controller;

import java.util.Map;
import java.util.HashMap;
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

import com.olivechip.restaurant_waitlist.dto.LoginRequest;
import com.olivechip.restaurant_waitlist.dto.StaffLoginResponseDTO;
import com.olivechip.restaurant_waitlist.entity.Staff;
import com.olivechip.restaurant_waitlist.repository.StaffRepository;

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
    public ResponseEntity<Map<String, Object>> getAuthStatus() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Map<String, Object> response = new HashMap<>();

        if (authentication != null && authentication.isAuthenticated()
                && authentication.getPrincipal() instanceof UserDetails) {

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            Staff staff = (Staff) userDetails;

            Map<String, Object> staffData = new HashMap<>();
            staffData.put("firstName", staff.getFirstName());
            staffData.put("lastName", staff.getLastName());
            staffData.put("username", staff.getUsername());
            staffData.put("role", staff.getRole());
            staffData.put("restaurant", staff.getRestaurant());

            response.put("isLoggedIn", true);
            response.put("staff", staffData);
        } else {
            response.put("isLoggedIn", false);
        }

        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<StaffLoginResponseDTO> login(HttpServletRequest request,
            @RequestBody LoginRequest loginRequest) {
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

                    StaffLoginResponseDTO response = new StaffLoginResponseDTO(
                            "Login successful",
                            staff.getFirstName(),
                            staff.getLastName(),
                            staff.getUsername(),
                            staff.getRole(),
                            staff.getRestaurant().getId());
                    return ResponseEntity.ok(response);
                } else {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new StaffLoginResponseDTO(
                            "Restaurant association not found",
                            null,
                            null,
                            null,
                            null,
                            null));
                }
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new StaffLoginResponseDTO(
                        "Staff member not found",
                        null,
                        null,
                        null,
                        null,
                        null));
            }
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new StaffLoginResponseDTO(
                    "Invalid credentials",
                    null,
                    null,
                    null,
                    null,
                    null));
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<Map<String, Object>> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Map<String, Object> response = new HashMap<>();
        if (session != null) {
            session.invalidate();
            SecurityContextHolder.clearContext();
            response.put("message", "Logged out successfully");
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "No active session to logout from.");
            return ResponseEntity.ok(response);
        }
    }
}