//package com.kitex.kitex.filter;
//
//import com.kitex.kitex.auth.JpaUserDetailsService;
//import com.kitex.kitex.auth.TokenService;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebFilter;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.AllArgsConstructor;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.oauth2.jwt.Jwt;
//import org.springframework.security.oauth2.jwt.JwtException;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//
//
//@AllArgsConstructor
//@Component
//public class JwtRequestFilter extends OncePerRequestFilter {
//
//    private final TokenService tokenService;
//    private final JpaUserDetailsService jpaUserDetailsService;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        final String header = request.getHeader(org.springframework.http.HttpHeaders.AUTHORIZATION);
//
//        if (header == null || !header.startsWith("Bearer ")) {
//            filterChain.doFilter(request, response);
//            return;
//        }
//        final String token = header.substring(7);
//        try {
//            Jwt jwtInstance = this.tokenService.valiateJwt(token);
//            String email = jwtInstance.getSubject();
//
//            final UserDetails userDetails = jpaUserDetailsService.loadUserByUsername(email);
//
//            final UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//            filterChain.doFilter(request, response);
//
//        } catch (JwtException e) {
//            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
//        }
//
//        filterChain.doFilter(request, response);
//
//    }
//
//
//}
