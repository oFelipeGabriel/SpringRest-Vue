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
        <input type="button" value="Cadastrar" @click="cadastrar">
    </div>

</template>

<script>
import axios from 'axios'
import { mapMutations } from 'vuex'
export default{
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
            console.log(this.token);
            var header = {
                'Access-Control-Allow-Origin': 'http://localhost:8080',
                'Token': 'Baerer '+this.token
            }
            axios.post('/springRest/api/novoProduto',p,header).then(res =>{
                console.log('ok',res);
                this.$emit('cadastrado');
                //this.$router.push('/Admin')
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
    }
}
</script>