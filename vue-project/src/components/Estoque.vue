<template>
<section class="section">
    <div class="box has-text-centered is-3">
<<<<<<< HEAD
    <h1>Estoque</h1>
=======
    <h1>estoque_un</h1>
>>>>>>> f0980d10361fed3396f42a8b91acbb58fc51952f
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
<<<<<<< HEAD
    <input class="input" type="text" v-model="numero_lote"></div>
=======
    <input class="input" type="number" v-model="numero_lote"></div>
>>>>>>> f0980d10361fed3396f42a8b91acbb58fc51952f
    <a class="button is-primary" @click="cadastrar">Cadastrar</a>
    <h1 style="font-size:25px; padding-bottom: 15px" align="center"> Lista de items </h1>
    <!-- Aplicar loop aqui!-->
    <div class="box content">
<<<<<<< HEAD
       <v-timeline>
            <v-timeline-item  v-for="produto in estoque" :key="produto.id" color="orange lighten-3" large>
                <v-card class="elevation-6">
                    <v-card-title class="headline">{{ produto.produto.nome }}</v-card-title>
                    <v-card-text>
                        Validade: {{ produto.periodo_validade.getDate() }} dias
                        
                        <br>
                        Fornecedor: {{ produto.produto.fornecedor }}
                    </v-card-text>
                </v-card>
            </v-timeline-item>
            
        </v-timeline>                   
    </div>

 

=======
        <table>
            <tr v-for="produto in estoque_un" :key="produto.id">
                <article  style="padding-bottom: 10px;" class="post has-shadow">
                    <h3>Produto: {{ produto.produto.nome }}</h3>
                    <h4>Data de entrada: {{ produto.data_entrada.getDate() }}/{{produto.data_entrada.getMonth()+1}}/{{produto.data_entrada.getFullYear()}}</h4>
                    <h4>Data da nota fiscal: {{ produto.data_nota_fiscal.getDate() }}/{{produto.data_nota_fiscal.getMonth()+1}}/{{produto.data_nota_fiscal.getFullYear() }}</h4>
                    <h4>Data de fabricação: {{ produto.data_fabricacao.getDate() }}/{{produto.data_fabricacao.getMonth()+1}}/{{produto.data_fabricacao.getFullYear() }}</h4>
                    <h4>Data de validade: {{ produto.prazo_validade.getDate() }}/{{produto.prazo_validade.getMonth()+1}}/{{produto.prazo_validade.getFullYear() }}</h4>
                    

                </article>
            </tr>
        </table>
    </div>
>>>>>>> f0980d10361fed3396f42a8b91acbb58fc51952f
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
<<<<<<< HEAD
            estoque: [],
=======
            estoque_un: [],
            estoque_or: [],
>>>>>>> f0980d10361fed3396f42a8b91acbb58fc51952f
            produtos: [],
            nota_fiscal: '',
            data_nota_fiscal: '',
            data_entrada: '',
            data_fabricacao: '',
            numero_lote: '',
<<<<<<< HEAD
            currentDate: new Date()
