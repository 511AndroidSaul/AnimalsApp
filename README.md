# Animales App

Esta aplicación móvil permite explorar una lista de animales y sus respectivos ambientes. Los datos provienen de la API [Animals API](https://animals.juanfrausto.com/api/), y la app proporciona una navegación intuitiva para ver detalles de cada animal y ambiente, además de mostrar información adicional como galerías de imágenes y hechos interesantes.

## Requerimientos

### Funcionalidades:

1. **Pantalla 1: Lista de Animales**
    - Muestra todos los animales disponibles obtenidos desde la API.
    - Cada elemento debe incluir:
        - Imagen principal del animal.
        - Nombre del animal.
    - Al hacer clic en un animal, se debe navegar a la **Pantalla de Detalle del Animal**.

2. **Pantalla 2: Lista de Ambientes**
    - Muestra todos los ambientes disponibles obtenidos desde la API.
    - Cada elemento debe incluir:
        - Imagen del ambiente.
        - Nombre del ambiente.
    - Al hacer clic en un ambiente, se debe navegar a la **Pantalla de Detalle del Ambiente**.

3. **Pantalla 3: Detalle del Animal**
    - Muestra toda la información del animal seleccionado:
        - Nombre del animal.
        - Imagen principal.
        - Descripción.
        - Galería de imágenes (puede ser un carrusel).
        - Listado de hechos interesantes sobre el animal.

4. **Pantalla 4: Detalle del Ambiente**
    - Muestra toda la información del ambiente seleccionado:
        - Imagen del ambiente.
        - Descripción.
        - Lista de animales que viven en ese ambiente, incluyendo:
            - Nombre del animal.
            - Imagen principal del animal.

### Barra de Navegación:
- **Bottom Navigation Bar** para alternar entre:
    - **Pantalla de Animales**.
    - **Pantalla de Ambientes**.

---

## Requisitos

### Requisitos técnicos:

- **Android Studio** para el desarrollo de la aplicación.
- **Kotlin** como lenguaje de programación.
- **Jetpack Compose** para la creación de la interfaz de usuario.
- **Retrofit** para hacer las solicitudes a la API.
- **Coil** para cargar las imágenes de manera eficiente.
- **API**: [Animals API](https://animals.juanfrausto.com/api/animals).

### Dependencias:
- Retrofit: `implementation 'com.squareup.retrofit2:retrofit:2.9.0'`
- Gson Converter: `implementation 'com.squareup.retrofit2:converter-gson:2.9.0'`
- Coil: `implementation 'io.coil-kt:coil-compose:2.1.0'`
- Jetpack Compose: `implementation "androidx.compose.ui:ui:1.1.0"`

---

## Estructura del Proyecto

La estructura de la aplicación está organizada en las siguientes carpetas:

- **screens**: Contiene las pantallas principales, como la lista de animales, la lista de ambientes, y las pantallas de detalle.
- **models**: Contiene las clases de datos que representan los animales y los ambientes.
- **services**: Contiene las interfaces de Retrofit que definen las solicitudes a la API.
- **ui**: Contiene los elementos reutilizables de la interfaz, como botones, imágenes y más.

---

## Configuración de la API

El acceso a los datos de los animales y los ambientes se realiza mediante las siguientes URLs:

- **Animales**: [https://animals.juanfrausto.com/api/animals](https://animals.juanfrausto.com/api/animals)
- **Ambientes**: [https://animals.juanfrausto.com/api/ambients](https://animals.juanfrausto.com/api/ambients)

Cada respuesta es un JSON que contiene información detallada de los animales y los ambientes. Los animales incluyen datos como nombre, descripción, imagen, y hechos interesantes. Los ambientes incluyen nombre, descripción, y lista de animales que habitan en ellos.

---

## Instalación y Uso

### 1. Clonar el repositorio:
- git clone https://github.com/tu-usuario/animals-app.git

### 2. Abrir el proyecto en Android Studio:
   - Abre Android Studio y selecciona "Open an existing project".
   - Navega al directorio donde clonaste el repositorio y selecciona el proyecto.

### 3. Instalar dependencias:
   - Android Studio debería instalar automáticamente las dependencias necesarias al sincronizar el proyecto. Si no es así, ve a **File > Sync Project with Gradle Files**.

### 4. Ejecutar la aplicación:
   - Conecta un dispositivo Android o usa un emulador.
   - Haz clic en **Run** o usa el atajo **Shift + F10** para ejecutar la aplicación.

---


