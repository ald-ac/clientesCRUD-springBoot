package com.bolsadeideas.springboot.app.crudRepo;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.bolsadeideas.springboot.app.models.entity.Cliente;

public interface IClienteDaoCrud extends PagingAndSortingRepository<Cliente, Long >{
	
}
