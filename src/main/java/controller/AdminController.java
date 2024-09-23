package controller;

import model.Admin;
import model.AuthResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import service.AdminService;


@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Admin admin) {
        Admin authenticatedAdmin = adminService.authenticate(admin.getUsername(), admin.getPassword());
        if (authenticatedAdmin != null) {
            String token = adminService.generateToken(authenticatedAdmin);
            return ResponseEntity.ok(new AuthResponse(token));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }
}



//@RestController
//@RequestMapping("/admin")
//public class AdminController {
//
//
//    private final AdminService adminService;
//
//    public AdminController(AdminService adminService) {
//        this.adminService = adminService;
//    }
//
//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody Admin admin) {
//        try {
//            Admin authenticatedAdmin = adminService.authenticate(admin.getUsername(), admin.getPassword());
//            if (authenticatedAdmin != null) {
//                String token = adminService.generateToken(authenticatedAdmin);
//                return ResponseEntity.ok(new AuthResponse(token));
//            } else {
//                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
//            }
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
//        }
//    }
//}
//






    //    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody Admin admin) {
//        try {
//            Admin authenticatedAdmin = adminService.authenticate(admin.getUsername(), admin.getPassword());
//            if (authenticatedAdmin != null) {
//                String token = adminService.generateToken(authenticatedAdmin); // Make sure this method exists
//                return ResponseEntity.ok(new AuthResponse(token)); // Ensure AuthResponse class exists
//            } else {
//                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
//            }
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
//        }
//    }
//    @Autowired
//    private JwtUtil jwtUtil;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;

//    @Autowired
//    private AuthenticationManager authenticationManager;
    //    @Autowired
//    private AdminRepository adminRepository;
//
//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody AdminLoginRequest request) {
//        Admin admin = adminRepository.findByUsername(request.getUsername());
//
//        if (admin == null || !passwordEncoder.matches(request.getPassword(), admin.getPassword())) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
//        }
//
//        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
//
//        String token = jwtUtil.generateToken(request.getUsername());
//
//        return ResponseEntity.ok(new JwtResponse(token));
//    }
