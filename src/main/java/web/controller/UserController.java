package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import web.dao.UserDAO;
import web.dao.UserDAOImpl;
import web.model.User;
import web.service.UserService;

import javax.transaction.Transactional;
import java.util.List;

@Controller
@Transactional
@RequestMapping("/")
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String getIndex(Model model){
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }

    @GetMapping("/addNewUser")
    public String addNewUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "addNewUser";
    }

    @PostMapping(value = "/addNewUser")
    public String createNewUser(@ModelAttribute("user") User user, BindingResult bindingResult) {

        userService.saveNewUser(user);
        return "redirect:/";
    }

    @GetMapping(value = "{id}")
    public String editUser(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", userService.getUserById(id));
        return "editUser";
    }

    @PatchMapping("{id}")
    public String userUpdate(@PathVariable("id") Long id,@ModelAttribute("user") User user) {
        userService.editUser(user);
        return "redirect:/";
    }

    @DeleteMapping("delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/";
    }


}
