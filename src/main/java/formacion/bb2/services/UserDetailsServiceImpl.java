package formacion.bb2.services;

import formacion.bb2.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    protected UserService userService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) {
        User user = userService.findUserByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User Not Found with username: " + username);
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().name()));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), true, true, true, true, authorities);
    }
}