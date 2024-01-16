package br.com.maaicondgl.apirestfull.CodeChallengeSwat.Service;

import java.util.List;

import br.com.maaicondgl.apirestfull.CodeChallengeSwat.Exceptions.ResourceNotFoundException;
import br.com.maaicondgl.apirestfull.CodeChallengeSwat.Model.ClienteEntity;
import br.com.maaicondgl.apirestfull.CodeChallengeSwat.Repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
    @Autowired
    ClienteRepository clienteRepository;

    public ClienteEntity create(ClienteEntity cliente) {
        return clienteRepository.save(cliente);
    }

    public ClienteEntity update(ClienteEntity cliente) {

        var  entity = clienteRepository.findById(cliente.getId()).orElseThrow(() -> new ResourceNotFoundException("ID Cliente não encontrado"));

        entity.setName(cliente.getName());
        entity.setPhoneNumber(cliente.getPhoneNumber());
        entity.setAddress(cliente.getAddress());

        return clienteRepository.save(cliente);
    }

    public void delete(Long id) {
        var  entity = clienteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("ID Cliente não encontrado"));

        clienteRepository.delete(entity);
    }

    public ClienteEntity findById(Long id) {
        return clienteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("ID Cliente não encontrado"));
    }

    public List<ClienteEntity> findAll() {
        return clienteRepository.findAll();
    }

}
