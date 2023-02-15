package ProyectoFInal2023.ProyectoFInal2023.Controller;


import ProyectoFInal2023.ProyectoFInal2023.Entities.Categorias;
import ProyectoFInal2023.ProyectoFInal2023.Exception.RequestException;
import ProyectoFInal2023.ProyectoFInal2023.Service.CategoriasService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("/categorias")
public class CategoriasController {

    private final CategoriasService categoriasService;

    @PostMapping("/agregar")
    public ResponseEntity<?> agregar(@RequestBody Categorias categorias) {

        var titulo = categorias.getTitulo();
        var descripcion = categorias.getDescripcion();
        var url = categorias.getUrl();

        var buscarTitulo = categoriasService.buscarTitulo(titulo);

        if (buscarTitulo!= null){
            throw new RequestException("400 Bad Request","Titulo ya existente");
        }
        if (titulo != null & descripcion != null & url != null & !titulo.equals("") & !descripcion.equals("") & !url.equals("")){
            categoriasService.agregar(categorias);
            return new ResponseEntity<>("La Categoria se guardo con exito", null, HttpStatus.CREATED);
        }else{
            throw new RequestException("400 Bad Request","Sintaxis Invalida");
        }
    }

    @GetMapping("/listar")
    public List<Categorias> listar() {
        return categoriasService.listar();
    }

    @PutMapping("/modificar/{id}")
    public ResponseEntity<?> modificar(@RequestBody Categorias categorias, @PathVariable Long id) {

        var titulo = categorias.getTitulo();
        var descripcion = categorias.getDescripcion();
        var url = categorias.getUrl();


        var buscarId = categoriasService.buscarPorId(id);
        var buscarTitulo = categoriasService.buscarTitulo(titulo);

        if (buscarId.isPresent()) {
            if (buscarTitulo != null){
                throw new RequestException("400 Bad Request","Titulo ya existente");
            }
            if (titulo != null & descripcion != null & url != null & !titulo.equals("") & !descripcion.equals("") & !url.equals("")){
                categoriasService.modificar(categorias,id);
                return new ResponseEntity<>("La Categoria se Actualizo con exito", null, HttpStatus.CREATED);
            }else{
                throw new RequestException("400 Bad Request","Sintaxis Invalida");
            }
        } else {
            throw new RequestException("400 Bad Request","No existe Categoria para el Id: "+id);
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {

        var buscarId = categoriasService.buscarPorId(id);

        if (buscarId.isEmpty()) {
            throw new RequestException("400 Bad Request","No existe Categoria para el Id: "+id);
        } else {
           categoriasService.eliminar(id);
            return new ResponseEntity<>(("La Categoria con Id: "+id+" se elimino con exito"), null, HttpStatus.CREATED);
        }
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id){

        ResponseEntity<?> respuestaHttp = null;
        var buscarId = categoriasService.buscarPorId(id);

        if (buscarId.isEmpty()) {
            throw new RequestException("400 Bad Request","Id: {"+ id + "} no corresponde a ninguna Categoria");
        }
        else {
            respuestaHttp = ResponseEntity.ok(buscarId);
        }
        return respuestaHttp;
    }

    @GetMapping("/buscarTitulo/{titulo}")
    public ResponseEntity<?> buscarMatricula(@PathVariable String titulo){

        ResponseEntity<?> respuestaHttp = null;
        var buscarTitulo = categoriasService.buscarTitulo(titulo);

        if (buscarTitulo == null){
            throw new RequestException("400 Bad Request","No existe ninguna Categoria con Titulo: "+ titulo );
        }
        else {
            respuestaHttp = ResponseEntity.ok(buscarTitulo);
        }
        return respuestaHttp;
    }



}
