package ProyectoFInal2023.ProyectoFInal2023.Service;

import ProyectoFInal2023.ProyectoFInal2023.Entities.Categorias;
import ProyectoFInal2023.ProyectoFInal2023.Repository.CategoriasRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoriasService {

    private final CategoriasRepository categoriasRepository;

    public void agregar(Categorias c){
        categoriasRepository.save(c);
    }
    public List<Categorias> listar() {
        return (categoriasRepository.findAll());
    }

    public void modificar(Categorias c, Long id) {
        var categoriaOld = categoriasRepository.findById(id).get();
        if(c.getTitulo() != null & !c.getTitulo().equals("")) categoriaOld.setTitulo(c.getTitulo());
        if(c.getDescripcion() != null & !c.getDescripcion().equals("")) categoriaOld.setDescripcion(c.getDescripcion());
        if(c.getUrl() != null & !c.getUrl().equals(""))categoriaOld.setUrl(c.getUrl());
        categoriasRepository.save(categoriaOld);
    }

    public void eliminar(Long  id) {
        categoriasRepository.deleteById(id);
    }

    public Optional<Categorias> buscarPorId(Long id) {
        return categoriasRepository.findById(id);
    }

    public Categorias buscarTitulo(String titulo) {
        return categoriasRepository.findByTitulo(titulo);
    }



}
