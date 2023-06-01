package pi.dev.realestate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pi.dev.realestate.entities.UserEntity;
import pi.dev.realestate.services.interfaces.IUserService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    IUserService iUserService;

    @PostMapping("/add")
    public UserEntity addUser (@RequestBody UserEntity userEntity) {
        return iUserService.addUser(userEntity);
    }
}
