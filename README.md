# CRUD de Clientes con Spring Boot y Vue.js

Este proyecto es una aplicación web que permite gestionar clientes a través de un CRUD (Crear, Leer, Actualizar, Eliminar). Está desarrollado usando **Spring Boot** para el backend y **Vue.js** para el frontend.

## Características

- **Inicio de sesión y registro de usuarios**: Para acceder al CRUD, los usuarios deben iniciar sesión. También es posible registrarse para crear nuevas cuentas.
- **Gestión de clientes**:
  - Crear nuevos clientes.
  - Ver la lista de todos los clientes.
  - Buscar clientes por ID.
  - Editar la información del cliente (dirección, correo, teléfono).
  - Eliminar clientes.
- **Protección de rutas**: El acceso al CRUD está protegido. Solo los usuarios autenticados pueden acceder a las rutas protegidas.

## Estructura del Proyecto

### Backend - Spring Boot

El backend es un API REST que proporciona los endpoints para la gestión de clientes y la autenticación de usuarios. Está desarrollado con **Spring Boot** y **Maven**.

#### Endpoints principales:

- `POST /auth/login`: Inicio de sesión.
- `POST /auth/register`: Registro de usuarios.
- `GET /clientes`: Obtener todos los clientes.
- `GET /clientes/{id}`: Obtener un cliente por ID.
- `POST /clientes`: Crear un nuevo cliente.
- `PUT /clientes/{id}`: Actualizar la información de un cliente.
- `DELETE /clientes/{id}`: Eliminar un cliente.

### Seguridad

Se implementa un sistema de autenticación por tokens **JWT**, y las contraseñas están encriptadas para mayor seguridad.

### Frontend - Vue.js

El frontend está construido con **Vue.js**. Los componentes principales son:

#### Componentes

- **LoginPage.vue**: Página de inicio de sesión y registro.
  - Los usuarios pueden iniciar sesión o registrarse.
  - Después de iniciar sesión, se guarda el token en `localStorage`.
  - Se valida la autenticación antes de acceder a las rutas protegidas.

- **ListaClientes.vue**:
  - Muestra la lista de clientes en una tabla.
  - Permite buscar clientes por ID.
  - Incluye opciones para ver, editar y eliminar clientes.

- **FormularioCliente.vue**:
  - Formulario para agregar nuevos clientes.
  - Se redirige a la lista de clientes después de guardar un nuevo cliente.

### Navegación y Rutas

La configuración de las rutas se encuentra en el archivo `index.js`. Las rutas protegidas requieren autenticación, y si el usuario no está autenticado, es redirigido a la página de inicio de sesión.

```javascript
const routes = [
  { path: '/', name: 'ListaClientes', component: ListaClientes, meta: { requiresAuth: true }},
  { path: '/formulario-cliente', name: 'FormularioCliente', component: FormularioCliente, meta: { requiresAuth: true }},
  { path: '/login', name: 'LoginPage', component: LoginPage }
];
```

## Requisitos del sistema

- **Java 11** o superior.
- **JDK 21** o superior
- **Maven** para gestionar dependencias en el backend.
- **Node.js** y **npm** para gestionar dependencias en el frontend.
- **Vue.js** para manejar todo el forntend

## Instrucciones para ejecutar el proyecto
De preferencia sugiero primero ejecutar el backend para no tener problemas con los puertos, ya que el backend esta confiugurado para usar el puerto 8080 
y el frontend se adapta para tomar el siguiente puerto si el 8080  esta ocupado, en este caso usaría el puerto 8081

### Backend (Spring Boot)

1. Clonar el repositorio.
2. Navegar al directorio del backend.
3. Ejecutar el siguiente comando para compilar y ejecutar el backend:

    ```bash
    mvn spring-boot:run
    ```

4. El servidor estará disponible en `http://localhost:8080`.

### Frontend (Vue.js)

1. Navegar al directorio del frontend.
2. Instalar las dependencias con:

    ```bash
    npm install
    ```

3. Ejecutar el servidor de desarrollo con:

    ```bash
    npm run serve
    ```

4. La aplicación estará disponible en `http://localhost:8081`.

## Funcionalidades de autenticación

- **Login**: Los usuarios deben autenticarse para acceder al sistema.
- **Token JWT**: El token se guarda en `localStorage` y se usa para proteger las rutas del frontend.
- **Logout**: Al cerrar sesión, el token se elimina y el usuario es redirigido a la página de login.

## Contacto

Para cualquier duda o consulta, puedes contactarme a través de mi correo electrónico: fernando.camacho@udla.edu.ec
