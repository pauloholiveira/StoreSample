package br.com.paulo.store.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity(name="produto")
@Table(name="produto", schema="store")
@JsonSerialize
public class Product implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id;
	@Column(name="nome_descricao")
	private String nome_descricao;
	@Column(name="valor")
	private Double valor;
	
	
	public Product() {
		super();
	}
	
	public Product(Long id, String nome_descricao, Double valor) {
		super();
		this.id = id;
		this.nome_descricao = nome_descricao;
		this.valor = valor;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome_descricao() {
		return nome_descricao;
	}
	public void setNome_descricao(String nome_descricao) {
		this.nome_descricao = nome_descricao;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	
	
}
