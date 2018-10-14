package br.com.paulo.store.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.paulo.store.model.Cart;
import br.com.paulo.store.model.CartItem;
import br.com.paulo.store.model.Product;
import br.com.paulo.store.repository.ProductRepository;

@Service
public class CartService {
	@Autowired
	private Cart cart;
	
	@Autowired
	private ProductRepository prodRepository;
	
	public CartItem adcionarProduto(Long id, Integer qtd) {
		if(id==null) {
			return null;
		}
		
		if(qtd == null || qtd <=0) {
			return null;
		}
		
		Product produto = prodRepository.findOne(id);
		CartItem item = null;
		
		if(produto != null) {
			item = cart.getItens().get(produto.getId());
			
			if(item != null) {
				item.setQuantidade(item.getQuantidade()+qtd);
				Double novo_valor = item.getValor_item() + (item.getProduto().getValor()*qtd);
				item.setValor_item(novo_valor);
				
			} else {
				item = new CartItem(produto, qtd, produto.getValor()*qtd);
			}
			
			cart.addItem(item);
			cart.atualizarValorTotalCarrinho();
		}
		
		return item;
	}
	
	public void removerItemProdutos(Long id) {
		CartItem item = cart.getItens().get(id);
		if(item != null) {
			cart.removeItem(item);
		
			cart.atualizarValorTotalCarrinho();
		}
	}
	
	public void atualizarItemProdutos(Long id, Integer qtd) {
		if(id==null) {
			return;
		}
		
		if(qtd != null && qtd > 0) {
		
			CartItem item = cart.getItens().get(id);
			
			if(item != null) {
				item.setQuantidade(qtd);
				item.setValor_item(item.getProduto().getValor()*qtd);
			}
		}
		
		cart.atualizarValorTotalCarrinho();
	}
	
	public CartItem obterItem(Long id) {
		if(id==null) {
			return null;
		}
		
		
		CartItem item = cart.getItens().get(id);
			
		if(item != null) {
			return item;
		}
		
		return null;
		
	}

	
	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}
}

