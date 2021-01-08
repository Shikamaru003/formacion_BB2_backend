package formacion.bb2.controllers;

import formacion.bb2.dtos.UserDto;
import formacion.bb2.models.User;
import formacion.bb2.services.UserService;
import formacion.bb2.utils.DTOModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder encoder;

    @GetMapping("users/all")
    @Secured("ROLE_ADMIN")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("users")
    @Secured("ROLE_ADMIN")
    public Page<UserDto> getUsers(@RequestParam Integer page, @RequestParam Integer size, @RequestParam String sortField, @RequestParam Integer sortOrder) {
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

        Page<User> usersPage = userService.getUsers(pageable);
        List<UserDto> users = DTOModelMapper.mapList(usersPage.getContent(), UserDto.class);
        return new PageImpl<>(users, pageable, usersPage.getTotalElements());
    }

    @GetMapping("users/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<UserDto> findUserById(@PathVariable Long id) {
        User user = userService.findUserById(id);
        return new ResponseEntity<>(DTOModelMapper.map(user, UserDto.class), HttpStatus.OK);
    }

    @PostMapping("users")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<String> newUser(@RequestBody UserDto userDto) {
        User user = DTOModelMapper.map(userDto, User.class);

        if (Boolean.TRUE.equals(userService.existsByUsername(user.getUsername()))) {
            return ResponseEntity.badRequest().body("Error: Username is already taken!");
        }

        user.setPassword(encoder.encode(user.getPassword()));
        userService.saveUser(user);
        return ResponseEntity.ok("User registered successfully!");
    }

    @PutMapping("users/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto) {
        User user = DTOModelMapper.map(userDto, User.class);
        User updatedUser = userService.updateUser(user);
        return new ResponseEntity<>(DTOModelMapper.map(updatedUser, UserDto.class), HttpStatus.OK);
    }

    @DeleteMapping("users/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}
