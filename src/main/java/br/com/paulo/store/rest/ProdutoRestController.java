package br.com.paulo.store.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.paulo.store.model.Product;
import br.com.paulo.store.services.ProductsService;

@RestController
public class ProdutoRestController {
	
	@Autowired
	private ProductsService prodService;
	
	@PostMapping("/newProduct") //OK
	public Product novoProduto(@RequestBody Product produto) {
		Product produto_novo = prodService.novoProduto(produto);
		return produto_novo;
	}
	
	@DeleteMapping("/deleteProduct") //OK
	public String removerProduto(@RequestParam Long id) {
		prodService.deleteProduct(id);
		
		return "success";
	}
	
	@PostMapping("/updateProduct") //OK
	public Product atualizarProduto(@RequestBody Product produto) {
		Product produto_atualizado = prodService.atualizarProduto(produto);
		
		return produto_atualizado;
	}
	
	@GetMapping("/listProducts") //OK
	public List<Product> listarTodosProdutos() {
		List<Product> produtos = prodService.listarProdutos();
		
		return produtos;
	}
}
