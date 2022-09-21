# 📰 Documentação da API HelloBank

<p> Bem-vindo à documentação da API HelloBank. Através dela, você poderá utilizar as 
funcionalidades e recursos disponíveis do sistema.

Esse projeto fazer parte do treinamento da Gama Academy com IBM - :fist_raised: [If black, then code](https://ifblackthencode.corporate.gama.academy/) | Turma 2 (Grupo 6)</p>


<details>
  <summary> <h3> O que é API HelloBank? </h3></summary></br>
  <p>
  É uma API desenvolvida pelo grupo Black Man {Coding}  para a empresa Hello Bank (banco fictício).
  
  Sendo um sistema que permite cadastrar novos clientes, incluir dados pessoais e dados para contato. 
      
  Também possibilita registrar o histórico das transações entre contas.
  </p>
</details>

<details>
  <summary> <h3> Tecnologias e ferramentas utilizadas </h3></summary></br>
  <p>
  
  - Git
  
  - Github
  
  - Docker
  
  - Jenkins
  
  - Amazon AWS
  
  - Elastic Beanstalk
  
  - Lambda
  
  - JDK 11
  
  - Hibernate
  
  - Spring Initializr (Maven, dependências)
  
  - IDE VScode
  
  - Trello

  </p>
</details>

<details>
  <summary> <h3> Pré - Requisitos </h3> </summary></br>
  <p>
  
  - JDK 11 https://www.oracle.com/java/technologies/downloads
  
  - Maven https://maven.apache.org/download.cgi
  
  - Docker https://docs.docker.com/get-docker/
  
  - Cliente HTTP  https://www.postman.com 
  
  - IDE VS Code (recomendado) https://code.visualstudio.com/download

  </p>
</details>

<details>
  <summary> <h3> Comandos para utilizar no projeto </h3> </summary></br>
  <p>
   Após fazer o git clone do projeto você pode abrir o arquivo no VS Code e executar alguns comandos
   que foram automatizados.

   Para executá-los é do digitar no terminal ./nomedoarquivo.sh


   - (build.sh) </br>
     *comando mvn clean (Limpa todas as dependências(.jars). </br>
       comando mvn package (gera os .jars).*

   - (run.sh)</br>
     *comando java -jar target/hellobank-0.0.1-SNAPSHOT.jar (Executa a aplicação).*

   - (git_docker_push.sh) </br>
      *comando docker build para atribuir várias tags a uma imagem em um único comando).*
  </p>
</details> </br>

> ## 🔒 Endpoints </br>

*A maioria das rotas necessitam de um token de autenticação que pode ser obtido ao fazer
login ou criação de cliente.* </br>


<details>
  <summary> <h3> Rota de autenticação (no token) </h3> </summary></br>
  <p>
  
  - POST Login: http://localhost:8080/cliente/login
  
  - POST Criar: Cliente http://localhost:8080/cliente/criar
  
  </p>
</details>

<details>
  <summary> <h3> Cliente (token) </h3> </summary></br>
  <p>
  
  - PUT Update Cliente por id: http://localhost:8080/cliente/updateCliente/id
  
  - POST Get Cliente por id: http://localhost:8080/cliente/id
  
  - DEL Delete Cliente: http://localhost:8080/cliente/deleteById/id

  </p>
</details>

<details>
  <summary> <h3> Conta Corrente (token) </h3> </summary></br>
  <p>
  
  - PUT Update Conta Corrente: http://localhost:8080/contaCorrente/updateContaCorrente/id
  
  - POST Get Cliente por id:  http://localhost:8080/contaCorrente/id
  </p>
</details>

<details>
  <summary> <h3> Transferência (token) </h3> </summary></br>
  <p>
  
  - POST Criar Transferência:  (http://localhost:8080/transferencia/criar)
  
  - POST Get Transferência Enviadas por CPF: (http://localhost:8080/transferencia/AcharTransferenciasPeloRemetente/cpf) 
  
  - POST Get Transferências Recebidas  por CPF: (http://localhost:8080/transferencia/AcharTransferenciasPeloDestinatario/cpf)

  </p>
</details>

<details>
  <summary> <h3> Transação(token) </h3> </summary></br>
  <p>
  
  - POST Criar Transação Saque: http://localhost:8080/transacao/criar)
  
  - POST Get Transação Depósito: http://localhost:8080/transacao/criar)
  
  - POST Get Transferências Recebidas por CPF: http://localhost:8080/transacao/ClienteCpf/cpf)
  </p>
</details> </br>

> ## Links do projeto 

<details>
  <summary> <h3> Trello </h3> </summary></br>
  <p>
    - Método Kanban> https://trello.com/b/narX2YQV/banco-fict%C3%ADcio-hellobank-desafio-final-ibm
  </p>
</details>

<details>
  <summary> <h3> Documentação em PDF </h3> </summary></br>
  <p>
    - Documentação> https://drive.google.com/file/d/1W3iM5IwryciPVExCJUzRzmQ6VZjsikuM/view?usp=sharing
  </p>
</details> </br>

<details>
  <summary> <h3> Apresentação do Projeto </h3> </summary></br>
  <p>
    - Documentação> https://drive.google.com/file/d/1p-POuc1vf5Sok1VA1PIVTd0aJZSScU9X/view?usp=sharing
  </p>
</details> </br>


> ## 😜 Equipe 
- [Diego Moura dos Santos](https://www.linkedin.com/in/diegomouradossantos/)
- [Luis Henrique Calixto de Souza](https://www.linkedin.com/in/luiz-henrique-calixto-de-souza-29b892170/)
- [Rodrigo Nunes da Silva](https://www.linkedin.com/in/rodrigo-nunes-7a9a957b)
- [Rosemberg Vieira Araújo Filho](https://github.com/RosembergAraujo)

> **Warning**
> Variáveis de ambiente e acesso ao banco estão expostas de proposito </br>


<footer> *Este projeto é de código  aberto e está disponível sob a Licença.*</footer>




