package co.com.nisum.config.security;

import co.com.nisum.api.common.util.TokenUtils;
import co.com.nisum.model.common.Constant;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class JWTAuthorizationFilter extends OncePerRequestFilter {
    private final TokenUtils tokenUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader(Constant.AUTHORIZATION_HEADER.getValue());
        if (Objects.nonNull(token) && token.startsWith(Constant.TOKEN_PREFIX.getValue())) {
            token = token.replace(Constant.TOKEN_PREFIX.getValue(), Constant.EMPTY_STRING.getValue());
            UsernamePasswordAuthenticationToken authToken = tokenUtils.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }
        filterChain.doFilter(request, response);
    }
}
