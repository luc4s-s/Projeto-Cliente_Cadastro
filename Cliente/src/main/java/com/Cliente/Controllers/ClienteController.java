package com.Cliente.Controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.Cliente.Model.Entities.ClienteCadastro;
import com.Cliente.repositories.ClienteRepository;
import com.Projeto.model.entities.Produto;

@RestController
@RequestMapping("/api/clientecadastro")
public class ClienteController {

	@Autowired
	private ClienteRepository clienterepository;
	
	
	// metodo para INSERIR e para ALTERAR
	//URL  http://localhost:8080/api/clientecadastro
	
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT})  
	public @ResponseBody ClienteCadastro novoCliente(@Valid ClienteCadastro clientecadastro) {
		clienterepository.save(clientecadastro);
		return clientecadastro;
	}
	
	// retorna toda a lista de CLIENTE
	//URL http://localhost:8080/api/clientecadastro
	@GetMapping
	public Iterable<ClienteCadastro> obterCliente() { 
		return clienterepository.findAll();
		
	}
	
	// consulta por nome http://localhost:8080/api/produtos/nome/lapis
		@GetMapping(path = "/nome/{parteNome}")
		public Iterable<ClienteCadastro> obterClientePorNome(@PathVariable String parteNome) { // RETORNA A LISTA DE PRODUTO
			return clienterepository.findBynomeContainingIgnoreCase(parteNome);
		}
	
	//metodo para DELETAR tipo DELETE http://localhost:8080/api/clientecadastro/10	
		@DeleteMapping(path="/{id}")
		public void excluirCliente(@PathVariable int id) {
			clienterepository.deleteById(id); 
		}
		
		
		//consulta produto por ID tipo GET	http://localhost:8080/api/produtos/10
		@GetMapping(path = "/{id}") //usado essa path para cair nessa URL
		public Optional<ClienteCadastro> obterClientePorId(@PathVariable int id) {
			return clienterepository.findById(id);
			
		}
		
		// metodo para Consulta paginada http://localhost:8080/api/produtos/pagina/1
		@GetMapping(path="pagina/{numeroPagina}/{qtdePagina}")
		public Iterable<ClienteCadastro> obterClienetePorPagina(
				@PathVariable int numeroPagina, @PathVariable int qtdePagina){
			Pageable page = PageRequest.of(numeroPagina,qtdePagina);
			if (qtdePagina >=5) qtdePagina =5; //validaçao que vai apenas retorna 5 elemento na pagina 
			return clienterepository.findAll(page);
		}
}
