# 🦊 QuickFox News (ITQNoticiasApp)

Aplicación móvil nativa para Android desarrollada en Java para consultar noticias globales y tecnológicas en tiempo real, diseñada con un estilo de interfaz Arcade HUD.

---

## 📌 Acerca del Proyecto

**QuickFox News** es una aplicación cliente de noticias desarrollada para la comunidad del Instituto Tecnológico de Querétaro (ITQ) / TecNM. La aplicación obtiene encabezados y artículos de prensa en tiempo real utilizando servicios REST HTTP, mostrándolos en tarjetas dinámicas interactivas con actualización mediante deslizamiento (*Swipe-to-Refresh*) y una vista detallada mediante un navegador web integrado (*WebView*).

---

## 🚀 Características Principales

- **Pantalla de Inicio con Splash Screen Nativo**: Implementado con la API Jetpack `androidx.core:core-splashscreen` que muestra el logotipo e imagen de marca durante la carga inicial.
- **Interfaz Arcade HUD / Material Design 3**: Encabezado personalizado estilo marcador de videojuego y tarjetas con esquina redondeada y paleta de colores centralizada.
- **Consumo de Noticias en Tiempo Real**: Integración con servicios REST mediante Volley, procesando respuestas en formato JSON (soporta esquemas estándar de NewsAPI y feeds RSS2JSON de Google News).
- **Actualización por Deslizamiento (SwipeRefreshLayout)**: Recarga asíncrona de datos desde el servidor en cualquier momento.
- **Navegación WebView Integrada**: Abre la noticia original en una pantalla secundaria sin salir de la aplicación.
- **Arquitectura MVVM**: Separación clara de responsabilidades entre la capa de presentación (Activity / Adapter), la lógica de negocio (ViewModel) y la capa de datos (Repository / DTO).

---

## 🛠️ Arquitectura y Patrones de Diseño

El proyecto sigue las recomendaciones oficiales de arquitectura de Android:

- **Model-View-ViewModel (MVVM)**:
  - `MainActivity`: Maneja la interfaz de usuario y observa los cambios de estado mediante `LiveData`.
  - `NewsViewModel`: Retiene y expose el estado de la lista de noticias de manera reactiva, sobreviviendo a rotaciones de pantalla.
- **Repository Pattern**:
  - `NewsRepository`: Orquesta las solicitudes de red mediante Volley de forma asíncrona.
- **Data Transfer Object (DTO)**:
  - `ArticleDto`: Representa la estructura de un artículo de prensa con anotaciones `@Keep` para compatibilidad con R8/ProGuard.

---

## 📚 Librerías y Dependencias

| Librería / Componente | Versión | Descripción |
| :--- | :--- | :--- |
| **AndroidX AppCompat** | `1.6.1` | Compatibilidad de componentes de UI y temas. |
| **Google Material Components** | `1.11.0` | Componentes visuales bajo lineamientos Material Design. |
| **ConstraintLayout** | `2.1.4` | Diseño de interfaz flexible y optimizado. |
| **RecyclerView** | `1.4.0` | Renderizado eficiente de listas de elementos. |
| **CardView** | `1.0.0` | Tarjetas con elevación y bordes redondeados para las noticias. |
| **SwipeRefreshLayout** | `1.2.0` | Componente nativo para actualización de contenido. |
| **Lifecycle (ViewModel & LiveData)** | `2.11.1` | Gestión de estados reactivos y ciclo de vida de componentes. |
| **Volley** | `1.2.1` | Gestión de peticiones de red HTTP/HTTPS asíncronas. |
| **Core Splashscreen** | `1.0.1` | API nativa para la pantalla de bienvenida. |

---

## ⚙️ Requisitos del Sistema y Entorno de Desarrollo

- **JDK**: Java 17 (Java Toolchain configurado)
- **Compile SDK**: `35` (Android 15)
- **Target SDK**: `34` (Android 14)
- **Min SDK**: `24` (Android 7.0 Nougat o superior)
- **Android Gradle Plugin (AGP)**: `9.5.3`
- **Gradle**: `9.9.2`

---

## 🛠️ Cómo Compilar y Ejecutar el Proyecto

1. **Clonar o abrir el proyecto** en **Android Studio**.
2. **Sincronizar Gradle**: Android Studio descargará las dependencias especificadas en `gradle/libs.versions.toml`.
3. **Compilar el proyecto**:
   - Desde la terminal del IDE o PowerShell:
     ```bash
     ./gradlew app:assembleDebug
     ```
   - O usando la opción **Build > Make Project** en Android Studio.
4. **Ejecutar en Emulador o Dispositivo Físico**:
   - Selecciona el módulo `app` y presiona **Run (Shift + F10)**.

---

## 📄 Licencia y Créditos

Desarrollado para el proyecto escolar de **Noticias Rápidas TecNM / QuickFox News** - Instituto Tecnológico de Querétaro.
