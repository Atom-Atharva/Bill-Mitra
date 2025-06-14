package in.billmitra.controller;

import in.billmitra.io.AuthRequest;
import in.billmitra.io.AuthResponse;
import in.billmitra.service.UserService;
import in.billmitra.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;
    private final UserService userService;

    @PostMapping("/encode")
    public String encodePassword(@RequestBody Map<String,String> request){
        return passwordEncoder.encode(request.get("password"));
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {
        // Authenticate the User
        authenticate(request.getEmail(),request.getPassword());

        // Generate the JWT Token
        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        final String jwtToken = jwtUtil.generateJwtToken(userDetails);

        // Get Role
        final String role = userService.getUserRole(request.getEmail());

        return new AuthResponse(request.getEmail(),jwtToken, role);
    }

    private void authenticate(String email, String password) {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email,password));
        }catch (DisabledException ignored){
            throw new DisabledException("User account is disabled.");
        }catch (BadCredentialsException ignored){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Invalid credentials.");
        }
    }
}
