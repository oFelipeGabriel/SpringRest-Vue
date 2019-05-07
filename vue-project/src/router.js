import Vue from 'vue'
import Router from 'vue-router'
import Home from './views/Home.vue'

Vue.use(Router)

export default new Router({
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
    
  ]
})
