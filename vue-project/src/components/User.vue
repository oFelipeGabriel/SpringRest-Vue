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
        </div>
        
        <div class="container">
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
                                            Validade: {{ produto.validade }} 
                                        </p>
                                    </div>
                                </div>
                            </div>
                    </article>
                </tr>
            </table>
		</div>

        </div>
                     <!-- Final do loop !-->
    </div>

</template>

<script>

</script>