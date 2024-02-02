package br.com.maaicondgl.apirestfull.CodeChallengeSwat.Controller;

import br.com.maaicondgl.apirestfull.CodeChallengeSwat.Model.ContaBancariaEntity;
import br.com.maaicondgl.apirestfull.CodeChallengeSwat.Service.ContaBancariaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
@RequestMapping("/contaBancaria")
public class ContaBancariaController {
    @Autowired
    private ContaBancariaService contaBancariaService;

    @PostMapping
    public ResponseEntity<ContaBancariaEntity> registerAccount(@RequestBody ContaBancariaEntity contaBancaria){
        return ResponseEntity.ok(contaBancariaService.registerAccount(contaBancaria));
    }

    //pesquisar conta não pelo id, mas pela conta e agência que é mais usual
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

}
