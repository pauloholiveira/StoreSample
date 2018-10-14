package br.com.paulo.store.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.paulo.store.model.CartItem;

@SpringBootTest
@AutoConfigureDataJpa
@RunWith(SpringRunner.class)
public class CartServiceIntegrationTest {

	@TestConfiguration
	static class CartServiceIntegrationTestContextConfiguration {

		@Bean
		public CartService cartService() {
			return new CartService();
		}
	}

	@Autowired
    private CartService cartService;
	
	@Test
	public void testAdcionarProduto_parametrosVazios_retornaNull() {
		CartItem cartItem  = cartService.adcionarProduto(null, null);
		
		assertNull(cartItem);
		
	}
	
	@Test
	public void testAdcionarProduto_IDInexistente_QuantidadeInvalida_retornaNull() {
		CartItem cartItem  = cartService.adcionarProduto(45L, 0);
		CartItem cartItem2  = cartService.adcionarProduto(45L, -1);
		CartItem cartItem3  = cartService.adcionarProduto(45L, null);
		
		assertNull(cartItem);
		assertNull(cartItem2);
		assertNull(cartItem3);

	}
	
	@Test
	public void testAdcionarProduto_IDInvalido_QuantidadeValida_retornaNull() {
		CartItem cartItem  = cartService.adcionarProduto(null, 2);
		CartItem cartItem2  = cartService.adcionarProduto(-1L, 3);
		
		assertNull(cartItem);
		assertNull(cartItem2);
		
	}
	
	@Test
	public void testAdcionarProduto_IDExistente_QuantidadeInValida_retornaNull() {
		CartItem cartItem  = cartService.adcionarProduto(1L, -1);
		CartItem cartItem2  = cartService.adcionarProduto(1L, 0);
		
		assertNull(cartItem);
		assertNull(cartItem2);
	}
	
	@Test
	public void testAdcionarProduto_CarrinhoVazio_ParametrosValidos_retornaItemAtualizado() {
		
		CartItem cartItem = cartService.adcionarProduto(1L, 1);
		assertNotNull(cartItem);
		
		assertEquals(new Integer(1), cartItem.getQuantidade());
		
		cartService.removerItemProdutos(1L);
		

	}
	
	@Test
	public void testAdcionarProduto_CarrinhoJaPossuiOItem_ParametrosValidos_retornaItemAtualizadoQuantidadeIncrementada() {
		
		CartItem cartItem = cartService.adcionarProduto(1L, 1);
		assertNotNull(cartItem);
		assertEquals(new Integer(1), cartItem.getQuantidade());
		assertEquals(cartItem.getValor_item(), cartItem.getProduto().getValor());
		assertEquals(cartItem.getValor_item(), cartService.getCart().getValor_total());
		
		cartItem = cartService.adcionarProduto(1L, 1);
		assertNotNull(cartItem);
		assertEquals(new Integer(2), cartItem.getQuantidade());
		assertEquals(Double.valueOf(cartItem.getValor_item()), Double.valueOf(cartItem.getProduto().getValor()*2.00D));
				
		assertEquals(1, cartService.getCart().getItens().size());
		assertEquals(cartItem.getValor_item(), cartService.getCart().getValor_total());
		
		cartService.removerItemProdutos(1L);
		
		assertEquals(Double.valueOf(0.0D), cartService.getCart().getValor_total());
		assertEquals(0, cartService.getCart().getItens().size());
		

	}
	
