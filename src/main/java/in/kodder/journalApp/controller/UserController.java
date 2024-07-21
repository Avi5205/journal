package in.kodder.journalApp.controller;

import in.kodder.journalApp.entity.UserEntity;
import in.kodder.journalApp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

    @PutMapping("/{username}")
    public ResponseEntity<UserEntity> updateUser(@RequestBody @Valid UserEntity user, @PathVariable String username) {
        System.out.println("Updating user with username: " + username);

        UserEntity userInDb = userService.findByUserName(username);
        if (userInDb!= null) {
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

//    @PutMapping("/{username}")
//    public ResponseEntity<UserEntity> updateUser(@RequestBody @Valid UserEntity user, @PathVariable String username) {
//        if (!username.equals(user.getUsername())) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//
//        UserEntity userInDb = userService.findByUserName(username);
//        if (userInDb != null) {
//            userInDb.setUsername(user.getUsername());
//            userInDb.setPassword(user.getPassword());
//            userService.saveEntry(userInDb);
//            return new ResponseEntity<>(userInDb, HttpStatus.OK);
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }

    @PostMapping
    public ResponseEntity<UserEntity> createUser(@RequestBody @Valid UserEntity userEntity) {
        try {
            userService.saveEntry(userEntity);
            return new ResponseEntity<>(userEntity, HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

//    @GetMapping("/id/{myId}")
//    public ResponseEntity<UserEntity> getUserEntryById(@PathVariable ObjectId myId) {
//        Optional<UserEntity> userEntry = UserService.findById(myId);
//        if (userEntry.isPresent()) {
//            return new ResponseEntity<>(userEntry.get(), HttpStatus.OK);
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }

//    @DeleteMapping("/id/{myId}")
//    public ResponseEntity<?> deleteUserById(@PathVariable ObjectId myId) {
//        userService.deleteById(myId);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }



