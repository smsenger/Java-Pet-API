package com.javaworkshop.book.manager;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PetController {

    private PetRepository repository;

    public PetController(PetRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/pets")
    public List<Pet> getAll() {
        return repository.findAll();
    }

    @GetMapping("/pets/{id}")
    public Pet findOne(@PathVariable Long id) {
        return repository.findById(id).orElseThrow();
    }

    @PostMapping("/pets")
    public Pet create(@RequestBody Pet pet) {
        return repository.save(pet);
    }

    @PutMapping("/pets/{id}")
    public Pet updateOne(@RequestBody Pet newData, @PathVariable Long id) {
        return this.repository.findById(id).map(pet -> {
            pet.setBreed(newData.getBreed());
            pet.setName(newData.getName());
            pet.setWeight(newData.getWeight());
            return this.repository.save(pet);
        }).orElseThrow();
    }

    @DeleteMapping("/pets/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }

}
