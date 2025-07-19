package com.alura.literatura.model;

import com.alura.literatura.service.ConsultaGemini;
import jakarta.persistence.*;
import org.hibernate.query.named.ResultMemento;

import java.util.List;

@Entity
@Table(name="libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(unique = true) // no se repota nombre del titulo
    private String titulo;
    @OneToMany(mappedBy = "libro" , cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    private List<Autor> autores;
    @Column(length=1500)
    private List<String> resumenes;
    private List<String> idiomas;
    private Double numeroDescargas;


    // JPA obliga que haya un constructor predeterminado , cuando creamos un constructor personALIZADO TENEMOS QUE DECLARAR EL PREDETERMINADO
    public Libro(){}
    public Libro(DatosLibro datosLibro){
        this.titulo = datosLibro.titulo();

        this.resumenes= datosLibro.resumenes().stream().map(resumen -> ConsultaGemini.obtenerTraduccion(resumen)).toList();
        this.idiomas =  datosLibro.idiomas();
        this.numeroDescargas = datosLibro.numeroDescargas();
        this.autores = datosLibro.autores().stream().map(datosAutor -> new Autor(datosAutor)).toList();

    }

    public void actualizarAutores(List<Autor> autoresActual,List<Libro> totalLibros){
        autoresActual.forEach(autor -> {autor.actualizarLibrosAutor(totalLibros,autor.getLibrosAutor());});
        this.autores = autoresActual;
    }

    public void setAutores(List<Autor> autoresActual){
        autoresActual.forEach(autor ->{
            autor.setLibro(this);
            autor.setLibrosAutor(this.getTitulo(),autor.getLibrosAutor());
        });
        this.autores=autoresActual;
    }
    public List<Autor>  getAutores(){
        return autores;
    }
    public void setResumenes(List<String> resumenes){
        this.resumenes = resumenes;
    }
    public List<String> getResumenes(){
        return   resumenes;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<String> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<String> idiomas) {
        this.idiomas = idiomas;
    }

    public Double getNumeroDescargas() {
        return numeroDescargas;
    }

    public void setNumeroDescargas(Double numeroDescargas) {
        this.numeroDescargas = numeroDescargas;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }
    @Override
    public String toString() {
        return  "Libro :("+
                "  titulo='" + titulo + '\'' +
                ", autores=" + autores +
                ", resumenes=" + resumenes +
                ", idiomas=" + idiomas +
                ", numeroDescargas=" + numeroDescargas +
                ")";
    }
}
