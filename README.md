<!-- markdownlint-disable MD041 -->
<!-- markdownlint-disable MD033 -->
<!-- markdownlint-disable MD032 -->
<!-- markdownlint-disable MD022 -->

<img src="https://github.com/PoliAcho/AppLiteralura_dePoli/assets/127801323/c6194df4-e25a-40d2-90d9-154a7ea7092c" alt="imagen de Portada" style="width: 100%;">

El desafío consiste en crear una aplicación usando los conocimientos recibidos hasta el momento en los cursos anteriores.
Dicha aplicación debe implementar el siguiente menú:

<p align="center">
  <img src="https://github.com/PoliAcho/AppLiteralura_dePoli/assets/127801323/937166ce-5da1-4cda-be58-38d68f0524ac" alt="Propuesta" style="width: 40%;">
</p>

## Descripción de las funcionalidades a cumplir

* En la opción 1 (la más compleja a mi parecer) se solicita al usuario ingrese el nombre del libro (o autor) a buscar. Para satisfacer esa requisitoria, la lógica de programación se divide en dos partes:
  * En la primera debemos conectarnos a la API Gutendex para obtener datos del libro/autor solicitado.
  * En la segunda deberemos conectarnos a una base de datos (PostgreSQL) para registrar esos datos.
* La opción 2 consulta la base de datos para mostrar, al usuario, lo solicitado: los libros que se registraron.  
* La opción 3, del mismo modo, consulta la bases de datos para mostrar los autores que se registraron.
* La opción 4 solicita al usurario que ingrese un año para mostrar los autores (registrados) que estuvieron vivos en dicho año.
* La opción 5 lista los idiomas habilitados y solicita se ingrese un idioma para listar los libros registrados que están en es idioma.
  
En todas las opciones se debe realizar un control (mínimo) de que lo ingresado por el usuario es admisible por la aplicación.
Si bien la aplicación cumple con estas cinco funcionalidades que propuso el instructor Eric Monné Fraga de Olivera, y  que él califica de sencillas, he querido dar un pasito más. Atendiendo al constante incentivo de la instructora Génesys Rondón, agruegué una opción mas, que me costó, pero me deja más satisfecho.

* Opción 6. Lista los autores registrados y pide se elija uno para listar los libros de su autoría que estén en la bases de datos.

### Mejoras a la propuesta inicial

La opción 1 registraba el primer libro que proveía la API Gutendex. Quise que el usuario (y yo mismo) tuviera más opciones. Por ello ahora se podrian mostrar todos los libros que devuelva la API, ordenados por los que tienen el mayor número de descargas. Por razones prácticas lo limité a solo doce; y el usuario tendrá la pobilidad de elegir cuál libro registrar o de no registrar ninguno.

En los listados de los libros, el idioma estaba dado por solo un código que un usuario puede desconocer. A fin de solucionar ese inconveniente se implementa la tabla idiomas (con 2 campos: id y detalle) que contiene los 184 idiomas de la norma ISO-639. Adjunto el script 'idiomasTablaISO639.sql' que deberá correrse al momento de crearse la base de datos (1). Esta mejora puede apreciarse claramente en la opción 5.

Agregé la opción 6 (un desafío personal) como ya mencioné anteriormente.

## Capturas y comentarios

En esta imagen se muestra como está estructurada la aplicación.
La clase inicial LiteraluraApplication en la raiz, tal como lo hace Spring Initializr.
Tomé como modalidad personal procurar que los nombres de los paquetes y las clases se limiten a cuatro letras, anteponiendo la inicial del tipo de clase.

<img src="https://github.com/PoliAcho/AppLiteralura_dePoli/assets/127801323/533d90fe-869b-4563-8329-358892cbb948" alt="Estructura" style="width: 40%;">

### Opción 1 e ingreso de nombre del libro a buscar

<img src="https://github.com/PoliAcho/AppLiteralura_dePoli/assets/127801323/303801bf-3341-435e-9b4c-3445fee821b8" alt="Opción1a_libro" style="width: 35%;">
<img src="https://github.com/PoliAcho/AppLiteralura_dePoli/assets/127801323/7e0872c3-54e4-48ae-85a6-8b435c9fe37c" alt="Opción1b_libro" style="width: 35%;">

### Opción 1 e ingreso de nombre del autor a buscar

