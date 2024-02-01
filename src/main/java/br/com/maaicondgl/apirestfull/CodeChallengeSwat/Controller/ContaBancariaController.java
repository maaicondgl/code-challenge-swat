package br.com.maaicondgl.apirestfull.CodeChallengeSwat.Controller;

import java.util.List;

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

@RestController
@RequestMapping("/contaBancaria")
public class ContaBancariaController {
    @Autowired
    private ContaBancariaService contaService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ContaBancariaEntity> listarContas(){
        return contaService.listarContas();
    }

    @GetMapping(value = "/{idConta}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContaBancariaEntity> findById(@PathVariable(value = "idConta")Long idConta){
        ContaBancariaEntity contaBancaria = contaService.findById(idConta);
        if (contaBancaria != null){

            return  ResponseEntity.ok(contaService.findById(idConta));
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContaBancariaEntity> create(@RequestBody ContaBancariaEntity conta){
        return ResponseEntity.ok(contaService.create(conta));
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContaBancariaEntity>update(@RequestBody ContaBancariaEntity conta){
        return ResponseEntity.ok(contaService.update(conta));
    }

    @DeleteMapping(value = "/{idConta}")
    public ResponseEntity<?> delete(@PathVariable(value = "idConta")Long idConta) {
        contaService.delete(idConta);
        return  ResponseEntity.noContent().build();
    }

    @GetMapping("/buscarconta/{conta}/{agenciaBancaria}")
    public ResponseEntity<?> realizarConsultaConta(@PathVariable Long conta, @PathVariable(name = "agenciaBancaria") Long agenciaBancaria) {

        ContaBancariaEntity contaBancaria = contaService.realizarConsultaDeConta(conta, agenciaBancaria);

        if (contaBancaria != null) {
            return ResponseEntity.ok(contaBancaria);
        }

        return new ResponseEntity<>("Conta Bancária não encontrada.", HttpStatus.NOT_FOUND);
    }

}
