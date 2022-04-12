package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.models.User;
import web.servis.UserServis;

@Controller
public class UsersController {
    private final UserServis userServis;

    @Autowired
    public UsersController(UserServis userServis) {
        this.userServis = userServis;
    }

    @GetMapping("/users")
    public String findAll(Model model) {
        model.addAttribute("userList",userServis.getAllUsers());
        return "users-list";
    }
    @GetMapping("/user-create")
    public String createUserForm(User user) {
        return "user-create";
    }
    @PostMapping("/user-create")
    public String createUser(User user) {
        userServis.addUser(user);
        return "redirect:/users";
    }
    @GetMapping("user-delete/{id}")
    public String removeUser(@PathVariable("id") int id) {
        userServis.removeUserById(id);
        return "redirect:/users";
    }
    @GetMapping("/user-update/{id}")
    public String updateUserForm(@PathVariable("id") int id, Model model) {
        User user =userServis.getUserById(id);
        model.addAttribute("user", user);
        return "/user-update";
    }
    @PostMapping("/user-update")
    public String update(User user) {
        userServis.updateUser(user);
        return "redirect:/users";
    }
}
