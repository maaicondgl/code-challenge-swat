package br.com.maaicondgl.apirestfull.CodeChallengeSwat.Service;

import java.util.List;

import br.com.maaicondgl.apirestfull.CodeChallengeSwat.Exceptions.ResourceNotFoundException;
import br.com.maaicondgl.apirestfull.CodeChallengeSwat.Model.ContaBancariaEntity;
import br.com.maaicondgl.apirestfull.CodeChallengeSwat.Repository.ContaBancariaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContaBancariaService {

    @Autowired
    ContaBancariaRepository contaRepository;
    public ContaBancariaEntity findById(Long idConta){

        return contaRepository.findById(idConta).orElseThrow(() -> new ResourceNotFoundException("ID não encontrado"));
    }
    public List<ContaBancariaEntity> listarContas(){
        return contaRepository.findAll();
    }

    public ContaBancariaEntity create(ContaBancariaEntity conta){
        if (!conta.getChequeEspecial()) {
            Double valorLimite = 0.00;
            conta.setLimite(valorLimite);
        }
        return contaRepository.save(conta);
    }

    public ContaBancariaEntity update(ContaBancariaEntity conta){

        //       AQUI VAI NO BANCO E RECUPERA A PESSOA POR ID
        var  entity = contaRepository.findById(conta.getIdConta()).orElseThrow(() -> new ResourceNotFoundException("ID Conta não encontrado"));

        entity.setNomeBanco(conta.getNomeBanco());
        entity.setAgenciaBancaria(conta.getAgenciaBancaria());
        entity.setConta(conta.getConta());
        entity.setSaldo(conta.getSaldo());

        return contaRepository.save(conta);
    }

    public void delete(Long IdConta){
//         Busca o ID na base passando  para variavel entity em seguida deleta a entidade no banco(Conta)
        var entity = contaRepository.findById(IdConta).orElseThrow(() -> new ResourceNotFoundException("ID Conta não encontrado!"));
        contaRepository.delete(entity);
    }
    public ContaBancariaEntity realizarAlteracaoConta(ContaBancariaEntity conta) {
        if (!conta.getChequeEspecial()) {
            Double valorLimite = 0.00;
            conta.setLimite(valorLimite);
        }
        return contaRepository.save(conta);
    }
    public ContaBancariaEntity realizarConsultaDeConta(Long conta, Long agenciaBancaroa) {
        return contaRepository.findByContaAndAgenciaBancaria(conta, agenciaBancaroa);
    }
}
