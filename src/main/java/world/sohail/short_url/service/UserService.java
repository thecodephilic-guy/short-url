package world.sohail.short_url.service;

import org.springframework.stereotype.Service;
import world.sohail.short_url.dto.UserRequest;
import world.sohail.short_url.entity.User;
import world.sohail.short_url.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User createUser(UserRequest user) {
        String name = user.getName();
        String email = user.getEmail();

        User userEntity = new User(name, email);

        return userRepository.save(userEntity);
    }
}
