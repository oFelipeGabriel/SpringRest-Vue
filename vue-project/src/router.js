import Vue from 'vue'
import Router from 'vue-router'
import Home from './views/Home.vue'
import store from './store';

Vue.use(Router)

const router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home
    },
    {
      path: '/Admin',
      name: 'admin',
      component: () => import('./views/Admin.vue')
    },
    {
      path: '/Login',
      name: 'login',
      component: () => import('./views/Login.vue')
    },
    {
      path: '/User',
      name: 'user',
      component: () => import('./views/User.vue')
    },
    {
      path: '/Usuario',
      name: 'usuario',
      component: () => import('./views/Usuario.vue')
    },
    {
    path: '/Estoque',
    name: 'estoque',
    component: () => import('./views/Estoque.vue')
  },
  {
    path: '/Logout',
    name: 'logout',
  },
  ]

})

router.beforeEach((to, from, next) => {
  if (to.fullPath === '/Admin') {
    if (!store.state.temToken) {
      next('/login');
    }
  }
  else if (to.fullPath === '/Gerenciar') {
    if (!store.state.temToken) {
      next('/login');
    }
  }
  else if (to.fullPath === '/Logout') {
    if (!store.state.temToken) {
      store.commit('logout')
      next('/login');
    }
  }


  next();
});

export default router;
