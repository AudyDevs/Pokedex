<h1 align="center">Pokedex</h1>

<p align="center">  
  La Pokedex es una aplicación que nos permite ver una lista de Pokemons de todas las generaciones y tipos. Se puede filtrar y ver en detalle las estadísticas de cada uno de ellos.
</p>
<p align="center">   
  Aplicicación Android basada en la arquitectura MVVM desarrollada con DaggerHilt, StateFlows, ViewModels, Corrutinas, Room, Retrofit y Testing
</p>

## 🛠 Herramientas y librerias
- Basado en lenguaje [Kotlin](https://kotlinlang.org/) con una interfaz en XML
- Arquitectura MVVM (Model-View-ViewModel)
- ViewModel y StateFlow: Nos permite almacenar el estado y realizar cambios de forma reactiva en la interfaz de usuario.
- Lifecycle: Observador de los ciclos de vida de Androrid. Los usamos para recolectar los cambios de estado en el StateFlow para modificar la interfaz del usuario.
- Room: Base de datos local sobre SQLite para permitirnos un acceso fluido, eficiente y seguro.
- [Retrofit2](https://github.com/square/retrofit): Cliente de HTTP para conexiones de red. Nos permite hacer consultas API-REST.
- [Gson](https://github.com/google/gson): Nos permite convertir un formato JSON a un objeto Kotlin.
- [Coroutines](https://github.com/Kotlin/kotlinx.coroutines): Manejo de tareas asíncronas, usado para no bloquear el hilo principal de la aplicación mientras se espera la respuesta de la consulta.
- [Glide](https://github.com/bumptech/glide): Nos permite representar imagenes a traves de una URL.
- [Lottie](https://github.com/airbnb/lottie-android): Nos permite representar animaciones a través de un JSON.
- [Dagger Hilt](https://dagger.dev/hilt/) para inyección de dependencias.
- Navigation component: Es una parte de la suite de Jetpack que simplifica la implementación de la navegación en las Activities y los Fragments.
- Datastore preferences: Nos permite almacenar datos en local de forma asíncrona. Ideal para guardar preferencias de usuario y configuraciones de la aplicación.
- Testing
- Código con Clean Code y Clean Architecture

## ☁ Open API
<img src="https://user-images.githubusercontent.com/24237865/83422649-d1b1d980-a464-11ea-8c91-a24fdf89cd6b.png" align="right" width="21%"/>

[PokeAPI](https://pokeapi.co/) proporciona una interfaz API RESTful con objetos muy detallados creados a partir de miles de líneas de datos relacionados con Pokémon.

## 📱 Capturas
| Main | Menu 1 | Menu 2 |
|--|--|--|
| <img src="" width="245" height="500"> | <img src="" width="245" height="500"> | <img src="" width="245" height="500">

## 👇 Descargar 👇
Ir a [Releases](https://github.com/AudyDevs/Pokedex/releases) para descargar el último APK.
