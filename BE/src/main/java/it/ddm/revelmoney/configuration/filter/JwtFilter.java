package it.ddm.revelmoney.configuration.filter;

import io.jsonwebtoken.JwtException;
import it.ddm.revelmoney.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.GenericFilter;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

@Component
public class JwtFilter extends GenericFilter {

    private final JwtUtil jwtUtil;
    private final UserDetailsService customerDetailsService;

    public JwtFilter(final JwtUtil jwtUtil, final UserDetailsService customerDetailsService) {
        this.jwtUtil = jwtUtil;
        this.customerDetailsService = customerDetailsService;
    }

    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain)
            throws IOException,
            ServletException {
        HttpServletRequest httpReq = (HttpServletRequest) request;
        String header = httpReq.getHeader(HttpHeaders.AUTHORIZATION);
        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            try {
                String username = jwtUtil.extractUsername(token);
                UserDetails userDetails = customerDetailsService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpReq));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            } catch (JwtException e) {
                ((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid JWT");
                return;
            }
        }
        chain.doFilter(request, response);
    }
}
