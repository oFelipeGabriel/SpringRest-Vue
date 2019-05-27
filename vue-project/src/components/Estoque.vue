<template>
<section class="section">
    <div class="box has-text-centered is-3">
    <h1>Estoque</h1>
    <div class="select">
    <label class="label" for="produto">Produto: </label>
    <select class="select" v-model="produto">
        <option v-for="produto in produtos" :value="produto" :key="produto.id">{{produto.nome}}</option>
    </select></div>
    <div class="control">
    <label class="label" for="nota_fiscal">Nº Nota Fiscal</label>
    <input class="input" type="number" v-model="nota_fiscal"></div>
     <div class="control">
    <label class="label" for="data_nota_fiscal">Data da Nota Fiscal</label>
    <input class="input" type="date" v-model="data_nota_fiscal"></div>
    <div class="control">
    <label class="label" for="data_entrada">Data de Entrada</label>
    <input class="input" type="date" v-model="data_entrada"></div>
    <div class="control">
    <label class="label" for="data_fabricacao">Data de Fabricação</label>
    <input class="input" type="date" v-model="data_fabricacao"></div>
    <div class="control">
    <label class="label" for="numero_lote">Número do Lote</label>
    <input class="input" type="text" v-model="numero_lote"></div>
    <a class="button is-primary" @click="cadastrar">Cadastrar</a>
    <h1 style="font-size:25px; padding-bottom: 15px" align="center"> Lista de items </h1>
    <!-- Aplicar loop aqui!-->
    <div class="box content">
        <table>
            <tr v-for="produto in estoque" :key="produto.id">
                <article  style="padding-bottom: 10px;" class="post has-shadow">
                    <h3>{{ produto.produto.nome }}</h3>
                    
                </article>
            </tr>
        </table>
    </div>
        <!-- Final do loop !-->
</div>
</section>
</template>

<script>
import axios from 'axios'
import { mapMutations } from 'vuex'
export default{
    data(){
        return{
            produto: null,
            estoque: [],
            produtos: [],
            nota_fiscal: '',
            data_nota_fiscal: '',
            data_entrada: '',
            data_fabricacao: '',
            numero_lote: '',
        }
    },
    methods:{
        atualizar () {
             var header = {
                'Access-Control-Allow-Origin': 'http://localhost:8080',
                'Authorization:': 'Baerer '+this.token
            };
            axios.get('springRest/apiEstoque/prateleiras', 
          { headers: { Accept: 'application/json', 'Authorization:': 'Baerer '+this.token } })
        .then(res => {
          console.log(res)
          this.estoque = res.data
        })
        .catch(error => console.log(error))
        /*
            axios.get('springRest/api/prateleiras', {headers: header})
                .then(res => {
                this.estoque = res.data;
                console.log(res);
                })
                .catch(error => console.log(error))*/
            },
            
        cadastrar(){
            console.log(this.produto);
             var header = {
                'Access-Control-Allow-Origin': 'http://localhost:8080',
                'Token': 'Baerer '+this.token
            };
            var e = Object();
            e.nota_fiscal = this.nota_fiscal;
            e.data_nota_fiscal = this.data_nota_fiscal;
            e.data_entrada = this.data_entrada;
            e.data_fabricacao = this.data_fabricacao;
            e.numero_lote = this.numero_lote;
            e.produto = this.produto;
            axios.post('/springRest/api/novoEstoque',e,header).then(res =>{
                    console.log('ok',res);
                    this.nota_fiscal = '';
                    this.data_nota_fiscal = '';
                    this.data_entrada = '';
                    this.data_fabricacao = '';
                    this.numero_lote = '';
                    this.atualizar();
                }).catch(error =>{
                    console.log('erro',error)
                })
        }
    },
    computed:{
        token:{
            get(){
                return this.$store.state.token;
            }
        }
    },
    created () {
        this.atualizar();
             
    },
    mounted(){
        var data = new Date();
        var dd = data.getDate();
        var mm = data.getMonth()+1;
        var yy = data.getFullYear();
        if(mm.toString().length<2){
            mm = "0"+mm.toString();
        }
        this.data_entrada = yy+"-"+mm+"-"+dd;
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