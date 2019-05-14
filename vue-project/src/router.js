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
      path: '/Produto',
      name: 'produto',
      component: () => import('./views/Produto.vue')
    }, 
  ]
  
})

router.beforeEach((to, from, next) => {
  if (to.fullPath === '/User') {
    if (!store.state.temToken) {
      next('/login');
    }
  }
  next();
});

export default router;

