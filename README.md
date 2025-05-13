# Conversor de Monedas - Java

Este proyecto es una aplicación de consola desarrollada en Java que permite convertir entre diferentes monedas utilizando una API de tipo de cambio. El diseño sigue el patrón MVC (Modelo-Vista-Controlador) para una mejor organización del código.

## Funcionalidades

* Conversión entre USD, ARS, BRL y COP.
* Consulta de tasas en tiempo real desde la API ExchangeRate.
* Historial de conversiones persistente (guardado en un archivo JSON).
* Formato de entrada amigable para usuarios.
* Lectura segura de la API Key desde un archivo `.env`.

## Estructura del proyecto

```
com.aluracursos.conversor
│
├── config                 # Lectura segura de variables de entorno
│   └── Config.java
│
├── controlador            # Lógica de negocio y servicios
│   ├── MonedaService.java
│   └── HistorialConversionService.java
│
├── modelo                 # Clases de datos y lógica relacionada
│   ├── CodigoMoneda.java
│   ├── ClienteServidor.java
│   ├── FormatoMonedaUtil.java
│   ├── Moneda.java
│   └── RegistroConversion.java
│
├── vista                  # Interacción con el usuario (consola)
│   └── Menu.java
│
└── principal              # Clase principal del programa
    └── Principal.java
```

## Requisitos

* Java 17 o superior
* [dotenv-java](https://github.com/cdimascio/dotenv-java) (para manejar variables de entorno)

## Configuración

1. Cloná el repositorio:

   ```bash
   git clone https://github.com/tu_usuario/conversor-monedas-java.git
   cd conversor-monedas-java
   ```

2. Creá un archivo `.env` en la raíz del proyecto:

   ```env
   API_KEY=tu_clave_api_de_exchangerate
   ```

3. Asegurate de tener la librería dotenv-java disponible (por ejemplo, desde Maven o IntelliJ):

```xml
<!-- pom.xml -->
<dependency>
  <groupId>io.github.cdimascio</groupId>
  <artifactId>dotenv-java</artifactId>
  <version>2.2.4</version>
</dependency>
```

4. Ejecutá el programa desde `Principal.java` o compilalo:

   ```bash
   javac -d out src/com/aluracursos/conversor/principal/Principal.java
   java -cp out com.aluracursos.conversor.principal.Principal
   ```

## Archivo de salida

El historial de conversiones se guarda automáticamente en un archivo `historial.json`, en el mismo directorio raíz del proyecto.

## Autora

* 💻 Paola — Estudiante de programación y testing

---

📌 **Nota**: El archivo `.env` está incluido en `.gitignore`, por lo tanto no se subirá al repositorio por seguridad.

---

¿Querés sugerir mejoras? ¡Estás invitado a contribuir!
