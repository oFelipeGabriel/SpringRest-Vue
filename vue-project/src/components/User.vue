<template>
    <div>
        <div style="background-color: #fcdfa6" class="hero">
            <div class="hero-body">
                <div class="container">
                    <h1 style="font-size: 25px"> Pesquise algum item.. </h1>
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
        </div>
        
        <v-timeline>
            <v-timeline-item  v-for="produto in sortedArray" :key="produto.id" color="orange lighten-3" large>
                <v-card class="elevation-6">
                    <v-card-title class="headline">{{ produto.nome }}</v-card-title>
                    <v-card-text>
                        Validade: {{ produto.periodo_validade }} dias
                        <br>
                        Fornecedor: {{ produto.fornecedor }}
                    </v-card-text>
                </v-card>
            </v-timeline-item>
            
        </v-timeline>

                 
                    
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
          { headers: { Accept: 'application/json' } })
        .then(res => {
          console.log(res)
          this.produtos = res.data
        })
        .catch(error => console.log(error))
    },
   },  
  created () {
    this.atualizar()
  },
  computed:{
        token:{
            get(){
                return this.$store.state.token;
            }
        },
        sortedArray: function() {
            function compare(a, b) {
            if (a.periodo_validade < b.periodo_validade)
                return -1;
            if (a.periodo_validade > b.periodo_validade)
                return 1;
            return 0;
      }

      return this.produtos.sort(compare);
    }
    }
}
</script>