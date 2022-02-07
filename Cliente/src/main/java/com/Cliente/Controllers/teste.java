package com.Cliente.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class teste {

	@GetMapping(path ="/aa")   
	public String test() {
		return "pegandooooo";
	}
}
