package lk.earth.earthuniversity.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import lk.earth.earthuniversity.entity.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter{

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserService userService;


    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil, UserService userService) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        setFilterProcessesUrl("/login"); // Set the login endpoint
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {

            //System.out.println("haha");
            //System.out.println(request.getInputStream());
            LoginRequest loginRequest = new ObjectMapper().readValue(request.getInputStream(), LoginRequest.class);
//            System.out.println("00000000000000000000");
//            System.out.println(loginRequest.getUsername()+"---"+loginRequest.getPassword());


            //System.out.println(loginRequest.getUsername()+"---"+loginRequest.getPassword());

            User user = userService.getByUsername(loginRequest.getUsername());

            String hashedPassword = user.getSalt() + loginRequest.getPassword();

            //System.out.println(user.getUsername()+"----"+user.getSalt()+"-----"+user.getPassword());

            Authentication auth = authenticationManager.authenticate( new UsernamePasswordAuthenticationToken( loginRequest.getUsername(), hashedPassword ) );

//            System.out.println("Authentication Failed/Not");
//            System.out.println(auth.isAuthenticated());
            return auth;

        } catch (IOException e) {
            throw new RuntimeException("Failed to parse login request", e);
        }
        catch (AuthenticationException e2) {
            throw new RuntimeException("Failed "+e2.getMessage());
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
//        System.out.println("baba");
        UserDetails userDetails = (UserDetails) authResult.getPrincipal();
        String token = jwtTokenUtil.generateToken(userDetails);
        response.addHeader("Authorization", "Bearer " + token);
        response.setHeader("Access-Control-Expose-Headers", "Authorization");
    }

}














//
//public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
//
//    private final AuthenticationManager authenticationManager;
//    private final JwtTokenUtil jwtTokenUtil;
//    private final UserService userService;
//
//    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil, UserService userService) {
//        this.authenticationManager = authenticationManager;
//        this.jwtTokenUtil = jwtTokenUtil;
//        this.userService = userService;
//        setFilterProcessesUrl("/login"); // Set the login endpoint
//    }
//
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
//        try {
//            LoginRequest loginRequest = new ObjectMapper().readValue(request.getInputStream(), LoginRequest.class);
//            User user = userService.getByUsername(loginRequest.getUsername());
//
//            if (user == null) {
//                throw new UsernameNotFoundException("User not found");
//            }
//
//            System.out.println(user.getUsername()+"----"+user.getSalt()+"-----"+user.getPassword());
//            String hashedPassword = user.getSalt() + loginRequest.getPassword();
//
//            return authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), hashedPassword)
//            );
//        } catch (IOException e) {
//            throw new RuntimeException("Failed to parse login request", e);
//        }
//    }
//
//    @Override
//    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
//        UserDetails userDetails = (UserDetails) authResult.getPrincipal();
//        String token = jwtTokenUtil.generateToken(userDetails);
//        response.addHeader("Authorization", "Bearer " + token);
//        response.setHeader("Access-Control-Expose-Headers", "Authorization");
//    }
//}
