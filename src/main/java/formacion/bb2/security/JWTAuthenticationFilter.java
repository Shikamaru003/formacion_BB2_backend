package formacion.bb2.security;

import com.google.gson.Gson;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.stream.Collectors;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        setFilterProcessesUrl("/api/auth/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String authHeader = request.getHeader(SecurityConstants.HEADER_STRING);
        String encodeUserPassword = new String(Base64.decodeBase64(authHeader.substring("Basic ".length()).trim()));
        String[] userPassword = encodeUserPassword.split(":");
        String username = userPassword[0];
        String password = userPassword[1];
        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication auth) throws IOException {
        String token = Jwts.builder()
                .setIssuedAt(new Date())
                .setSubject(((User) auth.getPrincipal()).getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SecurityConstants.SECRET)
                .compact();
        response.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + token);

        final Gson gson = new Gson();
        final String rolesJson = gson.toJson(((User) auth.getPrincipal()).getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));
        response.getWriter().write(rolesJson);

    }

}

