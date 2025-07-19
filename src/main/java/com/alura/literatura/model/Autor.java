package com.alura.literatura.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name="autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private Integer anioNacimiento;
    private Integer anioMuerte;
    @ManyToOne
    private Libro libro;
    private List<String> librosAutor;

    public Autor(){}

    public Autor(DatosAutor datosAutor){
        this.nombre = datosAutor.nombre();
        this.anioNacimiento = datosAutor.anioNacimiento();
        this.anioMuerte = datosAutor.anioMuerte();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getAnioNacimiento() {
        return anioNacimiento;
    }

    public void setAnioNacimiento(Integer anioNacimiento) {
        this.anioNacimiento = anioNacimiento;
    }

    public Integer getAnioMuerte() {
        return anioMuerte;
    }

    public void setAnioMuerte(Integer anioMuerte) {
        this.anioMuerte = anioMuerte;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }
    public void setLibrosAutor(String tituloLibro, List<String> librosAutor){
        try{
            if(!librosAutor.contains(tituloLibro)){
                librosAutor.add(tituloLibro);
                this.librosAutor=librosAutor;
            }
        }catch (Exception e){
            this.librosAutor = new ArrayList<>(Arrays.asList(tituloLibro));
        }

    }

    public void actualizarLibrosAutor(List<Libro> totalLibros,List<String> librosAutor){
        totalLibros.forEach(libro1 -> {if(libro1.getAutores().stream().map(autor -> autor.getNombre()).toList().contains(this.getNombre()) && !librosAutor.contains(libro1.getTitulo()))
        { librosAutor.add(libro1.getTitulo());}});
        this.librosAutor = librosAutor;
        //System.out.println("librosAutor :"+this.getLibrosAutor());
    }

    public List<String> getLibrosAutor() {
        return librosAutor;
    }

    @Override
    public String toString() {
        return
                "nombre='" + nombre + '\'' +
                ", anioNacimiento='" + anioNacimiento + '\'' +
                ", anioMuerte='" + anioMuerte + '\''+
                ", librosDeAutor= "+ librosAutor;

    }
}
