package com.Cliente.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.Cliente.Model.Entities.ClienteCadastro;

public interface ClienteRepository extends PagingAndSortingRepository<ClienteCadastro, Integer> {

	 public Iterable<ClienteCadastro> findBynomeContainingIgnoreCase(String ParteNome); 
}
