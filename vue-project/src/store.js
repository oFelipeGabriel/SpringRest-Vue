import Vue from 'vue'
import Vuex from 'vuex'
import VuexPersist from 'vuex-persist'
import router from './router';


Vue.use(Vuex)

const vuexPersist = new VuexPersist({
  key: 'my-sec-app',
  storage: localStorage
})

export default new Vuex.Store({
  plugins: [
    vuexPersist.plugin
  ],
  state: {
    usuario: null,
    token: null,
    temToken: false
  },
  mutations: {
    setUsuario (state, usuario) {
      state.usuario = usuario
    },
    setToken (state, token)  {
      state.token = token
      state.temToken = true
    },
    logout (state) {
      state.token = null
      state.usuario = null
      state.temToken = false
      router.push('/login');
    }
  },
  actions: {
    temAcesso:()=>{
      localStorage.getItem('temToken')
    }
  }
})
