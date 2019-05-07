<template>

<!-- Início tela de Admin !-->
<div>
    <!-- Início caixa de busca !-->
    <div class="hero is-success">
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
    <!-- Final caixa de busca !-->
    <div class="container">
			<div class="columns">
				<div class="column is-2">
					<a class="button is-success is-block is-alt is-large" href="#">
                        <span class="icon">
                            <i class="fa fa-inbox"></i>
                        </span>
                        <span>Novo produto</span>
                    </a>
                    <a class="button is-danger is-block is-alt is-large" href="#">Fora da validade</a>
		
				</div>
				<div class="column is-8">

                    <!-- Aplicar loop aqui!-->
					<div class="box content">
                        
						<article  style="padding-bottom: 15px;" class="post has-shadow">
							<h3>Iogurte Vigor</h3>
							<div class="media">
								<div class="media-content">
									<div class="content">
                                        <p style="font-size: 18px" class="tag">300g | Fabricação: 07/05/2019 | Validade: 11/05/2019 </p>
									</div>
								</div>
								<div class="media-right">
									<span class="has-text-grey-light"><i class="fa fa-comments"></i> 1</span>
								</div>
							</div>
						</article>
					</div>
                     <!-- Final do loop !-->
				</div>
			</div>
		</div>
    
    

</div>

</template>

<script>

</script>