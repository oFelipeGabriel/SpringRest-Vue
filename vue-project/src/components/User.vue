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
        
        <v-timeline>
            <v-timeline-item v-for="n in 4" :key="n" color="orange lighten-3" large>
                <v-card class="elevation-6">
                    <v-card-title class="headline">Nome do produto</v-card-title>
                    <v-card-text>
                        Data de validade:
                        <br>
                        Fornecedor:
                    </v-card-text>
                </v-card>
            </v-timeline-item>
        </v-timeline>
                    
    </div>

</template>

<script>

</script>