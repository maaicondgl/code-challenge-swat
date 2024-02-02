package br.com.maaicondgl.apirestfull.CodeChallengeSwat.Repository;

import br.com.maaicondgl.apirestfull.CodeChallengeSwat.Model.ClienteEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, String> {
    @Transactional
    @Modifying
    @Query("DELETE FROM ClienteEntity c WHERE c.cpf = ?1")
    void deleteByCpf(String cpf);

}
