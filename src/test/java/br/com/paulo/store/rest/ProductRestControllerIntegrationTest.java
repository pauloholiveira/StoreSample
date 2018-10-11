package br.com.paulo.store.rest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.google.gson.Gson;

import br.com.paulo.store.StoreApplication;
import br.com.paulo.store.model.Product;
import br.com.paulo.store.services.ProductsService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = StoreApplication.class)
@AutoConfigureMockMvc
@AutoConfigureDataJpa
public class ProductRestControllerIntegrationTest {
	@Autowired
	private MockMvc mvc;

	@Autowired
	private ProductsService service;

	@Test
	public void givenProducts_whenGetProducts_thenReturnJsonArray() throws Exception {

	}
	/*@Test
	public void givenProducts_whenGetProducts_thenReturnJsonArray() throws Exception {

		createTestEmployee(null, "Camiseta Teste", 50.00D);
		createTestEmployee(null, "Camiseta Teste 2", 150.00D);

		// @formatter:off
		mvc.perform(get("/listProducts").contentType(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(2)))).andExpect(jsonPath("$[20].nome_descricao", is("Camiseta Teste")))
				.andExpect(jsonPath("$[21].nome_descricao", is("Camiseta Teste 2")));
		// @formatter:on
		
		service.deleteProduct(21L);
		service.deleteProduct(22L);
	}
	
	@Test
	public void newProduct() throws Exception {
		Gson t = new Gson();
		Product prod = new Product(null, "Camiseta Teste 3", 50.00D);
		String prod_json = t.toJson(prod);
		
		// @formatter:off
		mvc.perform(post("/newProduct").contentType(MediaType.APPLICATION_JSON).content(prod_json)).andDo(print())
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
		// @formatter:on
		
		service.deleteProduct(23L);
	}
	
	@Test
	public void deleteProduct() throws Exception {
		createTestEmployee(null, "Camiseta Teste 5", 50.00D);
		
		// @formatter:off
		mvc.perform(post("/deleteProduct").contentType(MediaType.APPLICATION_FORM_URLENCODED).param("id", "24")).andDo(print())
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
		// @formatter:on
		
	}

	private void createTestEmployee(Long id, String name, Double valor) {
		Product product = new Product(id, name, valor);
		service.novoProduto(product);
	}*/
}
