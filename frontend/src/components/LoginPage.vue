<template>
  <div class="login-container">
    <h2>Iniciar Sesión</h2>
    <form @submit.prevent="login">
      <p><input v-model="credentials.username" placeholder="Nombre de usuario" required /></p>
      <p><input type="password" v-model="credentials.password" placeholder="Contraseña" required /></p>
      <button type="submit">Iniciar Sesión</button>
      <p v-if="loginError" class="error-message">{{ loginError }}</p>
    </form>

    <button @click="toggleRegister">{{ showRegister ? 'Cancelar Registro' : 'Regístrate' }}</button>

    <div v-if="showRegister" class="register-container">
      <h2>Registrarse</h2>
      <form @submit.prevent="register">
        <p><input v-model="newUser.username" placeholder="Nombre de usuario" required /></p>
        <p><input type="password" v-model="newUser.password" placeholder="Contraseña" required /></p>
        <button type="submit">Registrarse</button>
        <p v-if="registerMessage">{{ registerMessage }}</p>
      </form>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'LoginPage',
  data() {
    return {
      credentials: {
        username: '',
        password: ''
      },
      newUser: {
        username: '',
        password: ''
      },
      showRegister: false,
      loginError: '', // Mensaje de error para el inicio de sesión
      registerMessage: '' // Mensaje de éxito o error para el registro
    };
  },
  methods: {
    toggleRegister() {
      this.showRegister = !this.showRegister;
      this.registerMessage = ''; // Resetea el mensaje de registro al alternar
    },
    login() {
      // Lógica para iniciar sesión
      axios.post('http://localhost:8080/auth/login', this.credentials)
        .then(response => {
            localStorage.setItem('token', response.data.token);
            this.$router.push('/'); // Redirigir a la página principal
        })
        .catch(error => {
            if (error.response) {
                this.loginError = error.response.data.message || "Error en inicio de sesión.";
            } else {
                this.loginError = "Error en la conexión.";
            }
        });
    },
    register() {
      // Lógica para registrarse
      axios.post('http://localhost:8080/auth/register', this.newUser)
        .then(() => {
          this.registerMessage = "Registro exitoso. Puedes iniciar sesión ahora.";
          this.newUser.username = ''; // Resetea el campo de nombre de usuario
          this.newUser.password = ''; // Resetea el campo de contraseña
        })
        .catch(error => {
          // Manejar el error de registro
          if (error.response) {
            this.registerMessage = error.response.data.message || "Error en registro.";
          } else {
            this.registerMessage = "Error en la conexión.";
          }
        });
    }
  }
};
</script>

<style scoped>
.login-container {
  max-width: 400px;
  margin: auto;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 5px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  background-color: #f9f9f9;
}

h2 {
  text-align: center;
}

.error-message {
  color: red;
}

.register-container {
  margin-top: 20px;
}
</style>
