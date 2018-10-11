package br.com.paulo.store.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.paulo.store.model.Product;
import br.com.paulo.store.services.ProductsService;

//@Controller("/products")
public class ProdutosController {

	@Autowired
	private ProductsService prodService;
	
	List<Product> produtos = new ArrayList<Product>();
	
	
	@RequestMapping
	public String listProducts(Model model) {
		produtos = prodService.listarProdutos();
		model.addAttribute("listaProdutos", produtos);
		
		return "listagemProdutos";
	}
	
	@GetMapping("/add")
	public String adcionarProdutos(Model model) {
		produtos = prodService.listarProdutos();
		model.addAttribute("listaProdutos", produtos);
		
		return "listagemProdutos";
	}
}
