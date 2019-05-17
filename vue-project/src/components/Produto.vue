<template>
    <div>
        <label>Nome:</label>
        <input type="text" v-model="nome"><br>
        <label>Fornecedor:</label>
        <input type="text" v-model="fornecedor"><br>
        <label>Período de validade:</label>
        <input type="number" v-model="validade"><br>
        <label>Temperatura de armazenagem:</label>
        <input type="number" v-model="temperatura"><br>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
            color="primary"
            flat
             @click="cadastrar"
          >
           Cadastrar
          </v-btn>
          </v-card-actions>
    </div>

</template>

<script>
import axios from 'axios'
import { mapMutations } from 'vuex'
export default{
    props: ['produto'],
    data(){
        return{
            nome: '',
            fornecedor: '',
            validade: '',
            temperatura: '',
        }
    },
    methods:{
        cadastrar(){
            var p = Object();
            p.nome = this.nome;
            p.fornecedor = this.fornecedor;
            p.periodo_validade = this.validade;
            p.temp_armazemnagem = this.temperatura;
            var header = {
                'Access-Control-Allow-Origin': 'http://localhost:8080',
                'Token': 'Baerer '+this.token
            }
            if(this.produto.id!=null){
                p.id = this.produto.id;
                axios.put('/springRest/api/editaProduto/'+this.produto.id,p,header).then(res =>{
                    console.log(res);
                    this.$emit('cadastrado');
                }).catch(error =>{
                    console.log('erro',error)
                })
            }else{
                axios.post('/springRest/api/novoProduto',p,header).then(res =>{
                    console.log('ok',res);
                    this.$emit('cadastrado');
                    //this.$router.push('/Admin')
                }).catch(error =>{
                    console.log('erro',error)
                })
            }
            
        }
    },
    computed:{
        token:{
            get(){
                return this.$store.state.token;
            }
        }
    },
    watch:{
        produto(){
            if(this.produto!=null){
                this.nome = this.produto.nome;
                this.fornecedor = this.produto.fornecedor;
                this.validade = this.produto.periodo_validade;
                this.temperatura = this.produto.temp_armazemnagem;
            }
        }
    }
}
</script>