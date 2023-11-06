package lk.earth.earthuniversity.controller;

import lk.earth.earthuniversity.dao.UserDao;
import lk.earth.earthuniversity.entity.Privilage;
import lk.earth.earthuniversity.entity.User;
import lk.earth.earthuniversity.entity.Userrole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/authorities")
public class UserAuthorityController {

    @Autowired
    private UserDao userdao;

    @GetMapping("/{username}")
    @ResponseStatus(HttpStatus.CREATED)
    public List<String> getUserAuthoritiesByUsername(@PathVariable String username) {
        User user = userdao.findByUsername(username);
        List<Userrole> userroles = (List<Userrole>) user.getUserroles();
        List<String> authorities = new ArrayList<>();

        for (Userrole u : userroles) {
            List<Privilage> privilages = (List<Privilage>) u.getRole().getPrivilages();
            for (Privilage p : privilages) {
                String authority = p.getAuthority();
                authorities.add(authority);
            }
        }

        return authorities;
    }


}
