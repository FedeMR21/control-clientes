package ar.com.g5;

import org.springframework.data.repository.CrudRepository;

public interface ProductosRepository extends CrudRepository<Producto, Integer> {

    Producto findFirstByCodigo(String codigo);
}
