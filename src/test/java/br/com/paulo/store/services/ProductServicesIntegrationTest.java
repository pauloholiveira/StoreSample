package br.com.paulo.store.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.paulo.store.model.Product;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureDataJpa
public class ProductServicesIntegrationTest {
	@TestConfiguration
	static class EmployeeServiceImplTestContextConfiguration {

		@Bean
		public ProductsService productsService() {
			return new ProductsService();
		}
	}

	@Autowired
    private ProductsService productsService;
	
	//novoProduto
	@Test
	public void novoProduto_NullParameter_returnNull() {
		Product produto_inserido = productsService.novoProduto(null);
		
		assertNull(produto_inserido);
	}
	
	@Test
	public void novoProduto_ParameterProdutoExistente() {
		Product produto = new Product(1L, "Produto de Teste", 10.00D);
		Product produto_inserido = productsService.novoProduto(produto);
		
		assertNull(produto_inserido);
	}
	
	@Test
	public void novoProduto_ParameterProdutoNaoExistente() {
		Product produto = new Product(null, "Produto de Teste 1", 10.00D);
		
		Product produto_inserido = productsService.novoProduto(produto);
		long id = produto_inserido.getId();
		assertNotNull(produto_inserido);
		
		productsService.deleteProduct(id);
	}
	
	//atualizarProduto
	@Test
	public void atualizarProduto_NullParameter() {
		Product produto_inserido = productsService.novoProduto(null);
		
		assertNull(produto_inserido);
	}
	
	@Test
	public void atualizarProduto_ParameterProdutoExistente() {
		Product produto = productsService.obterProdutoPorID(1L);
		String descricao = produto.getNome_descricao();
		Double valor = produto.getValor();
		
		assertNotNull(produto);
		
		String nova_descricao = "Alterou Descricao Produto";
		produto.setNome_descricao(nova_descricao);
		Double novo_valor = 0.0D;
		produto.setValor(novo_valor);
		productsService.atualizarProduto(produto);
		
		//obtem produto do banco novamente para saber se a alteração foi realizada com sucesso. 
		Product produto_modificado = productsService.obterProdutoPorID(1L);
		assertNotNull(produto_modificado);
		assertEquals(nova_descricao, produto_modificado.getNome_descricao());
		assertEquals(novo_valor, produto_modificado.getValor());
		
		//volta alteração para a forma antiga
		produto_modificado.setNome_descricao(descricao);
		produto_modificado.setValor(valor);
		productsService.atualizarProduto(produto_modificado);
		
		//e pega novamente do banco para ver se as alteração foram realizadas.
		produto_modificado = productsService.obterProdutoPorID(1L);
		assertEquals(descricao, produto_modificado.getNome_descricao());
		assertEquals(valor, produto_modificado.getValor());
	}
	
	@Test
	public void atualizarProduto_ParameterProdutoNAOExistente() {
		Product produto = new Product(35L, "Porduto que não existe", 10.00D);
		
		Product produto_alterado = productsService.atualizarProduto(produto);
		
		assertNull(produto_alterado);
		
		produto = new Product(null, "Porduto que não existe", 10.00D);
		
		produto_alterado = productsService.atualizarProduto(produto);
		assertNull(produto_alterado);
	}
	
	//deleteProduct - param Produto
	@Test
	public void deleteProduct_NullParameterProduto() {
		Product produto1 = null;
		productsService.deleteProduct(produto1);
		
	}
	
	@Test
	public void deleteProduct_ParameterProdutoExistente() {
		Product produto1 = new Product(null, "Teste Produto 1", 10.00D);
		Product produto2 = new Product(null, "Teste Produto 2", 20.00D);
		
		produto1 = productsService.novoProduto(produto1);
		produto2 = productsService.novoProduto(produto2);
		assertNotNull(produto1);
		assertNotNull(produto2);
		
		produto1 = productsService.obterProdutoPorID(produto1.getId());
		produto2 = productsService.obterProdutoPorID(produto2.getId());
		assertNotNull(produto1);
		assertNotNull(produto2);
		
		productsService.deleteProduct(produto1);
		productsService.deleteProduct(produto2);
		
		produto1 = productsService.obterProdutoPorID(produto1.getId());
		produto2 = productsService.obterProdutoPorID(produto2.getId());
		assertNull(produto1);
		assertNull(produto2);
	}
	
	@Test
	public void deleteProduct_ParameterProdutoNAOExistente() {
		Product produto1 = new Product(null, "Teste Produto 1", 10.00D);
		
		productsService.deleteProduct(produto1);
	}
	
	//deleteProduct - param ID
	@Test
	public void deleteProduct_NullParameter() {
		Long id = null;
		productsService.deleteProduct(id);
	}
	
	@Test
	public void deleteProduct_ParameterIDExistente() {
		Product produto1 = new Product(null, "Teste Produto 1", 10.00D);
		Product produto2 = new Product(null, "Teste Produto 2", 20.00D);
		
		produto1 = productsService.novoProduto(produto1);
		produto2 = productsService.novoProduto(produto2);
		assertNotNull(produto1);
		assertNotNull(produto2);
		
		produto1 = productsService.obterProdutoPorID(produto1.getId());
		produto2 = productsService.obterProdutoPorID(produto2.getId());
		assertNotNull(produto1);
		assertNotNull(produto2);
		
		productsService.deleteProduct(produto1.getId());
		productsService.deleteProduct(produto2.getId());
		
		produto1 = productsService.obterProdutoPorID(produto1.getId());
		produto2 = productsService.obterProdutoPorID(produto2.getId());
		assertNull(produto1);
		assertNull(produto2);
	}
	
	@Test(expected=EmptyResultDataAccessException.class)
	public void deleteProduct_ParameterIDNAOExistente() {
		productsService.deleteProduct(45L);
	}
	
	//listarTudo
	@Test
	public void listarTudo() {
		Product produto_teste1 = new Product(null, "Produto teste 1", 10.00D);
		productsService.novoProduto(produto_teste1);
		
		Product produto_teste2 = new Product(null, "Produto Teste 2", 10.00D);
		productsService.novoProduto(produto_teste2);
		
		List<Product> lista_produtos = productsService.listarProdutos();
		assertNotNull(lista_produtos);
		
		assertEquals(22, lista_produtos.size());
		
		productsService.deleteProduct(produto_teste1);
		productsService.deleteProduct(produto_teste2);
		
		lista_produtos = productsService.listarProdutos();
		assertNotNull(lista_produtos);
		
		assertEquals(20, lista_produtos.size());
	}
	
	
	//obterProdutoPorID
	@Test
	public void obterProdutoPorID_NullParameterProduto() {
		Product produto1 = productsService.novoProduto(null);
		
		assertNull(produto1);
	}
	
	@Test
	public void obterProdutoPorID_ParameterProdutoExistente() {
		Product produto1 = new Product(null, "Teste Produto 1", 10.00D);
		Product produto2 = new Product(null, "Teste Produto 2", 20.00D);
		
		produto1 = productsService.novoProduto(produto1);
		produto2 = productsService.novoProduto(produto2);
		assertNotNull(produto1);
		assertNotNull(produto2);
		
		produto1 = productsService.obterProdutoPorID(produto1.getId());
		produto2 = productsService.obterProdutoPorID(produto2.getId());
		assertNotNull(produto1);
		assertNotNull(produto2);
		
		productsService.deleteProduct(produto1);
		productsService.deleteProduct(produto2);
	}
	
	@Test
	public void obterProdutoPorID_ParameterProdutoNAOExistente() {
		Product produto1 = productsService.obterProdutoPorID(100L);
		
		assertNull(produto1);
	}	
}
