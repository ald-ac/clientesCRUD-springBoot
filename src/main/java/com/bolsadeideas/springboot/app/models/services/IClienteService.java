package com.bolsadeideas.springboot.app.models.services;

import java.util.List;

import com.bolsadeideas.springboot.app.models.entity.Cliente;

public interface IClienteService {

	public List<Cliente> buscarTodo();
	
	public void guardar(Cliente cliente);
	
	public Cliente buscarCliente(long id);
	
	public void eliminar(long id);
}
