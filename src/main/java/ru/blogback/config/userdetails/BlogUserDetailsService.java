package ru.blogback.config.userdetails;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@RequiredArgsConstructor
public class BlogUserDetailsService implements UserDetailsService {
    private static final String QUERY = "select u.username, u.pass_hash as password, a.role " +
            "from authorities a inner join users u on u.username=a.username " +
            "where u.username=:username";

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        BlogUser user = jdbcTemplate.query(
                QUERY,
                new MapSqlParameterSource("username", username),
                rs -> {
                    rs.next();
                    return new BlogUser(
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("role")
                );}
        );
        return new User(user.getUsername(), user.getPassword(), user.getAuthorities());
    }
}
