package com.bolsadeideas.springboot.app.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bolsadeideas.springboot.app.models.entity.Cliente;

public interface IClienteService {

	public List<Cliente> buscarTodo();
	
	public Page<Cliente> buscarTodo(Pageable pageable);
	
	public void guardar(Cliente cliente);
	
	public Cliente buscarCliente(long id);
	
	public void eliminar(long id);
}
