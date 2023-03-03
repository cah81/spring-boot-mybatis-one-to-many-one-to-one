package com.knf.dev.demo.controller;

import com.knf.dev.demo.exception.ResourceNotFoundException;
import com.knf.dev.demo.model.Card;
import com.knf.dev.demo.model.User;
import com.knf.dev.demo.repository.CardRepository;
import com.knf.dev.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
public class UserController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    CardRepository cardRepository;

    @GetMapping("users/{email}")
    public User getUserById(@PathVariable String email)
     {

         User user = userRepository.selectUserById(email);

         if(user==null){
             throw  new ResourceNotFoundException("User not exist with email : " + email);
         }

         return user;
     }

    @GetMapping("cards/{id}")
    public Card getCardById(@PathVariable Integer id)
    {
        return cardRepository.selectCardById(id);
    }

    @PostMapping("/users")
    public Map<String,Boolean> createUser(@RequestBody User user){
        Map<String,Boolean> response = new HashMap<>();
        Boolean bool = userRepository.insert(user) > 0?
                response.put("created",Boolean.TRUE): response.put("created",Boolean.FALSE);
        return response;
    }

    @PostMapping("/cards")
    public Map<String,Boolean> createCard(@RequestBody Card card){
        Map<String,Boolean> response = new HashMap<>();
        Boolean bool = cardRepository.insert(card) > 0?
                response.put("created",Boolean.TRUE): response.put("created",Boolean.FALSE);
        return response;
    }
    @PutMapping("/users/{id}")
    public Map<String,Boolean> updateUser(@PathVariable Integer id,@RequestBody User userDetails){
        User user = userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User not exist with id : " + id));
        //userDetails.set(id);
        Map<String,Boolean> response = new HashMap<>();
        Boolean bool = userRepository.update(userDetails)>0?
                response.put("updated",Boolean.TRUE):
                response.put("updated",Boolean.FALSE);
        return response;
    }
    @GetMapping("/cards")
    public List<Card> getAllCards(){
        return cardRepository.getCards();
    }

}
