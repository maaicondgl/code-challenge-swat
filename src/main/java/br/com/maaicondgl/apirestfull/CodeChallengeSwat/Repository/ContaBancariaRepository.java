package br.com.maaicondgl.apirestfull.CodeChallengeSwat.Repository;

import br.com.maaicondgl.apirestfull.CodeChallengeSwat.Model.ContaBancariaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaBancariaRepository extends JpaRepository<ContaBancariaEntity, Long> {
    ContaBancariaEntity findByContaAndAgenciaBancaria(Long conta, Long agenciaBancaria);
}
