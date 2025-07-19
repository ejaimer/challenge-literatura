package com.alura.literatura.repository;

import com.alura.literatura.model.Autor;
import com.alura.literatura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libro,Long> {
    @Query(value="SELECT l FROM Libro l WHERE l.titulo ILIKE %:tituloLibro% ")
    Optional<Libro> buscarLibroPorTituloEnBD(String tituloLibro);

    @Query("SELECT l FROM Libro l")
    List<Libro> obtenerRegistoLibros();

    @Query("SELECT a FROM Autor a WHERE a.id IN (SELECT MIN(a2.id) FROM Autor a2 GROUP BY a2.nombre)")
    List<Autor> obtenerRegistroAutores();

    @Query("SELECT a FROM Autor a WHERE a.id IN (SELECT MIN(a2.id) FROM Autor a2 GROUP BY a2.nombre) AND a.anioMuerte <= :anio")
    List<Autor> listarAutoresAnioVivio(Integer anio);

}
