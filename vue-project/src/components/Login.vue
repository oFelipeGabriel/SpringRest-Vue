<template>
  <form class="hero is-fullheight" @submit.prevent="login">
          <div class="hero-body">
              <div class="container has-text-centered">
                  <div class="column is-4 is-offset-4">
                      <h3 class="title has-text-grey">Login</h3>
                      <p class="subtitle has-text-grey">Faça login para continuar</p>
                      <img class="is-rounded" style="border-radius: 50%; margin-bottom: 20px" src="../assets/login.gif">
                      <div class="box">
                          
                          <form>
                              <div class="field">
                                  <div class="control">
                                      <input class="input is-large" name="usernmae" type="text" placeholder="Your Username" required autofocus v-model="nome">
                                  </div>
                              </div>

                              <div class="field">
                                  <div class="control">
                                      <input class="input is-large" name="password" type="password" placeholder="Your Password" required v-model="senha">
                                  </div>
                              </div>
                  
                              <button type="submit" class="button is-block is-success is-large is-fullwidth">Login</button>
                          </form>
                      </div>
                      <p class="has-text-grey">
                      
                          <a href="../">Precisa de ajuda?</a>
                      </p>
                  </div>
              </div>
          </div>
  </form>
</template>

<script>
import axios from 'axios'
import { mapMutations } from 'vuex'

export default {
  name: 'login',
  data() {
    return {
      nome: '',
      senha: ''
    }
  },
  methods: {
    ...mapMutations([
      'setUsuario',
      'setToken'
    ]),
    login() {
      axios.post('login',
          {
            username: this.nome,
            password: this.senha
          })
        .then(res => {
          console.log(res)
          this.setUsuario(res.data)
          this.setToken(res.headers.token)
          this.$router.push('/')
        })
        .catch(error => console.log(error))
    }
  }
}
</script>