package ru.blogback.service;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;
import ru.blogback.entity.User;
import ru.blogback.repository.UserRepository;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EntryService {
    private UserRepository userRepository;

    private AuthenticationManager authenticationManager;
    private PasswordEncoder passwordEncoder;
    private JwtEncoder jwtEncoder;

    public String login(User user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPasswordHash())
        );
        var authorities = authentication.getAuthorities()
                .stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));
        Instant createdAt = Instant.now();
        Instant expiredAt = createdAt.plus(30, ChronoUnit.MINUTES);

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("example.com")
                .issuedAt(createdAt)
                .expiresAt(expiredAt)
                .subject(user.getUsername())
                .claim("roles", authorities)
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    public User register(User user) {
        user.setPasswordHash(passwordEncoder.encode(user.getPasswordHash()));
        return userRepository.save(user);
    }
}
