package br.com.maaicondgl.apirestfull.CodeChallengeSwat.Controller;

import java.util.Optional;

import br.com.maaicondgl.apirestfull.CodeChallengeSwat.Exceptions.ResourceNotFoundException;
import br.com.maaicondgl.apirestfull.CodeChallengeSwat.Model.ClienteEntity;
import br.com.maaicondgl.apirestfull.CodeChallengeSwat.Service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
    private ClienteService clienteService;

    @GetMapping("/consultapf/{cpf}")
    public ResponseEntity<?> searchCustomer(@PathVariable String cpf){
        // CPF do cliente é usado como ID
        Optional<ClienteEntity> opcaoCpf = clienteService.searchCustomer(cpf);

        if(opcaoCpf.isPresent()){
            return ResponseEntity.ok(opcaoCpf.get());
        }
        return new ResponseEntity<>("Cliente não encontrado.",HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<ClienteEntity> createCustomer(@RequestBody ClienteEntity cliente){

        return ResponseEntity.ok(clienteService.createCustomer(cliente));
    }
    @PutMapping
    public ResponseEntity updateCustomer(@RequestBody ClienteEntity cliente){

        try{
            return ResponseEntity.ok(clienteService.updateCustomer(cliente));
        } catch(ResourceNotFoundException e){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    @DeleteMapping("/excluir/{cpf}")
    public ResponseEntity<?> excluirClientePorCpf(@PathVariable String cpf) {
        clienteService.deletarClientePorCpf(cpf);
        return ResponseEntity.ok("Cliente excluído com sucesso.");
    }
}
