package br.com.paulo.store.model;

import java.io.Serializable;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class CartItem implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Product product;
	private Integer quantidade = 0;
	private Double valor_item = 0.00D;
	
	
	public CartItem() {
		super();
	}

	public CartItem(Product product, Integer quantidade, Double valor_item) {
		super();
		this.product = product;
		this.quantidade = quantidade;
		this.valor_item = valor_item;
	}
	
	
	public Product getProduto() {
		return product;
	}
	public void setProduto(Product product) {
		this.product = product;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	/*public Cart getCarrinho() {
		return carrinho;
	}
	public void setCarrinho(Cart carrinho) {
		this.carrinho = carrinho;
	}*/
	public Double getValor_item() {
		return valor_item;
	}
	public void setValor_item(Double valor_item) {
		this.valor_item = valor_item;
	}
	
	public void addProduto(Product product) {
		quantidade++;
		valor_item += product.getValor();
	}
	
	public void removeProduto(Product product) {
		if(product.getId() == this.product.getId() && quantidade > 0) {
			quantidade--;
			valor_item -= product.getValor();
		}
	}
}
