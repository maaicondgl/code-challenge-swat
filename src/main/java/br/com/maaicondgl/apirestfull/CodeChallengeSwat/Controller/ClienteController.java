package br.com.maaicondgl.apirestfull.CodeChallengeSwat.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import br.com.maaicondgl.apirestfull.CodeChallengeSwat.Exceptions.ResourceNotFoundException;
import br.com.maaicondgl.apirestfull.CodeChallengeSwat.Model.ClienteEntity;
import br.com.maaicondgl.apirestfull.CodeChallengeSwat.Model.ContaBancariaEntity;
import br.com.maaicondgl.apirestfull.CodeChallengeSwat.Service.ClienteService;
import br.com.maaicondgl.apirestfull.CodeChallengeSwat.Service.ContaBancariaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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
    private ClienteService clienteService;

    ContaBancariaEntity contaBancaria;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ClienteEntity> findAll() {
        return clienteService.customerList();
    }

    @GetMapping(value = "/consultacpf/{cpf}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> searchCustomer(@PathVariable String cpf) {
        try {
            // CPF do cliente é usado como ID
            Optional<ClienteEntity> opcaoCpf = clienteService.searchCustomer(cpf);

            if (opcaoCpf.isPresent()) {
                // Obtém a conta bancária do cliente
                ContaBancariaEntity contaBancaria = opcaoCpf.get().getContaBancaria();

                if (contaBancaria != null) {
                    //Obtém valor do Cheque Especial true || false
                    boolean valorChequeEspecial = contaBancaria.getChequeEspecial();
                    String mensagem;

                    // Verifica o valor do cheque especial
                    if (valorChequeEspecial) {
                        mensagem = "Cheque especial liberado.";
                    } else {
                        mensagem = "Cheque especial indisponível.";
                    }

                    // Monta o objeto de resposta
                    Map<String, Object> responseData = new HashMap<>();
                    responseData.put("cliente", opcaoCpf.get());
                    responseData.put("mensagem", mensagem);

                    // Converte o mapa em JSON
                    ObjectMapper mapper = new ObjectMapper();
                    String json = mapper.writeValueAsString(responseData);

                    return ResponseEntity.ok().body(json);
                } else {
                    return ResponseEntity.ok().body("Cliente encontrado, mas não há informações de conta bancária.");
                }
            }

            return new ResponseEntity<>("Cliente não encontrado.", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro ao processar a solicitação.");
        }
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
