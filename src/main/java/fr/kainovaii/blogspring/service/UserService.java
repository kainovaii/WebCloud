package fr.kainovaii.blogspring.service;

import fr.kainovaii.blogspring.model.User;
import fr.kainovaii.blogspring.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(User user) {
        System.out.println("Mot de passe en clair avant encodage : " + user.getPassword());

        String encodedPassword = passwordEncoder.encode(user.getPassword());

        System.out.println("Mot de passe encodé : " + encodedPassword);

        user.setPassword(encodedPassword);
        user.setEmail("test@test.fr");

        userRepository.save(user);
        System.out.println("Utilisateur enregistré en BDD avec password encodé");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé avec l'username : " + username));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"))
        );
    }
}

