package ProyectoFInal2023.ProyectoFInal2023.Repository;

import ProyectoFInal2023.ProyectoFInal2023.Entities.Categorias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CategoriasRepository extends JpaRepository<Categorias, Long> {

    @Query("select c FROM Categorias c where c.titulo= ?1")
    public Categorias findByTitulo(String titulo);
}
