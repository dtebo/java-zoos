package com.lambdaschool.javazoos.controllers;

import com.lambdaschool.javazoos.models.Animal;
import com.lambdaschool.javazoos.services.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/animals")
public class AnimalController {
    @Autowired
    AnimalService animalService;

    @GetMapping(value = "/animals")
    public ResponseEntity<?> listAllAnimals(){
        List<Animal> myAnimals = animalService.findAll();

        return new ResponseEntity<>(myAnimals, HttpStatus.OK);
    }

    
}
