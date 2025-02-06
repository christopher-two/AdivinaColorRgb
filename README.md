# Color Guessing Game

![App Screenshot](screenshot.png)

## Descripción

**Color Guessing Game** es un mini juego desarrollado con Jetpack Compose que reta al usuario a adivinar un color aleatorio mediante la modificación de sus valores RGB. La aplicación genera un color objetivo aleatorio, el usuario intenta reproducirlo ajustando tres campos de entrada para cada componente RGB, y luego se le indica qué tan cerca estuvo de acertar, mostrando un porcentaje de coincidencia.

## Características

- **Generación de color aleatorio:** Al presionar el botón "Generar Color", se selecciona un color objetivo de forma aleatoria.
- **Adivinanza del color:** El usuario ajusta los valores de rojo, verde y azul mediante TextFields personalizados.
- **Retroalimentación:** Se calcula y muestra un porcentaje que indica qué tan cercano está el color armado por el usuario al color objetivo.
- **Interfaz adaptativa:** Los TextFields se adaptan visualmente (colores de borde, texto y placeholder) para mantener la legibilidad según el color de fondo.

## Captura de pantalla

Asegúrate de colocar la imagen de la aplicación en la carpeta raíz (o en una subcarpeta) y nombrarla, por ejemplo, `screenshot.png`.

![Captura de pantalla de la aplicación](img.png)

## Tecnologías utilizadas

- **Kotlin**
- **Jetpack Compose** (o Compose Multiplatform, según el entorno)
- **StateFlow** para la gestión de estado

## Instalación y ejecución

### Requisitos

- [Android Studio](https://developer.android.com/studio) o [IntelliJ IDEA](https://www.jetbrains.com/idea/)
- JDK 11 o superior
- Gradle

### Pasos

1. **Clonar el repositorio:**

   ```bash
   gh repo clone christopher-two/AdivinaColorRgb
