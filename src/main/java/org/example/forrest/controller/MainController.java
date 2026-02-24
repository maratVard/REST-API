package org.example.forrest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.example.forrest.DTO.CatDTO;
import org.example.forrest.entity.Cat;
import org.example.forrest.repository.CatRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "main_methods")
@Slf4j
@RestController
@RequiredArgsConstructor
public class MainController {
    private final CatRepo catRepo;

    @Operation(summary = "Кладет нового кота в базу" ,
               description = "Получает DTO и с помощью билдера собирает кота и помкщает в БД")
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

    @Operation(summary = "Создает нового кота",
            description = "Принимает объект Cat в формате JSON и сохраняет его в БД")
    @PostMapping("/api/add")
    public void addCat(@RequestBody CatDTO catDTO) {
        log.info("New row: ", catRepo.save(
                Cat.builder()
                        .age(catDTO.getAge())
                        .weight(catDTO.getWeight())
                        .name(catDTO.getName())
                        .build()));
    }
}
