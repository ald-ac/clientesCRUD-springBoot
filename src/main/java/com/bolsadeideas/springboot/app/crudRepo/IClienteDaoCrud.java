package com.bolsadeideas.springboot.app.crudRepo;

import org.springframework.data.repository.CrudRepository;

import com.bolsadeideas.springboot.app.models.entity.Cliente;

public interface IClienteDaoCrud extends CrudRepository<Cliente, Long >{
	
}
