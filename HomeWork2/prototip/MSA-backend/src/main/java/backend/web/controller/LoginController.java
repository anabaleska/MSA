package backend.web.controller;

import backend.model.dto.LoginDTO;

import backend.model.dto.LoginResponse;
import backend.services.AuthService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    private final AuthService authService;

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Autowired
    private AuthenticationManager authenticationManager;

    public LoginController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping
    public ResponseEntity<?> login(@RequestBody LoginDTO userLoginDTO) {
        try {


            String token = authService.login(userLoginDTO.email(), userLoginDTO.password());


            return ResponseEntity.ok(new LoginResponse(token));
        } catch (Exception ex) {

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed: " + ex.getMessage());
        }
    }

    private String generateJwtToken(Authentication authentication) {
        String username = authentication.getName();
        String roles = authentication.getAuthorities().toString();

        return Jwts.builder()
                .setSubject(username)
                .claim("roles", roles)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    @GetMapping("/check-login")
    public ResponseEntity<String> checkLogin(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            return ResponseEntity.ok("You are logged in as " + authentication.getName());
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You are not logged in");
    }
}
