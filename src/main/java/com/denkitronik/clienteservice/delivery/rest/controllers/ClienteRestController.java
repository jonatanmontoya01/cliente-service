package com.denkitronik.clienteservice.delivery.rest.controllers;

import com.denkitronik.clienteservice.domain.entities.Cliente;
import com.denkitronik.clienteservice.domain.entities.Region;
import com.denkitronik.clienteservice.domain.services.IClienteService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/${api.version}/cliente-service")
public class ClienteRestController {

    @Autowired
    private IClienteService clienteService;

    @GetMapping("/clientes")
    public List<Cliente> index() {
        return clienteService.findAll();
    }

    @GetMapping("/clientes/page/{page}")
    public Page<Cliente> index(@PathVariable Integer page) {
        return clienteService.findAll(PageRequest.of(page, 4));
    }

    @GetMapping("/clientes/{id}")
    public ResponseEntity<Cliente> show(@PathVariable Long id) {
        return ResponseEntity.ok(clienteService.findById(id));
    }

    @PostMapping("/clientes")
    public ResponseEntity<Cliente> create(@Valid @RequestBody Cliente cliente) {
        return new ResponseEntity<>(clienteService.save(cliente), HttpStatus.CREATED);
    }

    @PutMapping("/clientes/{id}")
    public ResponseEntity<Cliente> update(@Valid @RequestBody Cliente cliente, @PathVariable Long id) {
        return new ResponseEntity<>(clienteService.update(id, cliente), HttpStatus.CREATED);
    }

    @DeleteMapping("/clientes/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/regiones")
    public List<Region> listarRegiones() {
        return clienteService.findAllRegiones();
    }
}