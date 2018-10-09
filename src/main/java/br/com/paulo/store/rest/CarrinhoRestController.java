package br.com.paulo.store.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.paulo.store.model.Cart;
import br.com.paulo.store.model.CartItem;
import br.com.paulo.store.services.CartService;

@RestController
public class CarrinhoRestController {

	@Autowired
	private Cart cart;
	
	@Autowired
	private CartService cartService;
	
	@PostMapping("/addToCart")
	public CartItem adcionarProdutoCarrinho(@RequestParam(value="id_produto") Long id_produto, @RequestParam(value="quantidade") Integer quantidade) {
		
		CartItem item = cartService.adcionarProduto(id_produto,quantidade);
		
		return item;
	}
	
	@GetMapping("/listCart")
	public Cart listarProdutosDoCarrinho() {
		Cart carrinho_return = new Cart(cart.getValor_total(), cart.getItens());
		return carrinho_return;
	}
	
	@PutMapping("/updateCart")
	public Cart  atualizarCarrinho(@RequestParam(value="id_produto") Long id_produto, @RequestParam(value="quantidade") Integer quantidade) {
		cartService.atualizarItemProdutos(id_produto, quantidade);
		
		Cart carrinho_return = new Cart(cart.getValor_total(), cart.getItens());
		
		return carrinho_return;
	}
	
	@DeleteMapping("/deleteFromCart")
	public Cart removerProduto(@RequestParam(value="id_produto") Long id_produto) {		
		cartService.removerItemProdutos(id_produto);
		
		Cart carrinho_return = new Cart(cart.getValor_total(), cart.getItens());
		
		return carrinho_return;
		
		
	}
}
