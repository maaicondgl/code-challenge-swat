package br.com.maaicondgl.apirestfull.CodeChallengeSwat.Repository;

import br.com.maaicondgl.apirestfull.CodeChallengeSwat.Model.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {

}
