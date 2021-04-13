package ar.com.g5;

import org.springframework.data.repository.CrudRepository;

public interface ClientesRepository extends CrudRepository<Cliente, Integer> {

    Cliente findFirstById(Integer id);
}
