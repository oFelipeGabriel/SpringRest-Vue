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
                                <input class="input is-large" type="search" v-model="busca" v-on:change="buscar">
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
					<a class="button is-success is-block is-alt is-large" @click="novo">
                        <span class="icon">
                            <i class="fa fa-inbox"></i>
                        </span>
                        <span>Novo produto</span>
                    </a>
                    <br>
                    <!-- Botão de verificar produtos fora da validade !-->
                    <a class="button is-danger is-block is-alt is-large" href="/User">
                       <span class="icon">
                            <i class="fa fa-inbox"></i>
                        </span>
                    
                        <span>Fora da Validade</span>
                    </a>
		
				</div>
				<div class="column is-8">
         <!--  <v-alert class="notification is-success"
          :value="alert"
          dismissible
          type="success">
      Produto adicionado com sucesso :)
        </v-alert> -->
        <div class="notification is-success" v-show="alert">
          Produto adicionado com sucesso :)
        </div>
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
                                                    Validade: {{ produto.periodo_validade }} dias </p>
                                                    
                                            </div>
                                        </div>
                                        <!-- Botão de editar !-->
                                        <div style="padding-right: 35px; font-size: 20px; font-weight: 500">
                                        <span v-if="produto.temp_armazemnagem<=0" style="color:blue">{{ produto.temp_armazemnagem }} ºC</span>
                                        <span v-else style="color:red">{{ produto.temp_armazemnagem }} ºC</span> 
                                        </div>
                                        <span class="button is-warning mr-3" @click="editar(produto)"> Editar</span>
                                        <span class="button is-danger" @click="deletar(produto.id)"> Deletar</span>
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
    <!-- modal produto -->
  <div class="text-xs-center">
    <v-dialog
      v-model="dialog"
      width="500"
      
    >

      <v-card>
        <v-card-title
          class="headline verde-padrao"
          primary-title
        >
         Registro de Produto
        </v-card-title>

        <Produto @cadastrado="cadastrou" :produto="produto"></Produto>
  
        
      </v-card>
    </v-dialog>
  </div>
    

</div>

</template>

<script>
import axios from 'axios'
import { mapState } from 'vuex'
import Produto from './Produto'

export default {
  //name: 'produtos',
  components: {
      Produto
    },
  data() {
    return {
      header: {
                'Access-Control-Allow-Origin': 'http://localhost:8080',
                'Token': 'Baerer '+this.token
            },
      assunto: '',
      texto: '',
      anotacoes: [],
      produtos:[],
      busca: '',
      dialog: false,
      alert: false,
      produto: null,
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
    cadastrou(){
      this.dialog = false;
      this.atualizar();
      this.alert = true; 
      var self = this;    
      setTimeout(function(){ this.alert = false; }.bind(self), 5000);
    },
    buscar(){
      console.log(this.token);
      if(this.busca != ""){
        axios.get('springRest/api/busca/'+this.busca, 
          this.header )
        .then(res => {
          console.log(res)
          this.produtos = res.data
        })
        .catch(error => console.log(error))
      }else{
        this.atualizar();
      }
    },    
    atualizar () {
      axios.get('springRest/api/produtos', 
          { headers: { Accept: 'application/json',
          'Access-Control-Allow-Origin': 'http://127.0.0.1:8080', } })
        .then(res => {
          console.log(res)
          this.produtos = res.data
        })
        .catch(error => console.log(error))
    },
    deletar(id){
      var header = {
                'Access-Control-Allow-Origin': 'http://localhost:8080',
                'Token': 'Bearer '+this.token
            }
       axios.delete('springRest/api/deleteProduto/'+id, header)
        .then(res => {
          console.log(res);
          this.atualizar();
        })
        .catch(error => console.log(error))
    },
    editar(produto){
      this.produto = produto;
      this.dialog = true;
    },
    novo(){
      this.produto = null;
      this.dialog = true;      
    }
  },  
  created () {
    this.atualizar()
  },
  computed:{
        token:{
            get(){
                return this.$store.state.token;
            }
        }
    }
}
</script>

<style>
.verde-padrao{
  background-color: #3aa56e;
  font-weight: 700;
  color: white;
}
.mr-3{
  margin-right: .3em;
}
</style>