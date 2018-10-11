package br.com.paulo.store.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.paulo.store.model.Product;
import br.com.paulo.store.repository.ProductRepository;

@Service
public class ProductsService {

	@Autowired
	private ProductRepository prodRepo;
	
	public Product novoProduto(Product produto) {
		Product produto_inserido = prodRepo.saveAndFlush(produto);
		
		return produto_inserido;
	}
	
	public Product atualizarProduto(Product produto) {
		Product produto_inserido = prodRepo.saveAndFlush(produto);
		
		return produto_inserido;
	}
	
	public void deleteProduct(Product produto) {
		prodRepo.delete(produto);
		prodRepo.flush();
	}
	
	public void deleteProduct(Long id) {
		prodRepo.delete(id);
		prodRepo.flush();
	}
	
	public List<Product> listarProdutos() {
		List<Product> produtos = prodRepo.findAll();
		
		return produtos;
	}
	
	public Product obterProdutoPorID(Long id_produto) {
		Product produto = prodRepo.findOne(id_produto);
		
		return produto;
	}
}