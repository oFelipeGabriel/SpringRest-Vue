<template>

<!-- Início tela de Admin !-->
<div>
    <!-- Início caixa de busca !-->
   <v-parallax  height="300" src="https://cdn.vuetifyjs.com/images/parallax/material.jpg">

        <div class="hero-body">  
            <div align="center" class="container">
                
                <h1  align="center" style="font-size: 25px"> Pesquise algum item.. </h1>
                <div class="card">
                    <div class="card-content">
                        <div class="content">
                            <div class="control has-icons-left has-icons-right">
                                <input class="input is-large" type="search">
                                <span class="icon is-medium is-left">
                                    <i class="fa fa-search"></i>
                                </span>
                                <span class="icon is-medium is-right">
                                    <i class="fa fa-empire"></i>
                                </span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </v-parallax>
    <!-- Final caixa de busca !-->
    <div class="container">
			<div class="columns">
				<div class="column is-3">
                    <!-- Botão de add produto !-->
					<a class="button is-success is-block is-alt is-large" href="#">
                        <span class="icon">
                            <i class="fa fa-inbox"></i>
                        </span>
                        <span>Novo produto</span>
                    </a>
                    <br>
                    <!-- Botão de verificar produtos fora da validade !-->
                    <a class="button is-danger is-block is-alt is-large" href="#">
                       <span class="icon">
                            <i class="fa fa-inbox"></i>
                        </span>
                    
                        <span>Fora da Validade</span>
                    </a>
		
				</div>
				<div class="column is-8">
                    <h1 style="font-size:25px; padding-bottom: 15px" align="center"> Lista de items </h1>
                    <!-- Aplicar loop aqui!-->
					<div class="box content">
                        <table>
                            <tr v-for="produto in produtos" :key="produto.id">
                                <article  style="padding-bottom: 10px;" class="post has-shadow">
                                    <h3>{{ produto.nome }}</h3>
                                    <div class="media">
                                        <div class="media-content">
                                            <div class="content">
                                                <p style="font-size: 18px"> Fornecedor:
                                                    {{ produto.fornecedor }} 
                                                    <br> 
                                                    Validade: {{ produto.validade }} </p>
                                            </div>
                                        </div>
                                        <!-- Botão de editar !-->
                                        <span class="button is-danger"> Editar</span>
                                        <!-- Botão de editar !-->
                                    </div>
                                </article>
                            </tr>
                        </table>
					</div>
                     <!-- Final do loop !-->
				</div>
			</div>
		</div>
    
    

</div>

</template>

<script>
import axios from 'axios'
import { mapState } from 'vuex'

export default {
  name: 'produtos',
  data() {
    return {
      assunto: '',
      texto: '',
      anotacoes: []
    }
  },
  computed: {
    ...mapState([
      'usuario'
    ])
  },
  methods: {
    cadastrar() {
      axios.post('produto/save',
          {
            nome: this.nome,
            fornecedor: this.fornecedor,
            validade: this.validade
          })
        .then(res => {
          this.nome = ''
          this.fornecedor = ''
          this.atualizar()
        })
        .catch(error => console.log(error))
    },
    atualizar () {
      axios.get('/produto/getAll', 
          { headers: { Accept: 'application/json' } })
        .then(res => {
          console.log(res)
          this.produtos = res.data
        })
        .catch(error => console.log(error))
    }
  },
  created () {
    this.atualizar()
  }
}
</script>