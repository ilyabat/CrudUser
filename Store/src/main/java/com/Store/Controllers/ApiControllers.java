package com.Store.Controllers;

import com.Store.Models.User;
import com.Store.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ApiControllers {
    @Autowired
    private UserRepo repo;

    @GetMapping(value = "/")
    public String getPage(){
        return "Hi";
    }

    @GetMapping(value = "/user")
    public List<User> getUser(){
        return repo.findAll();
    }
    @PostMapping(value = "/save")
    public String saveUser(@RequestBody User user){
        repo.save(user);
        return "Saved";
    }
    @PutMapping(value = "update/{id}")
    public String updateUser(@PathVariable long id, @RequestBody User user){
        User updatedUser = repo.findById(id).get();
        updatedUser.setFirstName(user.getFirstName());
        updatedUser.setLastName(user.getLastName());
        updatedUser.setAge(user.getAge());
        updatedUser.setOccupation(user.getOccupation());
        repo.save(updatedUser);
        return "Updated";
    }
    @DeleteMapping(value = "delete/{id}")
    public String deleteUser(@PathVariable long id){
        User deleteUser = repo.findById(id).get();
        repo.delete(deleteUser);
        return "User deleted";
    }
}
