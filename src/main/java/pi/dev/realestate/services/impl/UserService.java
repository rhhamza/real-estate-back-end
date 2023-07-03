package pi.dev.realestate.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pi.dev.realestate.entities.UserEntity;
import pi.dev.realestate.repositories.UserRepository;
import pi.dev.realestate.services.interfaces.IUserService;

import java.util.List;
@Service
public class UserService implements IUserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserEntity addUser(UserEntity userEntity){
        userRepository.save(userEntity);
        return userEntity;
    }

    @Override
    public List<UserEntity> getAlUser(){
        return userRepository.findAll();
    }
    @Override
    public UserEntity getUser(int id){
        return userRepository.findById(id).orElse(null);
    }
}