<img src="https://github.com/PoliAcho/AppLiteralura_dePoli/assets/127801323/83946444-26d6-40ac-9dbd-8f276d7fe8e1" alt="Opción1a_autor" style="width: 35%;">
<img src="https://github.com/PoliAcho/AppLiteralura_dePoli/assets/127801323/b98af98a-956c-4df9-86cd-4dfe3ffd43ee" alt="Opción1b_autor" style="width: 35%;">

### Opción 2 y lista de libros

<img src="https://github.com/PoliAcho/AppLiteralura_dePoli/assets/127801323/a626b2ad-aad4-4517-939f-70f3673e24f7" alt="Opción2a" style="width: 35%;">
<img src="https://github.com/PoliAcho/AppLiteralura_dePoli/assets/127801323/bc7bb4f9-cf53-44ee-8420-c82de64da96c" alt="Opción2b" style="width: 35%;">

### Opción 3 y lista de autores

<img src="https://github.com/PoliAcho/AppLiteralura_dePoli/assets/127801323/c5c146f7-f466-4d1a-96bc-c6b222a87f66" alt="Opción3a" style="width: 35%;">
<img src="https://github.com/PoliAcho/AppLiteralura_dePoli/assets/127801323/289b240f-1eed-45ed-b7bf-512b4a3fd4aa" alt="Opción3b" style="width: 35%;">

### Opción 4 e ingreso del año

<img src="https://github.com/PoliAcho/AppLiteralura_dePoli/assets/127801323/26882013-9719-4106-99af-c3dacf4ae304" alt="Opción4a" style="width: 35%;">
<img src="https://github.com/PoliAcho/AppLiteralura_dePoli/assets/127801323/c17efa87-b459-4d9b-9421-0dfe372bae66" alt="Opción4b" style="width: 35%;">

### Opción 5 e ingreso de opciones de idiomas diversos (nótese los títulos en japones y chino)

<img src="https://github.com/PoliAcho/AppLiteralura_dePoli/assets/127801323/2b040c07-b03a-4672-a8a0-f77fac24012e" alt="Opción5a" style="width: 35%;">
<img src="https://github.com/PoliAcho/AppLiteralura_dePoli/assets/127801323/ca49874c-8eb7-4f2a-bc98-a5095b0053bf" alt="Opción5b" style="width: 35%;">
<img src="https://github.com/PoliAcho/AppLiteralura_dePoli/assets/127801323/ce8df7ce-5801-4047-bbc8-642ff4c4cbff" alt="Opción5c" style="width: 35%;">
<img src="https://github.com/PoliAcho/AppLiteralura_dePoli/assets/127801323/f5cc0d3b-763e-404f-906c-71dd05bba09c" alt="Opción5d" style="width: 35%;">

### Opción 6, menú de autores y lista de libros

<img src="https://github.com/PoliAcho/AppLiteralura_dePoli/assets/127801323/0a308835-932d-4685-a46a-ff5e092743d7" alt="Opción6a" style="width: 30%;">
<img src="https://github.com/PoliAcho/AppLiteralura_dePoli/assets/127801323/6ec0571c-7d6c-4d4c-b705-cf3de02fec78" alt="Opción6b" style="width: 30%;">
<img src="https://github.com/PoliAcho/AppLiteralura_dePoli/assets/127801323/3df6117c-f9cf-4a63-809b-0c8edeb9aecc" alt="Opción6c" style="width: 30%;">

### Opción 0 y salida de la aplicacíon

<img src="https://github.com/PoliAcho/AppLiteralura_dePoli/assets/127801323/b49e05ba-3b24-4479-b104-c94203859dce" alt="Opción9a" style="width: 35%;">
<img src="https://github.com/PoliAcho/AppLiteralura_dePoli/assets/127801323/73e91912-781a-41ea-b3c0-df14f60d0cdd" alt="Opción9b" style="width: 35%;">

<!-- markdownlint-enable MD033 -->

* La aplicación se desarrollo en Java SE17, en el IDE IntelliJ.
  
* (1) Para hacer corre el script 'idiomasTablaISO639.sql', una vez creada la base de datos debe hacer clikDerecho sobre la misma y seleccionar 'Query Tool'. Desde allí, con Alt+o podrá abrir el script. Hágalo correr presionando F5 y se creará la tabla 'idioma' con los campos 'id' y 'detalle', y registrarán los 184 idiomas ISO-639.
Concluida la creación de la tabla, podrá ver en la aplicación los nombres completos de los idiomas.
