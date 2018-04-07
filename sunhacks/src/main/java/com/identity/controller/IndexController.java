package com.identity.controller;

import com.identity.pojo.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class IndexController {

    static HashMap<String, User> ca = new HashMap<>();
    static HashMap<String, User> merchants = new HashMap<>();
    static HashMap<String, User> individuals = new HashMap<>();
    //User cau = new User("ca","ca",3);

    @CrossOrigin
    @RequestMapping (value="/register",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity register(@RequestBody User user)
    {
        System.out.println(user.getType());
        String username = user.getUsername();
        if(user.getType().equals("individual")){
            individuals.put(username,user);
        }
        else if(user.getType().equals("merchant")){
            merchants.put(username,user);
        }

        return ResponseEntity.status(HttpStatus.OK).build();
    }
    //authenticate
    @CrossOrigin
    @RequestMapping (value="/login",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity login(@RequestBody User user)
    {
        String username = user.getUsername();
        System.out.println(username);
        System.out.println(user.getType());
        HashMap<String, User> hm = null;
        if(user.getType().equals("individual")){
            hm = individuals;
        }
        else if(user.getType().equals("merchant")){
            hm = merchants;
        }
        else if(user.getType().equals("ca")){
            hm = ca;
        }
        if(hm.containsKey(username)) {
            if(hm.get(username).getPassword().equals(user.getPassword()))
                return ResponseEntity.status(HttpStatus.OK).build();
            else
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
