package in.kodder.journalApp.controller;

import in.kodder.journalApp.entity.UserEntity;
import in.kodder.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService userService;

    @GetMapping("/health-check")
    public String healthCheck() {
        return "OK";
    }

    @PostMapping("/create-user")
    public void createUser(@RequestBody UserEntity user) {
        userService.saveEntry(user);
    }
}
