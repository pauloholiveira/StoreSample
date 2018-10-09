package br.com.paulo.store.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;


@Component
@SessionScope
@JsonSerialize
@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class Cart implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private Double valor_total = 0.00D;
	private Map<Long, CartItem> itens;
	
	
	public Cart() {
		super();
		itens = new HashMap<Long,CartItem>();
		
	}
	public Cart(Double valor_total, Map<Long, CartItem> itens) {
		super();
		this.valor_total = valor_total;
		this.itens = itens;
	}
	
	public Double getValor_total() {
		return valor_total;
	}
	public void setValor_total(Double valor_total) {
		this.valor_total = valor_total;
	}
	public Map<Long, CartItem> getItens() {
		return itens;
	}
	public void setItens(Map<Long, CartItem> itens) {
		this.itens = itens;
	}
	
	public void addItem(CartItem item) {
		valor_total += item.getValor_item();
		itens.put(item.getProduto().getId(), item);
	}
	
	public void removeItem(CartItem item) {
		valor_total -= item.getValor_item();
		itens.remove(item.getProduto().getId());
	}
	
	public void updateItem(CartItem item) {
		valor_total -= item.getValor_item();
		itens.remove(item.getProduto().getId());
	}
	
	public void atualizarValorTotalCarrinho() {
		Collection<CartItem> todos_itens = itens.values();
		
		Double valor_total = 0.00D;
		for(CartItem item : todos_itens) {
			valor_total += item.getValor_item();
		}
		
		this.setValor_total(valor_total);
	}
}
