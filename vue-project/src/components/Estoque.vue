<template>
<div>
    <section class="section">
        <div class="box has-text-centered is-3">
          <v-btn v-show="!cadastrar && temAcesso" color="info" @click="novo()">Cadastrar</v-btn>

        <h1>Estoque</h1>

        <v-dialog
              v-model="cadastrar"
              width="500"
            >
            <v-card>
        <v-select v-model="produto"
        label="Produto"
        :items="produtos"
        item-value='id'
        item-text='nome'
        single-line
          >
        </v-select>
        <div class="control">
        <label class="label" for="nota_fiscal">Nº Nota Fiscal</label>
        <v-text-field type="number" label="Número nota fiscal" v-model="nota_fiscal"></v-text-field></div>
        <div class="control">
        <label class="label" for="data_nota_fiscal">Data da Nota Fiscal</label>
        <v-text-field type="date" v-model="data_nota_fiscal"></v-text-field></div>
        <div class="control">
        <label class="label" for="data_entrada">Data de Entrada</label>
        <v-text-field type="date" v-model="data_entrada"></v-text-field></div>
        <div class="control">
        <label class="label" for="data_fabricacao">Data de Fabricação</label>
        <v-text-field type="date" v-model="data_fabricacao"></v-text-field></div>
        <div class="control">
        <label class="label" for="numero_lote">Número do Lote</label>
        <v-text-field type="number" label="Número do lote" v-model="numero_lote"></v-text-field></div>
        <a class="button is-primary" @click="cadastre">Cadastrar</a>
      </v-card>
    </v-dialog>
        <h1 style="font-size:25px; padding-bottom: 15px" align="center"> Lista de items </h1>
        <!-- Aplicar loop aqui!-->
    <!-- <div class="box content">
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
            -->


    </div>
    </section>

    <v-timeline>
        <v-timeline-item  v-for="produto in estoque_un" :key="produto.id" color="green lighten-3" large>
            <v-card class="elevation-6">
                <v-card-title class="headline">{{ produto.produto.nome }}</v-card-title>
                <v-card-text>
                        Data de entrada: {{ produto.data_entrada.getDate() }}/{{produto.data_entrada.getMonth()+1}}/{{produto.data_entrada.getFullYear()}}
                        <br>
                        Data da nota fiscal: {{ produto.data_nota_fiscal.getDate() }}/{{produto.data_nota_fiscal.getMonth()+1}}/{{produto.data_nota_fiscal.getFullYear() }}
                        <br>
                        Data de fabricação: {{ produto.data_fabricacao.getDate() }}/{{produto.data_fabricacao.getMonth()+1}}/{{produto.data_fabricacao.getFullYear() }}
                        <br>
                        Data de validade: {{ produto.prazo_validade.getDate() }}/{{produto.prazo_validade.getMonth()+1}}/{{produto.prazo_validade.getFullYear() }}
                </v-card-text>
            </v-card>
        </v-timeline-item>

    </v-timeline>

</div>
</template>

<script>
import axios from 'axios'
import { mapMutations } from 'vuex'
export default{
    data(){
        return{
            produto: null,
            estoque_un: [],
            estoque_or: [],
            produtos: [],
            nota_fiscal: '',
            data_nota_fiscal: '',
            data_entrada: '',
            data_fabricacao: '',
            numero_lote: '',
            currentDate: new Date(),
            cadastrar: false,
            temAcesso: false,
            cadastrando: false,
            app: this,
        }
    },
    methods:{
        atualizar () {

            axios.get('springRest/apiEstoque/prateleiras/',
            { headers: {
                'Access-Control-Allow-Origin': '8',
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
        cadastre(){
             var header = {
                'Access-Control-Allow-Origin': '*',
                'Token': 'Bearer '+this.token
            };
            var e = Object();
            e.nota_fiscal = this.nota_fiscal;
            e.data_nota_fiscal = this.data_nota_fiscal;
            console.log(this.data_nota_fiscal);
            e.data_entrada = this.data_entrada;
            e.data_fabricacao = this.data_fabricacao;
            e.numero_lote = this.numero_lote;
            e.id_produto = this.produto;
            axios.post('/springRest/apiEstoque/novoEstoque/',e,{headers:{
                'Access-Control-Allow-Origin': '*',
                'Authorization': 'Bearer '+this.token,
                'Content-Type': 'application/json' }}).then(res =>{
                    console.log('ok',res);
                    this.nota_fiscal = '';
                    this.data_nota_fiscal = '';
                    this.data_entrada = '';
                    this.data_fabricacao = '';
                    this.numero_lote = '';
                    this.atualizar();
                    this.cadastrar = false;
                }).catch(error =>{
                    console.log('erro',error)
                })
                this.cadastrando = false;
        },
        novo(){
          this.cadastrar = true;
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
      this.temAcesso = this.$store.getters.isAdmin;
        var data = new Date();
        var dd = data.getDate();
        var mm = data.getMonth()+1;
        var yy = data.getFullYear();
        if(mm.toString().length<2){
            mm = "0"+mm.toString();
        }
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
        this.atualizar();
    },

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
.menuable__content__active{
  top:55px !important;
  left: 30% !important;
}
.v-btn{
  background-color: #2196f3 !important;
}
</style>
