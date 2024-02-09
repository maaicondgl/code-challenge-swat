package br.com.maaicondgl.apirestfull.CodeChallengeSwat.Controller;

import br.com.maaicondgl.apirestfull.CodeChallengeSwat.Exceptions.ResourceNotFoundException;
import br.com.maaicondgl.apirestfull.CodeChallengeSwat.Model.ContaBancariaEntity;
import br.com.maaicondgl.apirestfull.CodeChallengeSwat.Service.ContaBancariaService;
import org.springframework.beans.factory.annotation.Autowired;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/contaBancaria")
public class ContaBancariaController {
    @Autowired
    private ContaBancariaService contaBancariaService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ContaBancariaEntity> findAll() {
        return contaBancariaService.listAccount();
    }

    @PostMapping
    public ResponseEntity<ContaBancariaEntity> registerAccount(@RequestBody ContaBancariaEntity contaBancaria){
        return ResponseEntity.ok(contaBancariaService.registerAccount(contaBancaria));
    }

    //pesquisar por conta e agencia
    @GetMapping("/search/{conta}/{agencia}")
    public ResponseEntity<?> ConsultationAccount(@PathVariable String conta, @PathVariable String agencia){

        ContaBancariaEntity contaBancaria = contaBancariaService.ConsultationAccount(conta, agencia);

        if(contaBancaria != null){
            return ResponseEntity.ok(contaBancaria);
        }
        return new ResponseEntity<>("Conta Bancária não encontrada.",HttpStatus.NOT_FOUND);
    }

    @PutMapping
    public ResponseEntity<ContaBancariaEntity> accountChange(@RequestBody ContaBancariaEntity contaBancaria){
        return ResponseEntity.ok(contaBancariaService.accountChange(contaBancaria));
    }

    @DeleteMapping(value = "/excluir/{idConta}")
    public ResponseEntity<?> delete(@PathVariable Long idConta) {
        contaBancariaService.deleteContaById(idConta);
        return  ResponseEntity.noContent().build();
    }
    @PostMapping("/{conta}/saque/{valor}")
    public ResponseEntity<Map<String, Object>> sacarComJuros(@PathVariable String conta, @PathVariable double valor) throws ResourceNotFoundException {
        ContaBancariaEntity contaBancaria = contaBancariaService.sacarChequeEspecial(conta, valor);
        double limiteAtual = contaBancaria.getLimite();
        try {
            Map<String, Object> response = new HashMap<>();
            String mensagem = "Cheque especial Sacado, valor atual disponével";
            response.put("SwatBank informa: " + mensagem, limiteAtual);
            response.put("contaBancaria", contaBancaria);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null); // Retorna uma resposta de erro 400 Bad Request
        }
    }
}
