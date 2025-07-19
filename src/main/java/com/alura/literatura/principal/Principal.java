package com.alura.literatura.principal;

import com.alura.literatura.model.*;
import com.alura.literatura.repository.LibroRepository;
import com.alura.literatura.service.ConsumoApi;
import com.alura.literatura.service.ConvierteDatosApiAClase;
import org.hibernate.event.spi.SaveOrUpdateEvent;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
    private ConsumoApi consumoApi = new ConsumoApi();
    private ConvierteDatosApiAClase convierteDatosApiAClase = new ConvierteDatosApiAClase();
    private final String URL_BASE = "https://gutendex.com";
    private Scanner teclado = new Scanner(System.in);
    private ConvierteDatosApiAClase conversorApiAClase = new ConvierteDatosApiAClase();
    private LibroRepository repositorio;
    private List<Libro> libros;

    public Principal(LibroRepository repository){
        this.repositorio = repository;
    }

    public void muestraElMenu(){
        String menu = """
                Elija una opcion a traves de un número :
                
                1.- Buscar Libro por titulo
                2.- Listar libros registrados
                3.- Listar autores registrados
                4.- Listar autores vivios en un determinado año
                5.- Listar libros por idioma
                
                6.- Salir
                """;
        Integer opcionElegida = -1;
        while (opcionElegida !=6 ){
            System.out.println("****************** Menu Libreria **********************");
            System.out.println(menu);
            opcionElegida = teclado.nextInt();
            teclado.nextLine();
            switch (opcionElegida){
                case 1:
                    buscarLibroPorTitulo();
                    break;
                case 2:
                    listarLibrosRegistrados();
                    break;
                case 3:
                    listarAutoresRegistrados();
                    break;
                case 4:
                    listarAutoresVivosHastaAnioDeterminado();
                    break;
                case 5:
                    listarLibrosPorIdioma();
                    break;
                case 6:
                    System.out.println("Cerrado la aplicacion...");
                    break;
                default:
                    System.out.println("Opcion Invalida");
            }

        }

    }

    private List<DatosLibro> getResultados(){
        //Retorna los datos de los libors busxcados en una lista ene el formato de clases DatoLibro y DatoAutor
        System.out.println("Escribe el nombre/Titulo del libro que quieres buscar :");
        String tituloLibro = teclado.nextLine();
        String json = consumoApi.obtenerDatos(URL_BASE+"/books/?search="+tituloLibro.replace(" ","+"));
        //System.out.println(json);
        DatosResultados datosResultados = conversorApiAClase.obtenerDatos(json, DatosResultados.class);
        //System.out.println((datosResultados.resultados()).getClass());

        // verificamos si en la lista de resultados hay datos
        List<DatosLibro> listaLibroBuscado = datosResultados.resultados();
        if(!listaLibroBuscado.isEmpty()){
            System.out.println("Libro / Libros encontrados");
            //System.out.println(listaLibroBuscado);
            return listaLibroBuscado;
        }else {
            System.out.println("no se encontró regisro de libros con este nombre");
            return null;
        }

    }

    private void buscarLibroPorTitulo(){
        //obtener datos de la serie buscada
        List<DatosLibro> datosBuscados = getResultados();
        //Convertimos a una lista colo elementos de clase Libro
        if(datosBuscados != null) {
            List<Libro> totalLibrosBD = repositorio.obtenerRegistoLibros();
            libros = datosBuscados.stream().map(datolibro -> new Libro(datolibro)).toList();
            //System.out.println(libros);
            // Seleccionamos solo el primer Libro el cual se guardara en el repositorio
            Libro libroEncontrado = libros.get(0);
            // Seteamos el libro en cada autor
            List<Autor> autores = libroEncontrado.getAutores();
            //libroEncontrado.setAutores(autores,totalLibrosBD);
            libroEncontrado.setAutores(autores);
            try {
                repositorio.save(libroEncontrado);
            }catch (Exception e){
                System.out.println("El libro se encuentra en la BD");
            }

            //System.out.println(libros.get(0).getIdiomas().getClass());
            imprimirLibro(libroEncontrado);
        }
    }
    private void imprimirLibro(Libro libro){
        System.out.println("-----------LIBRO----------");
        System.out.println("Titulo    : "+ libro.getTitulo());
        System.out.println("Autores   : "+libro.getAutores().stream().map(autor -> autor.getNombre()).toList());
        System.out.println("idioma    : "+libro.getIdiomas());
        System.out.println("Numero de descargas : "+libro.getNumeroDescargas());
        System.out.println("Resumen   : "+libro.getResumenes());
        System.out.println("_________________________");

    }
    private void imprimiAutor(Autor autor){
        System.out.println("-----------AUTOR----------");
        System.out.println("nombre    : "+ autor.getNombre());
        System.out.println("Año de nacimiento   : "+autor.getAnioNacimiento());
        System.out.println("Año de fallecimiento : "+autor.getAnioMuerte());
        System.out.println("Libros del autor : "+autor.getLibrosAutor());
        System.out.println("_________________________");

    }
    private  void listarLibrosRegistrados(){
        List<Libro> registoLibros = repositorio.obtenerRegistoLibros();
        registoLibros.forEach(libro -> imprimirLibro(libro));

    }
    private void listarAutoresRegistrados(){
        //obtenemos lista total de autores
        List<Libro> totalLibros = repositorio.obtenerRegistoLibros();
        //Actualizamos lis libros de cada autor
        totalLibros.forEach(libro -> libro.actualizarAutores(libro.getAutores(),totalLibros));
        totalLibros.forEach(libro -> repositorio.save(libro));
        //System.out.println(totalLibros.get(0));
        //Obtenemos registro de Autores unicos
        List<Autor> totalAutores = repositorio.obtenerRegistroAutores();
        //totalAutores.forEach(System.out::println);
        totalAutores.forEach(autor -> imprimiAutor(autor));
    }
    private void listarAutoresVivosHastaAnioDeterminado(){
        System.out.println("Ingresar el año en el cual quiere listar los auutores hasta vivieron :");
        Integer anioAutorVivo = teclado.nextInt();
        teclado.nextLine();
        //actualizamos la lista de libros de los autores
        actualizarLibrosDeAutores();
        List<Autor> autoresVivosAnio = repositorio.listarAutoresAnioVivio(anioAutorVivo);
        //autoresVivosAnio.forEach(System.out::println);
        System.out.println("Lista de autores vivos hasta antes del año "+anioAutorVivo);
        autoresVivosAnio.forEach(autor -> imprimiAutor(autor));
    }
    public void actualizarLibrosDeAutores(){
        //obtenemos lista total de autores
        List<Libro> totalLibros = repositorio.obtenerRegistoLibros();
        //Actualizamos lis libros de cada autor
        totalLibros.forEach(libro -> libro.actualizarAutores(libro.getAutores(),totalLibros));
        totalLibros.forEach(libro -> repositorio.save(libro));
    }
    private void listarLibrosPorIdioma(){
        System.out.println("Ingrese el idioma para buscar los libros :");
        String submenu = """
                es = español
                en = ingles
                fr = frances
                pt = portugues
                """;
        System.out.println(submenu);
        String idiomaSolicitado = teclado.nextLine();
        List<Libro> librosTotal = repositorio.obtenerRegistoLibros();
        List<Libro> librosIdioma = librosTotal.stream().filter(libro -> libro.getIdiomas().contains(idiomaSolicitado)).toList();
        System.out.println("Libros escritos en  el idioma "+idiomaSolicitado);
        librosIdioma.forEach(libro -> imprimirLibro(libro));
    }

}
