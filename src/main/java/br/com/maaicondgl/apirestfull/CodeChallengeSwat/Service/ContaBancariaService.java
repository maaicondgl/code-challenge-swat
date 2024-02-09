package br.com.maaicondgl.apirestfull.CodeChallengeSwat.Service;

import java.time.LocalDate;
import java.util.List;

import br.com.maaicondgl.apirestfull.CodeChallengeSwat.Exceptions.ResourceNotFoundException;
import br.com.maaicondgl.apirestfull.CodeChallengeSwat.Model.ContaBancariaEntity;
import br.com.maaicondgl.apirestfull.CodeChallengeSwat.Repository.ContaBancariaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContaBancariaService {
    @Autowired
    private ContaBancariaRepository contaBancariaRepository;

    public List<ContaBancariaEntity> listAccount() {
        return contaBancariaRepository.findAll();
    }

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

    public ContaBancariaEntity sacarChequeEspecial(String numConta, double valor) throws ResourceNotFoundException {
            // Recupera a entidade da conta bancária pelo ID
            ContaBancariaEntity contaBancaria = contaBancariaRepository.findByConta(numConta);

            boolean chequeEspecial = contaBancaria.getChequeEspecial();
            double limiteAtual = contaBancaria.getLimite();
            double saldo = contaBancaria.getSaldo();
            double valorComJuros = valor;
            double jurosChequeEspecial = contaBancaria.getJurosChequeEspecial();

            // Verifica se o valor do saque ultrapassa o limite atual

        if (valor > limiteAtual){
                // Verifica se o cheque especial está ativo
                if (chequeEspecial) {
                    // Calcula os juros de 2% ao dia
                    LocalDate dataSaque = LocalDate.now();
                    LocalDate dataAtual = LocalDate.now();
                    long dias = dataAtual.toEpochDay() - dataSaque.toEpochDay();
                    double juros = valor * 0.02 * dias; // Juros de 2% ao dia
                    valorComJuros = valor + juros;
                    juros = valorComJuros;
                    contaBancaria.setJurosChequeEspecial(juros);
                    if (valorComJuros > saldo) {
                        throw new IllegalArgumentException("Saldo insuficiente e cheque especial não cobre o saque com juros.");
                    }
                } else {
                    throw new IllegalArgumentException("Saldo insuficiente e cheque especial desativado. Saque não realizado.");
                }
        }

        // valida se limite atual é 0, se for 0 seta no banco cheque Especial false
        if (limiteAtual == 0.00 ||  limiteAtual < 0){
            contaBancaria.setChequeEspecial(false);
            throw new ResourceNotFoundException("Essa conta não possui limite disponível");
        }
        // verifica o se existe valor proveniente do juros do cheque especial, se sim subtrai do saldo e 0 o valor do juros atual
        if (jurosChequeEspecial > 0) {
            double novoSaldo = saldo - jurosChequeEspecial;
            contaBancaria.setSaldo(novoSaldo);
            if(novoSaldo <= 0){
                contaBancaria.setJurosChequeEspecial(0);
            }
        }
        // Atualiza a entidade da conta bancária no banco de dados
        return contaBancariaRepository.save(contaBancaria);
    }
}
