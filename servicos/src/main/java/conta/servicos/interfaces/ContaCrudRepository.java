package conta.servicos.interfaces;

import conta.servicos.entidade.ContaEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ContaCrudRepository extends JpaRepository<ContaEntity, Integer> {

}
