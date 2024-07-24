package in.kodder.journalApp.controller;

import in.kodder.journalApp.entity.UserEntity;
import in.kodder.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserEntity>> getAll() {
        List<UserEntity> all = userService.getAll();

        if (all != null && !all.isEmpty()) {
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public void createUser(@RequestBody UserEntity user) {
        userService.saveEntry(user);
    }

    @PutMapping("/{username}")
    public ResponseEntity<UserEntity> updateUser(@RequestBody UserEntity user, @PathVariable String username) {
        System.out.println("Updating user with username: " + username);

        UserEntity userInDb = userService.findByUserName(username);
        if (userInDb != null) {
            System.out.println("User found: " + userInDb);

            userInDb.setUsername(user.getUsername());
            userInDb.setPassword(user.getPassword());

            System.out.println("Updated user: " + userInDb);

            userService.saveEntry(userInDb);
            return new ResponseEntity<>(userInDb, HttpStatus.OK);
        }
        System.out.println("User not found");
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}




