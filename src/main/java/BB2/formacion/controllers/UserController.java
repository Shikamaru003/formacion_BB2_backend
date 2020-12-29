package BB2.formacion.controllers;

import BB2.formacion.models.User;
import BB2.formacion.security.jwt.response.MessageResponse;
import BB2.formacion.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder encoder;

    @GetMapping("users/all")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("users")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Page<User> getUsers(@RequestParam Integer page, @RequestParam Integer size, @RequestParam String sortField, @RequestParam Integer sortOrder) {
        Pageable pageable;
        if (sortField.isEmpty()) {
            pageable = PageRequest.of(page, size);
        } else {
            if (sortOrder == 1) {
                pageable = PageRequest.of(page, size, Sort.by(sortField).ascending());
            } else {
                pageable = PageRequest.of(page, size, Sort.by(sortField).descending());
            }
        }
        Page<User> users = userService.getUsers(pageable);
        return users;
    }

    @GetMapping("users/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<User> findUserById(@PathVariable Long id) {
        Optional<User> optional = userService.findUserById(id);
        User user = optional.isPresent() ? optional.get() : null;
        return new ResponseEntity(user, HttpStatus.OK);
    }

    @PostMapping("users")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<MessageResponse> newUser(@RequestBody User user) {

        if (Boolean.TRUE.equals(userService.existsByUsername(user.getUsername()))) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
        }

        user.setPassword(encoder.encode(user.getPassword()));

        User newUser = userService.saveUser(user);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @PutMapping("users/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        User updatedUser = userService.updateUser(user);
        return new ResponseEntity<User>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("users/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}
