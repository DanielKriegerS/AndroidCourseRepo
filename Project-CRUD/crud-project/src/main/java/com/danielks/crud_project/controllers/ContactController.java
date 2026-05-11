package com.danielks.crud_project.controllers;


import com.danielks.crud_project.entities.AddressEmbeddable;
import com.danielks.crud_project.entities.ContactEntity;
import com.danielks.crud_project.entities.dtos.AddressRequest;
import com.danielks.crud_project.entities.dtos.ContactRequest;
import com.danielks.crud_project.repositories.ContactRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    private final ContactRepository repository;

    public ContactController(ContactRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<ContactEntity> list() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ContactEntity get(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contact not found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ContactEntity create(@RequestBody @Valid ContactRequest request) {
        ContactEntity entity = new ContactEntity();
        entity.setName(request.name());
        entity.setEmail(request.email());
        entity.setPhone(request.phone());
        entity.setBirthday(request.birthday());

        if (request.address() != null) {
            entity.setAddress(toEmbeddable(request.address()));
        } else {
            entity.setAddress(null);
        }

        return repository.save(entity);
    }

    @PutMapping("/{id}")
    public ContactEntity update(@PathVariable Long id, @RequestBody @Valid ContactRequest request) {
        ContactEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contact not found"));

        entity.setName(request.name());
        entity.setEmail(request.email());
        entity.setPhone(request.phone());
        entity.setBirthday(request.birthday());

        if (request.address() != null) {
            entity.setAddress(toEmbeddable(request.address()));
        } else {
            entity.setAddress(null);
        }

        return repository.save(entity);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }

    private AddressEmbeddable toEmbeddable(AddressRequest req) {
        if (req == null) return null;

        AddressEmbeddable a = new AddressEmbeddable();
        a.setZipCode(req.zipCode());
        a.setStreet(req.street());
        a.setNeighborhood(req.neighborhood());
        a.setCity(req.city());
        a.setState(req.state());
        a.setNumber(req.number());
        return a;
    }
}

