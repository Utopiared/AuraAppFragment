# AuraApp — Módulo de Autenticación y Bienvenida (Activity única + Fragments)

Proyecto Android nativo (Kotlin + Views/Material Components 3, sin Jetpack Compose) listo para abrir en Android Studio.

## Cómo abrir el proyecto
1. Descomprime `AuraApp.zip`.
2. Android Studio → **File > Open** → selecciona la carpeta `AuraApp`.
3. Espera a que Gradle sincronice (requiere internet la primera vez).
4. **Run > Run 'app'** en un emulador/dispositivo con Android 8.0 (API 26) o superior.

## Credenciales de prueba
- **Usuario:** `user@tic.unam.mx`
- **Contraseña:** `d1pl0m0v1l35?`

## Arquitectura (Activities → Fragments)

- **`MainActivity`** (`AppCompatActivity`): **única Activity** de la app. Infla
  `res/layout/activity_main.xml` (un `FragmentContainerView`) y, solo en el
  primer arranque (`savedInstanceState == null`), carga `LoginFragment`. No
  contiene lógica de UI ni de validación.

- **`LoginFragment`**: infla **su propio layout**, `res/layout/fragment_login.xml`
  (`TextInputLayout`/`TextInputEditText` para correo y contraseña —el ícono
  de mostrar/ocultar contraseña usa el `endIconMode="password_toggle"` nativo
  de Material Components— y un `MaterialButton` de bordes rectos). Usa
  **View Binding** (`FragmentLoginBinding`) y conserva íntegramente la lógica
  de las prácticas anteriores: validación de campos vacíos, formato de correo
  (`Patterns.EMAIL_ADDRESS`), credenciales exactas, y botón deshabilitado
  hasta llenar ambos campos (`TextWatcher`). Al autenticar con éxito, navega
  mediante una **transacción de `FragmentManager`**
  (`parentFragmentManager.beginTransaction()...replace(...)`).

- **`WelcomeFragment`**: infla **su propio layout**, `res/layout/fragment_welcome.xml`.
  Recibe el correo autenticado como **parámetro a través de los argumentos
  del Fragment** (`arguments = bundleOf(...)`, expuesto por
  `WelcomeFragment.newInstance(email)`) — nunca mediante variables globales,
  `Intent` ni una segunda Activity — y lo usa para personalizar el mensaje
  de bienvenida (`welcome_message_personalized`).

- **Navegación**: exclusivamente con `FragmentManager` y transacciones de
  Fragments (`beginTransaction().replace(...).addToBackStack(...).commit()`).
  Las animaciones de transición (fade + slide, 500 ms, interpolador
  `fast_out_slow_in`) están en `res/anim/` y se aplican con
  `setCustomAnimations`.

## Configuración técnica
- Kotlin + Android Views + Material Components 3 (`com.google.android.material:material`)
- `AppCompatActivity` + `androidx.fragment:fragment-ktx`
- **View Binding** habilitado (`buildFeatures.viewBinding = true`) — sin Jetpack Compose
- `minSdk` = 26, `compileSdk` = 34, `targetSdk` = 34
- Gradle con Kotlin DSL (`build.gradle.kts`)

## Estructura relevante
```
AuraApp/
├── build.gradle.kts
├── settings.gradle.kts   (rootProject.name = "AuraApp")
└── app/
    ├── build.gradle.kts
    └── src/main/
        ├── AndroidManifest.xml            (declara MainActivity como única LAUNCHER)
        ├── java/mx/unam/tic/asistentemoda/
        │   ├── MainActivity.kt        (Activity contenedora)
        │   ├── LoginFragment.kt       (Fragment de inicio de sesión)
        │   └── WelcomeFragment.kt     (Fragment de bienvenida)
        └── res/
            ├── layout/
            │   ├── activity_main.xml      (FragmentContainerView)
            │   ├── fragment_login.xml     (layout propio del LoginFragment)
            │   └── fragment_welcome.xml   (layout propio del WelcomeFragment)
            ├── color/                                  (estados habilitado/enfocado)
            │   ├── button_container_color.xml
            │   ├── button_text_color.xml
            │   ├── text_input_box_stroke_color.xml
            │   └── text_input_hint_color.xml
            ├── anim/                                   (animaciones de transacción)
            │   ├── enter_fade_slide_up.xml
            │   ├── exit_fade_slide_left.xml
            │   ├── pop_enter_fade_slide_right.xml
            │   └── pop_exit_fade_slide_down.xml
            ├── drawable/ic_scan_frame.xml, ic_launcher_background.xml, ic_launcher_foreground.xml
            ├── mipmap-anydpi/ic_launcher.xml, ic_launcher_round.xml
            ├── values/colors.xml, strings.xml, themes.xml
            ├── values-en/strings.xml
            └── xml/backup_rules.xml, data_extraction_rules.xml
```

## Notas de implementación
- Cada Fragment tiene su propio archivo de layout XML:
  `fragment_login.xml` y `fragment_welcome.xml`.
- Todas las cadenas de texto siguen en `strings.xml` (español) y
  `values-en/strings.xml` (inglés) — internacionalización intacta.
- El nombre visible de la app ("AuraApp") y el título editorial de la
  pantalla de login ("Radiante y Bella") se conservan de la práctica
  anterior, igual que la corrección del hint duplicado en los campos de
  correo y contraseña.
- No se utiliza ninguna variable global para compartir datos entre
  Fragments: el único canal de comunicación es el `Bundle` de argumentos
  del Fragment de destino.
