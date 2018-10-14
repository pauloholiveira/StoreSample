# Store
Sistema de Carrinho de Compras



## Tecnologias Empregadas
* Sistema Operacional : Windows 10
* IDE: Eclipse Oxygen
* JDK: 1.8 
* Mysql: 5.7
* Maven: 3.5.4
* Spring Boot : 1.5.16
* Hibernate :


## Deploy Aplicação
Endpoint: Aplicação encontra-se publicada na [Amazon AWS](http://ec2-54-207-18-144.sa-east-1.compute.amazonaws.com:8080)



## Api REST
### Carrinho
* **Adicionar Produto ao Carrinho**

  * URL: http://ec2-54-207-18-144.sa-east-1.compute.amazonaws.com:8080/addToCart
  * Method: POST
  * Parameters: 
    1. id_produto - numeric
    2. quantidade - numeric
  
  * Response:
  CartItem informando quantidade e o produto inserido, juntamente com o valor deste item.
  
* **Listar Carrinho**

  * URL: http://ec2-54-207-18-144.sa-east-1.compute.amazonaws.com:8080/listCart
  * Method: GET
  * Parameters: 
    N/A  
  * Response:
  Carrinho com todos os itens contidos e o valor total.
 
* **Atualizar item do Carrinho**

  * URL: http://ec2-54-207-18-144.sa-east-1.compute.amazonaws.com:8080/updateCart
  * Method: PUT
  * Parameters: 
    1. id_produto - numeric
    2. quantidade - numeric
  * Response:
  Carrinho com as informações atualizadas.
  
* **Apagar Item do Carrinho**

  * URL: http://ec2-54-207-18-144.sa-east-1.compute.amazonaws.com:8080/deleteFromCart
  * Method: DELETE
  * Parameters: 
    1. id_produto - numeric
  * Response:
  Carrinho atualizado e sem o item apagado.
  
### CRUD Produtos
* **Adicionar Produto no Banco**

  * URL: http://ec2-54-207-18-144.sa-east-1.compute.amazonaws.com:8080/newProduct
  * Method: POST
  * Parameters: 
    1. produto - Exemplo: 
    ```json
      "{
          "id": 21,
          "nome_descricao": "teste",
          "valor": 50
      }"
  
  * Response:
  Produto que acabou de ser inserido.
  
* **Listar Todos Produtos**

  * URL: http://ec2-54-207-18-144.sa-east-1.compute.amazonaws.com:8080/listProducts
  * Method: GET
  * Parameters: N/A  
  * Response:
  Carrinho com todos os itens contidos e o valor total.
 
* **Atualizar Produto**

  * URL: http://ec2-54-207-18-144.sa-east-1.compute.amazonaws.com:8080/updateProduct
  * Method: POST
  * Parameters: 
    1. produto existente com os novos dados - Exemplo: 
    ```json 
      "{
          "id": 21,
          "nome_descricao": "testsssssssssse",
          "valor": 60
      }"
  * Response:
    Produto atualizado.
    ```json
      "{
          "id": 21,
          "nome_descricao": "testsssssssssse",
          "valor": 60
      }"
  
* **Apagar Produto**

  * URL: http://ec2-54-207-18-144.sa-east-1.compute.amazonaws.com:8080/deleteProduct 
  * Method: DELETE
  * Parameters: 
       1. id_produto - numeric
  * Response:
    String  - "success" caso tenha sido removido com sucesso.


## Dados teste
 DataBase: store
 
 Tabela: [schema.sql](https://github.com/pauloholiveira/store/blob/master/src/main/resources/schema.sql)

 Dados de testes: [data.sql](https://github.com/pauloholiveira/store/blob/master/src/main/resources/data.sql)

##### Autor
Nome: Paulo Henriqe de Oliveira

Email: pauloh2004@gmail.com

OBS: A projeto foi disponibilizado no dia 11/10/2018 as 17:30 no Branch Master, porém, alguns ajustes e aprimorações ainda poderão ser realizados em outros Branchs.
