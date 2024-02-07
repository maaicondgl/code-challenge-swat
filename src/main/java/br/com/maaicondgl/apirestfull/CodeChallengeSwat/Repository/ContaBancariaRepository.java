package br.com.maaicondgl.apirestfull.CodeChallengeSwat.Repository;

import br.com.maaicondgl.apirestfull.CodeChallengeSwat.Model.ContaBancariaEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaBancariaRepository extends JpaRepository<ContaBancariaEntity, Long> {
    ContaBancariaEntity findByContaAndAgencia(String conta, String agencia);

    ContaBancariaEntity findByConta(String conta);

    @Transactional
    @Modifying
    @Query("DELETE FROM ContaBancariaEntity c WHERE c.idConta = ?1")
    void deleteByIdConta(Long idConta);
}

