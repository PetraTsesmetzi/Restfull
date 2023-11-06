package com.example.myfirstrestapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user")
    public ResponseEntity<User>get(@RequestParam(value="id") int id){
        var user=userRepository.findById(id);
        if(user.isPresent()){
            return new ResponseEntity<User>(user.get(),HttpStatus.OK);
        }
        return new ResponseEntity("No User found with id "+id, HttpStatus.NOT_FOUND);
    }
    @GetMapping("/validate")
    public ResponseEntity<String> validate(@RequestParam(value="email") String email,
                                        @RequestParam(value="password") String password){
      var validUser=userRepository.findByEmailAndPassword(email,password);

      if(validUser.isPresent()){
          return new ResponseEntity<String>("API Secret: "+validUser.get().getSecret(),HttpStatus.OK);
      }
        return new ResponseEntity("Wrong Creditials/No Account found ",HttpStatus.NOT_FOUND);

    }
    @PostMapping("/register")
    private ResponseEntity<User> register(@RequestBody User newUser) {
        //generate secret
        newUser.setSecret(UUID.randomUUID().toString());

        var savedUser = userRepository.save(newUser);
        return new ResponseEntity<User>(newUser, HttpStatus.CREATED);
    }
    @GetMapping("/user/all")
    public ResponseEntity<Iterable<User>>getAll(){
        Iterable<User>allUsersInDb=userRepository.findAll();
        return new ResponseEntity<Iterable<User>>(allUsersInDb,HttpStatus.OK);
    }

    @DeleteMapping("/user")
    public ResponseEntity delete(@RequestParam(value="id")int id){
        Optional<User> userInDb=userRepository.findById(id);
        if(userInDb.isPresent()){
            userRepository.deleteById(id);
            return new ResponseEntity("user deleted",HttpStatus.OK);
        }
        return new ResponseEntity("Not such user found with id: "+id, HttpStatus.NOT_FOUND);
    }
    //nur ein attribut wird verändert im gegensatz zu Put
    @PatchMapping("/user/setPw")
    public ResponseEntity<User>setPw(@RequestParam(value="password")String password,
                                     @RequestParam(value="id") int id){
        Optional<User>userInDB=userRepository.findById(id);
        if(userInDB.isPresent()){
            userInDB.get().setPassword(password);
            User savedUser=userRepository.save(userInDB.get());
            return new ResponseEntity("ok",HttpStatus.OK);
        }
        return new ResponseEntity("No such user found with id: " + id,HttpStatus.NOT_FOUND);
    }

    //hier kann man dan komplette Objekt verändern
    @PutMapping("/user")
    public ResponseEntity<User>edit(@RequestBody User editUser){
        Optional<User>userInDb=userRepository.findById(editUser.getId());
        if(userInDb.isPresent()){
            User savedUser=userRepository.save(editUser);
            return new ResponseEntity<User>(savedUser, HttpStatus.OK);
        }
        return new ResponseEntity("No such user found with id: " + editUser.getId(),HttpStatus.NOT_FOUND);
    }
}
