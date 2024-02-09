package br.com.maaicondgl.apirestfull.CodeChallengeSwat.Service;

import java.util.List;
import java.util.Optional;

import br.com.maaicondgl.apirestfull.CodeChallengeSwat.Exceptions.ResourceNotFoundException;
import br.com.maaicondgl.apirestfull.CodeChallengeSwat.Model.ClienteEntity;
import br.com.maaicondgl.apirestfull.CodeChallengeSwat.Model.ContaBancariaEntity;
import br.com.maaicondgl.apirestfull.CodeChallengeSwat.Repository.ClienteRepository;
import br.com.maaicondgl.apirestfull.CodeChallengeSwat.Repository.ContaBancariaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ContaBancariaService contaBancariaService;

    @Autowired
    private ContaBancariaRepository contaBancariaRepository;

    public List<ClienteEntity> customerList(){
        return clienteRepository.findAll();
    }

    public Optional<ClienteEntity> searchCustomer(String cpf) {
        return clienteRepository.findById(cpf);
    }

    public ClienteEntity createCustomer(ClienteEntity cliente) {
        ContaBancariaEntity conta = contaBancariaService.registerAccount(cliente.getContaBancaria());

        cliente.setContaBancaria(conta);
        cliente = clienteRepository.save(cliente);
        return cliente;
    }

    public ClienteEntity updateCustomer(ClienteEntity cliente) throws ResourceNotFoundException {

        ContaBancariaEntity conta= contaBancariaService.accountChange(cliente.getContaBancaria());

        cliente.setContaBancaria(conta);
        return clienteRepository.save(cliente);
    }

    public void deletarClientePorCpf(String cpf) {
        clienteRepository.deleteByCpf(cpf);
    }
}
