package br.com.maaicondgl.apirestfull.CodeChallengeSwat.Controller;

import java.util.List;

import br.com.maaicondgl.apirestfull.CodeChallengeSwat.Model.ContaBancariaEntity;
import br.com.maaicondgl.apirestfull.CodeChallengeSwat.Service.ContaBancariaService;
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
@RequestMapping("/contaBancaria")
public class ContaBancariaController {
    @Autowired
    private ContaBancariaService contaService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ContaBancariaEntity> listarContas(){
        return contaService.listarContas();
    }

    @GetMapping(value = "/{idConta}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ContaBancariaEntity findById(@PathVariable(value = "idConta")Long idConta){
        return contaService.findById(idConta);
    }
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ContaBancariaEntity create(@RequestBody ContaBancariaEntity conta){
        return  contaService.create(conta);

    }
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ContaBancariaEntity update(@RequestBody ContaBancariaEntity conta){
        return  contaService.update(conta);

    }
    @DeleteMapping(value = "/{idConta}")
    public ResponseEntity<?> delete(@PathVariable(value = "idConta")Long idConta) {
        contaService.delete(idConta);
        return  ResponseEntity.noContent().build();
    }

}
