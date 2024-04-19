package com.github.faening.eng_soft_fp_api.controller;

import com.github.faening.eng_soft_fp_api.domain.service.AbstractService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SuppressWarnings("unused")
public abstract class AbstractController<REQ, RES> {
    protected final AbstractService<REQ, RES> service;

    public AbstractController(AbstractService<REQ, RES> service) {
        this.service = service;
    }

    @GetMapping(value = { "" }, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RES>> getAll() {
        List<RES> response = service.getAll();
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = { "/{id}" }, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RES> getById(@PathVariable Integer id) {
        RES response = service.getById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = { "" }, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RES> create(@RequestBody REQ request) {
        RES created = service.create(request);
        return ResponseEntity.ok(created);
    }

    @PatchMapping(value = { "/{id}" }, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RES> update(@PathVariable Integer id, @RequestBody REQ request) {
        RES updated = service.update(id, request);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping(value = { "/{id}" })
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}