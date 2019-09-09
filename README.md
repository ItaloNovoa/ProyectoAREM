# ProyectoAREM
# Servidor Web Basico
#
este proyecto se encarga de desarrollar un framework basico con la capacidad de cargar imagenes y htmls a travez de un browser y de correr los metodos con un parametro o sin parametros
#
# Prerequisitos
```
mvn 3.6 Guia-->https://www.youtube.com/watch?v=biBOXvSNaXg
java 1.8 Guia -->https://www.youtube.com/watch?v=kPWezAZGPks
```
#
# Instalacion y ejecucion del proyecto

## local


1. Descargue el repositorio
```
git clone https://github.com/ItaloNovoa/ProyectoAREM
```
2. ingrese por cmd, terminal o simbolo del sistema a la ubicacion del documento
3. digite la siguiente lineas para descargar dependencias
```
mvn package
mvn install
```
4. ejecutar proyecto
```
 mvn exec:java -D exec.mainClass="arem.proyecto.Controlador"
```
5. O correrlo en heroku

### Despliegue en  Heroku

[![Proyecto Arem](https://www.herokucdn.com/deploy/button.png)](https://boiling-scrubland-02497.herokuapp.com/)

# Ejecutar Pruebas
```
mvn test
```
# Casos de prueba
### local = http://localhost:4567/ +(complemento)
### heroku = https://secret-fjord-27017.herokuapp.com/ +(complemento)
 complemento:
 - imaginate.png
 - index.jpeg
 - main.html
 - apps/prueba1
 - apps/Nombre=TuNombre
 - apps/Cuadrado=2
#Construido con
-Maven 
#Autor
-Italo Orlando Cufiño Novoa
