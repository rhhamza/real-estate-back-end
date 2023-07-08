package pi.dev.realestate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
