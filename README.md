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

> ## 🔸 Script SQL 

<details>
  <summary> <h3> Hibernate </h3> </summary></br>
 Hibernate: 
    
    create table cliente_t (
       c_cliente_id uuid not null,
        c_cpf varchar(255) not null,
        c_created_at timestamp not null,
        c_email varchar(255) not null,
        c_endereco varchar(255),
        c_nome varchar(255) not null,
        c_senha varchar(255) not null,
        c_tel varchar(255),
        c_updated_at timestamp,
        primary key (c_cliente_id)
    )
Hibernate: 
    
    create table conta_corrente_t (
       c_conta_corrente_id uuid not null,
        c_created_at timestamp not null,
        c_limite_negativo float8,
        c_saldo float8 not null,
        c_updated_at timestamp,
        c_cliente_id uuid,
        primary key (c_conta_corrente_id)
    )
Hibernate: 
    
    create table transacao_t (
       c_transacao_id uuid not null,
        c_created_at timestamp not null,
        c_tipo_transacao varchar(255) not null,
        c_updated_at timestamp,
        c_valor float8 not null,
        c_cliente_id uuid not null,
        primary key (c_transacao_id)
    )
Hibernate: 
    
    create table transferencia_t (
       c_transferencia_id uuid not null,
        c_created_at timestamp not null,
        c_updated_at timestamp,
        c_valor float8 not null,
        c_cliente_destinatario_id uuid,
        c_cliente_remetente_id uuid,
        primary key (c_transferencia_id)
    )
Hibernate: 
    
    alter table cliente_t 
       drop constraint UK_797w5pvv6w36222lanb6outh9
Hibernate: 
    
    alter table cliente_t 
       add constraint UK_797w5pvv6w36222lanb6outh9 unique (c_cpf)
Hibernate: 
    
    alter table cliente_t 
       drop constraint UK_6dhibi73dc5iyuexovfhobyh7
Hibernate: 
    
    alter table cliente_t 
       add constraint UK_6dhibi73dc5iyuexovfhobyh7 unique (c_email)
Hibernate: 
    
    alter table conta_corrente_t 
       drop constraint UK_bqtiap2bpq1ssclaqd65fvx7m
Hibernate: 
    
    alter table conta_corrente_t 
       add constraint UK_bqtiap2bpq1ssclaqd65fvx7m unique (c_cliente_id)
Hibernate: 
    
    alter table conta_corrente_t 
       add constraint FKgsr667lpuhxvxlq41npbferne 
       foreign key (c_cliente_id) 
       references cliente_t
Hibernate: 
    
    alter table transacao_t 
       add constraint FKewdbf43b87c1pc8cbn8oukdg6 
       foreign key (c_cliente_id) 
       references cliente_t
Hibernate: 
    
    alter table transferencia_t 
       add constraint FKewik8dqxqyupalhq0evpj4f73 
       foreign key (c_cliente_destinatario_id) 
       references cliente_t
Hibernate: 
    
    alter table transferencia_t 
       add constraint FKrr61ojxi10r9d6hlhrobyvlp7 
       foreign key (c_cliente_remetente_id) 
       references cliente_t
</details></br>

> ## 🔗 Links do projeto 

<details>
  <summary> <h3> Trello </h3> </summary></br>
  <p>
    - Método Kanban> https://trello.com/b/narX2YQV/banco-fict%C3%ADcio-hellobank-desafio-final-ibm
  </p>
</details>

<details>
  <summary> <h3> Documentação em PDF </h3> </summary></br>
  <p>
    - Documentação: https://drive.google.com/file/d/1W3iM5IwryciPVExCJUzRzmQ6VZjsikuM/view?usp=sharing
  </p>
</details> 

<details>
  <summary> <h3> Apresentação do Projeto </h3> </summary></br>
  <p>
    - Projeto: https://drive.google.com/file/d/1O4q0NV-q_fRRgYIbXkbYD183UYRChvTT/view?usp=sharing
  </p>
</details>

<details>
  <summary> <h3> Documentação da API </h3> </summary></br>
  <p>
    - Documentação da API: https://documenter.getpostman.com/view/13139925/2s7Z12H4hf
  </p>
</details> </br>




> ## 😜 Equipe 
- [Diego Moura dos Santos](https://www.linkedin.com/in/diegomouradossantos/)
- [Luis Henrique Calixto de Souza](https://www.linkedin.com/in/luiz-henrique-calixto-de-souza-29b892170/)
- [Rodrigo Nunes da Silva](https://www.linkedin.com/in/rodrigo-nunes-7a9a957b)
- [Rosemberg Vieira Araújo Filho](https://github.com/RosembergAraujo)</br>

> **Warning**
> Variáveis de ambiente e acesso ao banco estão expostas de proposito </br>


<footer> *Este projeto é de código  aberto e está disponível sob a Licença MIT.*</footer>




