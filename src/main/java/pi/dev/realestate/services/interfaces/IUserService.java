package pi.dev.realestate.services.interfaces;

import pi.dev.realestate.entities.UserEntity;

import java.util.List;

public interface IUserService {
    UserEntity addUser(UserEntity userEntity);


    UserEntity activeUser(int id);

    List<UserEntity> getAlUser();

    UserEntity getUser(int id);
}
