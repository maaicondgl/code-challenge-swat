package br.com.maaicondgl.apirestfull.CodeChallengeSwat.Service;

import java.util.List;

import br.com.maaicondgl.apirestfull.CodeChallengeSwat.Exceptions.ResourceNotFoundException;
import br.com.maaicondgl.apirestfull.CodeChallengeSwat.Model.ContaBancariaEntity;
import br.com.maaicondgl.apirestfull.CodeChallengeSwat.Repository.ContaBancariaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class ContaBancariaService {
    @Autowired
    private ContaBancariaRepository contaBancariaRepository;

    private ContaBancariaEntity contaBancariaEntity;

    public ContaBancariaEntity ConsultationAccount(String numConta, String numAgencia) {
        return contaBancariaRepository.findByContaAndAgencia(numConta, numAgencia);
    }

    public ContaBancariaEntity accountChange(ContaBancariaEntity contaBancaria) {
        if (contaBancaria.getSaldo() > 1000) {
            contaBancaria.setChequeEspecial(true);
        } else {
            contaBancaria.setChequeEspecial(false);
        }
        return contaBancariaRepository.save(contaBancaria);
    }

    public ContaBancariaEntity registerAccount(ContaBancariaEntity contaBancaria) {
        String conta = contaBancaria.getConta();
        String agencia = contaBancaria.getAgencia();
        // Se saldo for > R$1000 cheque especial True
        if (contaBancaria.getSaldo() > 1000) {
            contaBancaria.setChequeEspecial(true);
        } else {
            contaBancaria.setChequeEspecial(false);
        }

        if (conta != null && agencia != null){
            contaBancaria.setConta(conta);
            contaBancaria.setAgencia(agencia);
        }else {
            throw new IllegalArgumentException("Conta e agencia não pode ser Nulas");
        }
        return contaBancariaRepository.save(contaBancaria);
    }

    public void deleteContaById(Long idConta) {
        contaBancariaRepository.deleteByIdConta(idConta);
    }

}
