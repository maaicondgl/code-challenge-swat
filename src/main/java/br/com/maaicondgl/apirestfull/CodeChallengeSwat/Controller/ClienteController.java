package br.com.maaicondgl.apirestfull.CodeChallengeSwat.Controller;

import java.util.List;

import br.com.maaicondgl.apirestfull.CodeChallengeSwat.Model.ClienteEntity;
import br.com.maaicondgl.apirestfull.CodeChallengeSwat.Service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
    @Autowired
    private ClienteService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ClienteEntity> findAll() {
        return service.findAll();
    }

    @GetMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ClienteEntity findById(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ClienteEntity create(@RequestBody ClienteEntity cliente) {
        return service.create(cliente);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ClienteEntity update(@RequestBody ClienteEntity cliente) {
        return service.update(cliente);
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return  ResponseEntity.noContent().build();
    }
}
