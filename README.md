<header>
  <h1 align = "center" width="70%"> CHALLENGE LITERATURA </h1>
  <img src="https://github.com/user-attachments/assets/890d01b1-e771-4e62-80b4-87650dff0543" width="10%">
</header>
<h2>Implementación challenge catalogo de libros - Alura</h2>

![STATUS](https://img.shields.io/badge/STATUS-FINISHED-brightgreen)

Este proyecto implementa un catálogo de libros usando Spring Boot.

## Indice

* [Titulo e imagen de portada](#Titulo-e-imagen-de-portada)
* [Insignias](#insignias)
* [Indice](#indice)
* [Descripcion del proyecto](#descripcion-del-proyecto)
* [Estado del proyecto](#estado-del-proyecto)
* [Funcionalidades del proyecto](#Funcionalidades-del-proyecto)
* [Acceso al proyecto](#Acceso-al-proyecto)
* [Tecnologias utilizadas](#Tecnologias-utilizadas)
* [Personas Contribuyentes](#Personas-contribuyentes)
* [Personas-Desarrolladores del Proyecto](#Personas-desarrolladores)
* [Licencias](#Licencias)
* [Conclusion](#Conclusion)

## Descripcion del proyecto
Proyecto challenge "literatura" del curso Java y Spring Framework  . Este proyecto consiste en implementar un programa hecho en java utilizando la orientacion a objetos , se realiza solicitudes a una API delibros utilizando JSON,guardarlos en una base de datos utilizando PostgreSQL y, finalmente, a filtrar y mostrar los libros y autores de interés.

## Estado del proyecto
Culminado

## 🔨 Funcionalidades del proyecto
- `Funcionalidad 1`: El programa te muestra un menu en consola con 6 opciones , el usuario ingresa una opcion :
  <img width="1170" height="377" alt="menu literatura" src="https://github.com/user-attachments/assets/64015e87-9107-40bb-af9c-b1782204ee68" />


- `Funcionalidad 2`: Al ingresar la opcion 1 , Buscar Libro por titulo, buscara el libro de acuerdo al titulo ingreado en la API de libros Gutendex , el cual retornara un archivo JSON con la informacion de los ibros solicitados , el cual se eligira el primer resultado de libro, se imprimira en pantalla los detalles del libro:
  
<img width="1578" height="410" alt="resultado busqueda de libro" src="https://github.com/user-attachments/assets/4cf17264-31b5-4e17-9ecb-a77b00a85d6f" />

El libro encontrado se agrega a la base de datos en la tabla libros:

<img width="1277" height="402" alt="resultado base de datos" src="https://github.com/user-attachments/assets/986cf849-116e-4f1f-bdea-b70fac0a4a9d" />

La identidad Autor/es se agregan a la tabla autores:

<img width="1415" height="547" alt="base de datos autores" src="https://github.com/user-attachments/assets/29c4947a-70cd-45e6-b844-417f5fcc4131" />

Si no encuentra el libro , mostrara en pantalla que no se encontro dicho libro :

<img width="735" height="402" alt="resultado busqueda sin registro" src="https://github.com/user-attachments/assets/9d67b28a-b46e-481f-8c46-2d4ecb0400c9" />

  
- `Funcionalidad 3`: Al elegir la opcion 2 , Listar libros registrados, se imprimira en pantalla todos los libros registrados en la base de datos con el detalle de cada uno:
  
  <img width="1382" height="473" alt="opcion 2 mostrar libros registrados" src="https://github.com/user-attachments/assets/3ca3d9ed-3191-480c-ad86-1c9bc36875eb" />


- `Funcionalidad 4`: Al elegir la opcion 3,Listar autores registrados, imprimira todos los autores registrados en la base de datos consu respectivo detalle , se agrego un atributo con una lista de libros escritos por cada autor :

  
<img width="1117" height="523" alt="opcion 3 mostrar autores registrados" src="https://github.com/user-attachments/assets/c48c3054-00b2-4562-ba1c-496b9d0c93b3" />

  
- `Funcionalidad 5`: Al elegir la opcion 4,Listar autores vivieron hasta un determinado año , la consola solicitara ingresar un determinado año , y se mostrara la lista de autores que vivieron hasta ese determinado año:
  
<img width="1087" height="488" alt="autores vivieron hasta determinado año" src="https://github.com/user-attachments/assets/5cc256da-28f9-4dd8-aaee-136baffc5a7f" />

 - `Funcionalidad 6`: Al elegir la opcion 5 , Listar libros por idioma, el programa mostrara un sub menu para que el usuario pueda ingresar el idioma de los libros que se quieren mostrar :

<img width="1177" height="632" alt="mostrar libros por idioma" src="https://github.com/user-attachments/assets/7d32f404-2521-4dcf-b55e-a49aba662a72" />

## 🔗Acceso al proyecto 
Si deseas puedes acceder al proyecto a traves del siguinete enlace:

  https://github.com/ejaimer/challenge-conversor-de-monedas/tree/main/src/com/edward

## ✔️ Tecnologias utilizadas
- Java
- Maven
- ExchangeRate-API

## Personas-Desarrolladores del proyecto
| [<img src="https://avatars.githubusercontent.com/u/70885445?s=400&u=6cbe5aa76688201740b9ae2cafbf789ab55bc533&v=4" width=115><br><sub>Edward Jaime Ricra</sub>](https://github.com/ejaimer) |
| :---: | 

## Conclusiones
- Se utilizo 3 clases Concretas : MenuCoversor,ConusltaTasa,RegistroCambiosMoneda para poder modelar la funcionalidad de nuestro programa.
- Se una clase Record,TasaDeCambio , esta última para poder registar los valores de cambios obtenidos de la API Exchange.
