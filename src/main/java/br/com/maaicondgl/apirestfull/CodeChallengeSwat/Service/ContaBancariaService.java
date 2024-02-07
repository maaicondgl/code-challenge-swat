package br.com.maaicondgl.apirestfull.CodeChallengeSwat.Service;

import java.time.LocalDate;

import br.com.maaicondgl.apirestfull.CodeChallengeSwat.Exceptions.ResourceNotFoundException;
import br.com.maaicondgl.apirestfull.CodeChallengeSwat.Model.ContaBancariaEntity;
import br.com.maaicondgl.apirestfull.CodeChallengeSwat.Repository.ContaBancariaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        public ContaBancariaEntity sacarChequeEspecial(String numConta, double valor) throws ResourceNotFoundException {
            // Recupera a entidade da conta bancária pelo ID
            ContaBancariaEntity contaBancaria = contaBancariaRepository.findByConta(numConta);

            boolean contaBancariaChequeEspecial = contaBancaria.getChequeEspecial();
            double limiteAtual = contaBancaria.getLimite();
            double saldo = contaBancaria.getSaldo();
            double valorComJuros = valor;

            // Verifica se o valor do saque ultrapassa o limite atual
            if (limiteAtual == 0.00 || limiteAtual <= 0){
                throw new ResourceNotFoundException("Saldo insuficiente e cheque especial desativado. Saque não realizado.");
            }else if (valor > limiteAtual ){
                // Verifica se o cheque especial está ativo
                if (contaBancariaChequeEspecial) {
                    // Calcula os juros de 2% ao dia
                    LocalDate dataSaque = LocalDate.now();
                    LocalDate dataAtual = LocalDate.now();
                    long dias = dataAtual.toEpochDay() - dataSaque.toEpochDay();
                    double juros = valor * 0.02 * dias; // Juros de 2% ao dia
                    valorComJuros = valor + juros;

                    // Verifica se o limite + cheque especial é suficiente para cobrir o saque
                    if (valorComJuros > saldo) {
                        throw new IllegalArgumentException("Saldo insuficiente e cheque especial não cobre o saque com juros.");
                    }
                } else {
                    throw new IllegalArgumentException("Saldo insuficiente e cheque especial desativado. Saque não realizado.");
                }
            }
            // Deduz o valor do saque do limite da conta
            contaBancaria.setLimite(limiteAtual - valorComJuros);

            // Atualiza a entidade da conta bancária no banco de dados
            return contaBancariaRepository.save(contaBancaria);
        }
}
