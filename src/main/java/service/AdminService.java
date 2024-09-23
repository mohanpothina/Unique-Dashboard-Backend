package service;

import model.Admin;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import repository.AdminRepository;
import utils.JwtUtil;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;


@Service
public class AdminService implements UserDetailsService {
    private final AdminRepository adminRepository;
    private final JwtUtil jwtUtil;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Value("${jwt.secret}")
    private String secretKey;  // Fetch from environment/application.properties

    public AdminService(AdminRepository adminRepository, JwtUtil jwtUtil) {
        this.adminRepository = adminRepository;
        this.jwtUtil = jwtUtil;
    }

    public Admin authenticate(String username, String password) {
        Admin admin = adminRepository.findByUsername(username);
        if (admin != null && passwordEncoder.matches(password, admin.getPassword())) {
            return admin;
        }
        return null;
    }

    public String generateToken(Admin admin) {
        return jwtUtil.generateToken(admin.getUsername());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = adminRepository.findByUsername(username);

        if (admin == null) {
            throw new UsernameNotFoundException("Admin not found with username: " + username);
        }

        return new org.springframework.security.core.userdetails.User(admin.getUsername(), admin.getPassword(),
                new ArrayList<>());  // If you have roles, you can set them here
    }
}


//@Service
//public class AdminService {
//    private final AdminRepository adminRepository;
//
//    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//
//
//    private String secretKey = "VevacloudUniqueagrisciences#2024@MV*";
//
//    public AdminService(AdminRepository adminRepository) {
//        this.adminRepository = adminRepository;
//    }
//
//    public Admin authenticate(String username, String password) {
//        Optional<Admin> admin = Optional.ofNullable(adminRepository.findByUsername(username));
//        if (admin.isPresent() && verifyPassword(password, admin.get().getPassword())) {
//            return admin.get();
//        }
//        return null;
//    }
//
//    public String generateToken(Admin admin) {
//        return Jwts.builder()
//                .setSubject(admin.getUsername())
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // Token expiration time (1 hour)
//                .signWith(SignatureAlgorithm.HS256, secretKey)
//                .compact();
//    }
//
//    public boolean validateToken(String token, String username) {
//        Claims claims = Jwts.parser()
//                .setSigningKey(secretKey)
//                .parseClaimsJws(token)
//                .getBody();
//        return (username.equals(claims.getSubject()) && !isTokenExpired(claims));
//    }
//
//    private boolean isTokenExpired(Claims claims) {
//        return claims.getExpiration().before(new Date());
//    }
//
//    private boolean verifyPassword(String rawPassword, String encodedPassword) {
//        // Implement password verification (e.g., using BCrypt for hashed passwords)
//        return  passwordEncoder.matches(rawPassword, encodedPassword);
//    }
//
//}
