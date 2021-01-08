package formacion.bb2.services;

import formacion.bb2.models.User;
import formacion.bb2.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    protected UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Page<User> getUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public User findUserById(Long id) {
        Optional<User> optional = userRepository.findById(id);
        return optional.orElse(null);
    }

    public User findUserByUsername(String username) {
        Optional<User> optional = userRepository.findByUsername(username);
        return optional.orElse(null);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public Boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

}

