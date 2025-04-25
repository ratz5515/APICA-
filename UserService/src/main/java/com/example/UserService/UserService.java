package com.example.UserService;

import com.example.userservice.model.User;
import com.example.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private static final String TOPIC = "user-events";

    public User registerUser(User user) {
        User savedUser = userRepository.save(user);
        kafkaTemplate.send(TOPIC, "User registered: " + user.getUsername());
        return savedUser;
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User updateUser(Long id, User updatedUser) {
        updatedUser.setId(id);
        User user = userRepository.save(updatedUser);
        kafkaTemplate.send(TOPIC, "User updated: " + updatedUser.getUsername());
        return user;
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
        kafkaTemplate.send(TOPIC, "User deleted: ID " + id);
    }
}
