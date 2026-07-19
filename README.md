# 📱 Práctica: Migración de Activities a Fragments

## 📌 Objetivo

Se realizaron las modificaciones necesarias para reemplazar las pantallas implementadas mediante **Activities** por **Fragments**, manteniendo exactamente el mismo funcionamiento de la aplicación para el usuario.

---

# ✅ Funcionalidades Conservadas

La aplicación continúa permitiendo:

- 🔐 Inicio de sesión utilizando las credenciales de la práctica anterior.
- 👋 Mostrar una pantalla de bienvenida después de una autenticación exitosa.
- ✔️ Mantener todas las validaciones implementadas previamente.
- 🌎 Conservar la internacionalización (idiomas) desarrollada anteriormente.
- 🎨 Mantener el mismo diseño e interfaz gráfica.

---

# 🏗️ Estructura de la Aplicación

## 📍 Activity Principal

La aplicación ahora cuenta con una única **MainActivity**, cuya única responsabilidad es:

- Alojar el contenedor (`FragmentContainerView`).
- Mostrar inicialmente el **LoginFragment**.
- Administrar la navegación entre Fragments.

> **Nota:** La MainActivity ya no contiene lógica relacionada con el inicio de sesión o la pantalla de bienvenida.

---

## 🔐 LoginFragment

La pantalla de autenticación fue migrada completamente a un **Fragment**.

### Funcionalidades

- Captura de usuario y contraseña.
- Validación de credenciales.
- Validaciones de campos vacíos.
- Envío de información al Fragment de bienvenida.
- Navegación mediante `FragmentManager`.

---

## 👋 WelcomeFragment

La pantalla de bienvenida fue implementada como un segundo **Fragment**.

### Funcionalidades

- Recepción de parámetros enviados desde el LoginFragment.
- Visualización del mensaje de bienvenida.
- Conservación del diseño desarrollado en la práctica anterior.

---

# 🔄 Navegación

La navegación entre pantallas se realiza mediante:

- `FragmentManager`
- `FragmentTransaction`

### Flujo de navegación

```text
MainActivity
      │
      ▼
LoginFragment
      │
 Validación correcta
      │
      ▼
WelcomeFragment
```

---

# 📋 Requerimientos Funcionales Cumplidos

| Requerimiento | Estado |
|--------------|:------:|
| Una única Activity | ✅ |
| Login implementado con Fragment | ✅ |
| Pantalla de bienvenida con Fragment | ✅ |
| Envío de parámetros entre Fragments | ✅ |
| Navegación mediante FragmentManager | ✅ |
| Conservación de validaciones | ✅ |
| Internacionalización | ✅ |
| Diseño anterior conservado | ✅ |

---

# 🚫 Restricciones Cumplidas

Durante el desarrollo se respetaron todas las restricciones establecidas.

| Restricción | Cumplimiento |
|-------------|:------------:|
| No utilizar una segunda Activity | ✅ |
| No navegar mediante Intents | ✅ |
| No utilizar variables globales | ✅ |
| No eliminar funcionalidades anteriores | ✅ |

---

# 📂 Arquitectura Final

```text
📦 App
│
├── 📄 MainActivity
│      │
│      ▼
│  FragmentContainerView
│
├── 🔐 LoginFragment
│      │
│      ▼
│  FragmentManager
│
└── 👋 WelcomeFragment
```

---

# ✅ Resultado

La aplicación fue refactorizada exitosamente para utilizar **Fragments** en lugar de múltiples **Activities**, manteniendo el mismo comportamiento funcional, respetando todas las restricciones establecidas y siguiendo las buenas prácticas de desarrollo en Android.