=======
>>>>>>> f0980d10361fed3396f42a8b91acbb58fc51952f
        }
    },
    methods:{
        atualizar () {
<<<<<<< HEAD
            axios.get('springRest/api/prateleiras', 
          { headers: {'Access-Control-Allow-Origin': 'http://127.0.0.1:8080',
                    'Authorization': 'Batata '+this.token } })
        .then(res => {
          this.estoque = res.data
          
          console.log(res.data)
          for(var i=0;i<res.data.length;i++){
              var p = res.data[i];
              var potato = Object();
              potato.nota_fiscal = p.nota_fiscal;
              potato.data_nota_fiscal = new Date(p.data_nota_fiscal);
              potato.data_entrada = new Date(p.data_entrada);
              potato.data_fabricacao = new Date(p.data_fabricacao);
              potato.numero_lote = p.numero_lote;
              potato.produto = p.produto;
              this.estoque.push(potato);
              var data = new Date(res.data[i].data_entrada);
              console.log((data.getDay()*1+1)+' '+(data.getMonth()*1+1)*1+' '+data.getFullYear());
          }
        })
        .catch(error => console.log('erro', error))
         axios.get('springRest/api/produtos', 
          { headers: { Accept: 'application/json',
          'Access-Control-Allow-Origin': 'http://127.0.0.1:8080', } })
        .then(res => {
          //console.log(res)
          this.produtos = res.data
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
             var header = {
                'Access-Control-Allow-Origin': 'http://localhost:8080',
                'Token': 'Baerer '+this.token,
                'Access-Control-Allow-Headers': 'Origin, Content-Type, X-Auth-Token'
=======
            
            axios.get('springRest/apiEstoque/prateleiras/', 
            { headers: { 
                'Access-Control-Allow-Origin': 'http://localhost:8080',
                'Authorization': 'Bearer '+this.token,
                'Accept': 'application/json' }})
            .then(res => {
            console.log(res)
            this.estoque_un = [];
            for(var i=0;i<res.data.length;i++){
                var data_entrada = new Date(res.data[i].data_entrada);
                data_entrada.setDate(data_entrada.getDate()+1);
                var data_fabricacao = new Date(res.data[i].data_fabricacao);
                data_fabricacao.setDate(data_fabricacao.getDate()+1);
                var data_nota_fiscal = new Date(res.data[i].data_nota_fiscal);
                data_nota_fiscal.setDate(data_nota_fiscal.getDate()+1);
                var prazo = new Date(res.data[i].validade);
                prazo.setDate(prazo.getDate()+1);
                this.estoque_un.push({
                    data_entrada: data_entrada,
                    data_fabricacao: data_fabricacao,
                    data_nota_fiscal: data_nota_fiscal,
                    id: res.data[i].id,
                    nota_fiscal: res.data[i].nota_fiscal,
                    numero_lote: res.data[i].numero_lote,
                    produto: res.data[i].produto,
                    prazo_validade: prazo,
                })
            }
            })
            .catch(error => console.log(error))
        
            },
        cadastrar(){
             var header = {
                'Access-Control-Allow-Origin': '*',
                'Token': 'Bearer '+this.token
>>>>>>> f0980d10361fed3396f42a8b91acbb58fc51952f
            };
            var e = Object();
            e.nota_fiscal = this.nota_fiscal;
            e.data_nota_fiscal = this.data_nota_fiscal;
<<<<<<< HEAD
            e.data_entrada = this.data_entrada;
            e.data_fabricacao = this.data_fabricacao;
            e.numero_lote = this.numero_lote;
            e.produto = this.produto;
            console.log(header)
            axios.post('/springRest/api/novoEstoque',e,header).then(res =>{
=======
            console.log(this.data_nota_fiscal);
            e.data_entrada = this.data_entrada;
            e.data_fabricacao = this.data_fabricacao;
            e.numero_lote = this.numero_lote;
            e.id_produto = this.produto.id;
            axios.post('/springRest/apiEstoque/novoEstoque/',e,{headers:{
                'Access-Control-Allow-Origin': 'http://localhost:8080',
                'Authorization': 'Bearer '+this.token,
                'Content-Type': 'application/json' }}).then(res =>{
>>>>>>> f0980d10361fed3396f42a8b91acbb58fc51952f
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
        
             
    },
    mounted(){
        var data = new Date();
        var dd = data.getDate();
        var mm = data.getMonth()+1;
        var yy = data.getFullYear();
        if(mm.toString().length<2){
            mm = "0"+mm.toString();
        }
<<<<<<< HEAD
        this.data_entrada = yy+"-"+mm+"-"+dd;
        
=======
        if(dd.toString().length<2){
            dd = "0"+dd.toString();
        }
        this.data_entrada = yy+"-"+mm+"-"+dd;
       axios.get('springRest/api/produtos', 
          { headers: { Accept: 'application/json' } })
        .then(res => {
          console.log(res)
          this.produtos = res.data
        })
        .catch(error => console.log(error))
>>>>>>> f0980d10361fed3396f42a8b91acbb58fc51952f
        this.atualizar();
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
<<<<<<< HEAD

=======
>>>>>>> f0980d10361fed3396f42a8b91acbb58fc51952f
</style>