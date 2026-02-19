package org.example.forrest.controller;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.example.forrest.entity.Cat;
import org.example.forrest.repository.CatRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

@Slf4j
@RestController
//@RequiredArgsConstructor
public class MainController {
    private final CatRepo catRepo;
    private final ObjectMapper objectMapper;

    @Autowired
    public MainController(CatRepo catRepo, ObjectMapper objectMapper) {
        this.catRepo = catRepo;
        this.objectMapper = objectMapper;
    }

    @PostMapping("/api/add")
    public void addCat(@RequestBody Cat cat) {
            //log.info("New row:" +
        catRepo.save(cat);
    }

    @SneakyThrows
    @GetMapping("/api/all")
    public List<Cat> getAllCats() {
        return catRepo.findAll();
    }

    @GetMapping("/api")
    public Cat getCat(@RequestParam int id) {
        return catRepo.findById(id).orElseThrow();
    }

    @DeleteMapping("/api")
    public void deleteCat(@RequestParam int id) {
        catRepo.deleteById(id);
    }

    @PutMapping("/api/add")
    public String changeCat(@RequestBody Cat cat) {
        if (!catRepo.existsById(cat.getId())) {
            return "No such row";
        }
        return catRepo.save(cat).toString();
    }
}
