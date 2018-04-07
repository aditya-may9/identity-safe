package com.identity.controller;

import com.identity.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CAController {

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity< String > persistPerson(@RequestBody User person) {

        System.out.println("Called");
        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();
    }
}
