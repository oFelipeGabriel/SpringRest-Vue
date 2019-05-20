<template>
  <div id="app">
    <nav id="nav" class="navbar has-shadow">
        <div class="container">
            <div class="navbar-brand">
                <a class="navbar-item brand-text" href="/"> GPP ALPHA BETA </a>
                <div class="navbar-burger burger" data-target="navMenu">
                    <span></span>
                    <span></span>
                    <span></span>
                </div>
            </div>
            <div id="navMenu" class="navbar-menu">
                <div class="navbar-start">
                    <router-link to="/" class="navbar-item">Home</router-link>
                    <router-link  v-if="usuario" to="/Produto" class="navbar-item">Produto</router-link> 
                    <a class="navbar-item" v-if="usuario" @click="logout">Logout</a>
                    <router-link to="/Admin" class="navbar-item">Gerenciar</router-link>
                    <router-link to="/Usuario" class="navbar-item">Usuarios</router-link>
                    <router-link v-if="!usuario" to="/login" class="navbar-item">Login</router-link>
                </div>
                
            </div>
        </div>
        
    </nav>
    <router-view/>
</div>

</template>

<script>
import { mapState } from 'vuex'
import { mapMutations } from 'vuex'
export default {
  name: 'app',
  computed: {
    ...mapState([
      'usuario'
    ])
  },
  methods: {
    ...mapMutations([
      'logout'
    ])
  }
}
</script>
