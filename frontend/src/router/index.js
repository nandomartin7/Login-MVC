import { createRouter, createWebHistory } from 'vue-router';
import ListaClientes from '../components/ListaClientes.vue';
import FormularioCliente from '../components/FormularioCliente.vue';

const routes = [
  {
    path: '/',
    name: 'ListaClientes',
    component: ListaClientes
  },
  {
    path: '/formulario-cliente',
    name: 'FormularioCliente',
    component: FormularioCliente
  }
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
});

export default router;