	@Test
	public void testAdcionarProduto_ProdutosDiferentes() {
		CartItem cartItem = cartService.adcionarProduto(1L, 1);
		assertNotNull(cartItem);
		assertEquals(new Integer(1), cartItem.getQuantidade());
		assertEquals(cartItem.getValor_item(), cartItem.getProduto().getValor());
		assertEquals(cartItem.getValor_item(), cartService.getCart().getValor_total());
		
		
		CartItem cartItem2 = cartService.adcionarProduto(2L, 3);
		assertNotNull(cartItem2);
		assertEquals(new Integer(3), cartItem2.getQuantidade());
		assertEquals(Double.valueOf(cartItem2.getValor_item()), Double.valueOf(cartItem2.getProduto().getValor()*3.00D));
				
		assertEquals(2, cartService.getCart().getItens().size());
		assertEquals(Double.valueOf(cartItem.getValor_item()+cartItem2.getValor_item()), cartService.getCart().getValor_total());
		
		
		cartItem = cartService.adcionarProduto(1L, 1);
		assertNotNull(cartItem);
		assertEquals(new Integer(2), cartItem.getQuantidade());
		assertEquals(Double.valueOf(cartItem.getValor_item()), Double.valueOf(cartItem.getProduto().getValor()*2.00D));
				
		assertEquals(2, cartService.getCart().getItens().size());
		assertEquals(Double.valueOf(cartItem.getValor_item()+cartItem2.getValor_item()), cartService.getCart().getValor_total());
		
		cartService.removerItemProdutos(1L);
		
		assertEquals(cartItem2.getValor_item(), cartService.getCart().getValor_total());
		assertEquals(1, cartService.getCart().getItens().size());
		
		cartService.removerItemProdutos(2L);
		
		assertEquals(Double.valueOf(0.0D), cartService.getCart().getValor_total());
		assertEquals(0, cartService.getCart().getItens().size());
		
		
		
	}

	@Test
	public void testRemoverItemProdutos_IDInvalido() {
		cartService.removerItemProdutos(null);
	}
	
	@Test
	public void testRemoverItemProdutos_IDValido_ProdutoInexistente() {
		CartItem cartItem = cartService.adcionarProduto(1L, 1);
		assertNotNull(cartItem);
		assertEquals(1, cartService.getCart().getItens().size());
		
		cartService.removerItemProdutos(2L);
		assertEquals(1, cartService.getCart().getItens().size());
		
	}
	@Test
	public void testRemoverItemProdutos_IDValido_ProdutoExistente() {
		CartItem cartItem = cartService.adcionarProduto(1L, 1);
		assertNotNull(cartItem);
		
		assertEquals(1, cartService.getCart().getItens().size());
		assertEquals(cartItem.getValor_item(), cartService.getCart().getValor_total());
		
		cartService.removerItemProdutos(1L);
		assertEquals(0, cartService.getCart().getItens().size());
		assertEquals(Double.valueOf(0.0D), cartService.getCart().getValor_total());
	}
	
	@Test
	public void testAtualizarItemProdutos_param_invalidos() {
		cartService.atualizarItemProdutos(null, null);
		
	}
	
	@Test
	public void testAtualizarItemProdutos_idInvalido_qtd_valida() {
		
		cartService.atualizarItemProdutos(null, 1);
	}
	
	@Test
	public void testAtualizarItemProdutos_idValido_qtd_invalida() {
		cartService.atualizarItemProdutos(1L, null);
		cartService.atualizarItemProdutos(50L, null);
		
		cartService.atualizarItemProdutos(1L, 0);
		cartService.atualizarItemProdutos(50L, 0);
		
		cartService.atualizarItemProdutos(1L, -1);
		cartService.atualizarItemProdutos(50L, -1);
	}
	
	@Test
	public void testAtualizarItemProdutos_IDValidoInexistente_QuantidadeValida() {
		
		cartService.atualizarItemProdutos(50L, 2);
	}
	
	@Test
	public void testAtualizarItemProdutos_IDValidoExistente_QuantidadeValida() {
		CartItem cartItem = cartService.adcionarProduto(1L, 1);
		assertNotNull(cartItem);
		
		CartItem cartItem_cart = cartService.obterItem(1L);
		assertNotNull(cartItem_cart);
		assertEquals(Integer.valueOf(1), cartItem_cart.getQuantidade());
		assertEquals(cartItem.getValor_item(), cartItem_cart.getValor_item());
		
		//teste se foi inserido normal
		cartService.atualizarItemProdutos(1L, 2);
		cartItem_cart = cartService.obterItem(1L);
		assertNotNull(cartItem_cart);
		assertEquals(Integer.valueOf(2), cartItem_cart.getQuantidade());
		assertEquals(Double.valueOf(2.00D*cartItem.getProduto().getValor()), cartItem_cart.getValor_item());
	}
	
	}
