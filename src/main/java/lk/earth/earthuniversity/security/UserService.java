package lk.earth.earthuniversity.security;

import lk.earth.earthuniversity.dao.UserDao;
import lk.earth.earthuniversity.entity.Privilage;
import lk.earth.earthuniversity.entity.Userrole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import  lk.earth.earthuniversity.entity.User;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {


    final UserDao userdao;

    @Autowired
    public UserService(UserDao userdao) {
        this.userdao = userdao;
    }

    public User getByUsername(String username){
        User user = userdao.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return user;
    }

/*
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userdao.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        Set<SimpleGrantedAuthority> authorities=new HashSet<>();

        List<Userrole> userroles = (List<Userrole>) user.getUserroles();
        for(Userrole u : userroles){
              List<Privilage> privilages = (List<Privilage>) u.getRole().getPrivilages();
              for (Privilage p:privilages){
                  authorities.add(new SimpleGrantedAuthority(p.getAuthority()));
              }
        }


        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities(authorities)
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }

    */


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Check if the username exists in the in-memory user list
        if ("dilee".equals(username)) {
            // Simulating user roles and authorities for the "asha" user
            Set<SimpleGrantedAuthority> authorities = new HashSet<>();
            authorities.add(new SimpleGrantedAuthority("gender-list-get"));
            authorities.add(new SimpleGrantedAuthority("designation-list-get"));
            authorities.add(new SimpleGrantedAuthority("employeestatus-list-get"));
            authorities.add(new SimpleGrantedAuthority("employee-post"));

            // Create a UserDetails object with the user's details
            return org.springframework.security.core.userdetails.User
                    .withUsername("dilee")
                    .password("dileesha")
                    .authorities(authorities)
                    .accountExpired(false)
                    .accountLocked(false)
                    .credentialsExpired(false)
                    .disabled(false)
                    .build();

            // If the username is not found, throw an exception
           // throw new UsernameNotFoundException("User not found with username: " + username);
        }
        else {

//            System.out.println("ooooooooooooo----"+username);

            User user = userdao.findByUsername(username);
            if (user == null) {
                throw new UsernameNotFoundException("User not found with username: " + username);
            }

            Set<SimpleGrantedAuthority> authorities=new HashSet<>();

//            System.out.println("11111111111111");

            List<Userrole> userroles = (List<Userrole>) user.getUserroles();

//            System.out.println(userroles==null);
//            System.out.println("22222222222222");
//            System.out.println(userroles.isEmpty());


            for(Userrole u : userroles){
//                System.out.println("3333333333333333");
                List<Privilage> privilages = (List<Privilage>) u.getRole().getPrivilages();
//                System.out.println("44444444444444444");
                for (Privilage p:privilages){
//                    System.out.println("bbbbbbbbbbbb----"+p.getAuthority());
                    authorities.add(new SimpleGrantedAuthority(p.getAuthority()));
                }
            }


            return org.springframework.security.core.userdetails.User
                    .withUsername(user.getUsername())
                    .password(user.getPassword())
                    .authorities(authorities)
                    .accountExpired(false)
                    .accountLocked(false)
                    .credentialsExpired(false)
                    .disabled(false)
                    .build();
        }


    }

}







