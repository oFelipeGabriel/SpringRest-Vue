<template>
<div>
<section class="section">
    <div class="box has-text-centered is-3">
        <div class="form-control">
            <label class="label">Nome:</label>
            <input class="input" type="text" v-model="nome">
        </div>
        <div class="form-control">
        <label class="label">Senha:</label>
        <input class="input" type="password" v-model="senha">
        </div>
        <div class="form-control">
        <label class="label">ADMIN:</label>
        <input class="" type="checkbox" v-model="is_admin">
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
<div class="box content">
                        <table>
                            <tr v-for="usuario in usuarios" :key="usuario.id">
                                <article  style="padding-bottom: 10px;" class="post has-shadow">
                                    <h3>{{ usuario.nome }}</h3>
                                    <div class="media">
                                        <p v-for="aut in usuario.autorizacoes" :key="aut.id">
                                            <span v-if="aut.nome == 'ROLE_ADMIN'">ADM</span>
                                            <span v-else>USER</span>
                                        </p>
                                        <!-- Botão de editar !-->
                                        
                                        <span class="button is-warning mr-3" @click="editar(usuario)"> Editar</span>
                                        <span class="button is-danger" @click="deletar(usuario.id)"> Deletar</span>
                                        <!-- Botão de editar !-->
                                    </div>
                                </article>
                            </tr>
                        </table>
					</div>
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
            senha: '',
            is_admin: false,
            usuarios: [],
            usuario: null
        }
    },
    methods:{
         atualizar () {
            axios.get('springRest/usuario/getAll', 
                { headers: { Accept: 'application/json' } })
                .then(res => {
                console.log(res)
                this.usuarios = res.data
                })
                .catch(error => console.log(error))
            },
        cadastrar(){
            var aut = '';
            if(this.is_admin==true){
                aut = "ROLE_ADMIN";
            }else{
                aut = "ROLE_USUARIO";
            }
            var header = {
                'Access-Control-Allow-Origin': '*',
                'Authorization': 'Bearer '+this.token
            }
            var body = {
                nome:this.nome,
                senha:this.senha,
                nomeAutorizacao:aut
            } 
            if(this.usuario==null){
            axios.post('/springRest/usuario/novoUsuario/',{nome:this.nome,senha:this.senha,nomeAutorizacao:aut},
                {headers:{
                    'Access-Control-Allow-Origin': '*',
                    'Authorization': 'Bearer '+this.token
                }}).then(res =>{
                    console.log(res);   
                    this.atualizar();                   
                }).catch(error =>{
                    console.log('erro',error)
                })
            } else {
                    var usuario = new Object();
                    usuario.nome = this.nome;
                    usuario.senha = this.senha;
                    axios.put('/springRest/usuario/editaUsuario/'+this.usuario.id, usuario,{headers:{
                    'Access-Control-Allow-Origin': 'http://localhost:8080',
                    'Authorization': 'Bearer '+this.token
                }}).then(res =>{
                    console.log(res);
                    this.atualizar();
                    this.usuario = null;
                    }).catch(error =>{
                        console.log('erro',error)
                    })               

            }
            this.usuario = null; 
            this.nome = '';
            this.senha = ''; 
            },
        editar(usuario){
            this.usuario = usuario; 
            this.nome = usuario.nome;
            this.senha = usuario.senha;           
        },
        deletar(id){
        var header = {
                    'Access-Control-Allow-Origin': 'http://localhost:8080',
                    'Token': 'Baerer '+this.token
                }
        axios.delete('springRest/usuario/deleteUsuario/'+id, {headers:{
                    'Access-Control-Allow-Origin': 'http://localhost:8080',
                    'Authorization': 'Bearer '+this.token
                }})
            .then(res => {
            console.log(res);
            this.atualizar();
            })
            .catch(error => console.log(error))
        },
            
        
    },
    computed:{
        token:{
            get(){
                return this.$store.state.token;
            }
        }
    },
    created () {
        this.atualizar()
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

</style>