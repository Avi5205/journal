package in.kodder.journalApp.service;

import in.kodder.journalApp.entity.UserEntity;
import in.kodder.journalApp.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@Component
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<UserEntity> findById(ObjectId id) {
        return userRepository.findById(id);
    }

    public void saveEntry(UserEntity user) {
        userRepository.save(user);
    }

    public List<UserEntity> getAll() {
        return userRepository.findAll();
    }

    public void deleteById(ObjectId id) {
        userRepository.deleteById(id);
    }

    public UserEntity findByUserName(String username) {
        return userRepository.findByUsername(username);
    }

    public void saveUser(UserEntity user) {
        userRepository.save(user);
    }
}