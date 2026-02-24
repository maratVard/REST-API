package org.example.forrest.controller;

import lombok.RequiredArgsConstructor;
import org.example.forrest.entity.Dog;
import org.example.forrest.repository.DogRepo;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/dogs")
@RequiredArgsConstructor
public class DogController {
    private final DogRepo dogRepo;

    @GetMapping("/all")
    public List<Dog> getAllDogsFromDB() {
        return dogRepo.findAll();
    }

    @GetMapping
    public Dog getDogFromName(@RequestParam String name) {
        return dogRepo.findByName(name);
    }

    @PostMapping
    public Dog putDogIntoDB(@RequestBody Dog dog) {
        dog.setId(UUID.randomUUID());
        return dogRepo.save(dog);
    }
}
