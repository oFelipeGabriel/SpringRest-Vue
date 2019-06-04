<template>
<section class="section">
    <div class="box has-text-centered is-3">
        <div class="form-control">
            <label class="label">Nome:</label>
            <input class="input" type="text" v-model="nome">
        </div>
        <div class="form-control">
        <label class="label">Fornecedor:</label>
        <input class="input" type="text" v-model="fornecedor">
        </div>
        <div class="form-control-5">
        <label class="label">Período de validade:</label>
        <input class="input" type="number" v-model="validade">
        </div>
        <div class="form-control-5 pl-3">
        <label class="label">Temperatura de armazenagem:</label>
        <input class="input" type="number" v-model="temperatura">
        </div>
        <v-card-actions>
          <v-spacer></v-spacer>
          <span
            class="botao-cadastrar"
            @click="cadastrar"
          >Cadastrar
          </span>
          </v-card-actions>
    </div>
    </section>

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
            if(this.produto!=null){
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
            if(this.produto!==null){
                this.nome = this.produto.nome;
                this.fornecedor = this.produto.fornecedor;
                this.validade = this.produto.periodo_validade;
                this.temperatura = this.produto.temp_armazemnagem;
            }else{
                this.nome = '';
                this.fornecedor = '';
                this.validade = '';
                this.temperatura = '';
            }
        }
    }
}
</script>

<style>
.form-control{
    width: 100%;
}
.form-control label, .form-control-5 label{
    width: 100%;
    text-align: left;
    font-size: .9em;
}
.form-control-5{
    width: 50%;
    float: left;
}
.form-control-5 input{
    width:100%;
}
.pl-3{
    padding-left: 10px;
}
.botao-cadastrar{
    background-color: #3aa56e;
    color: white;
    cursor: pointer;
    padding: 5px 8px;
    border-radius: 7px;
    margin-top: 10px;
}

</style>