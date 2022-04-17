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
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "user-create";
    }
    @PostMapping("/user-create")
    public String createUser(@ModelAttribute("user") User user) {
        userServis.addUser(user);
        return "redirect:/users";
    }
    @GetMapping("/user-update/{id}")
    public String update(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userServis.getUserById(id));
        return "/user-update";
    }
    @PatchMapping("/user-update/{id}")
    public String updateUser(@ModelAttribute("user") User user, @PathVariable("id") int id) {
        userServis.updateUser(user);
        return "redirect:/users";
    }
    @DeleteMapping("user-delete/{id}")
    public String delete(@PathVariable("id") int id) {
        userServis.removeUserById(id);
        return "redirect:/users";
    }
}
