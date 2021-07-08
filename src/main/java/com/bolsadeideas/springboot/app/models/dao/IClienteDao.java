package com.bolsadeideas.springboot.app.models.dao;

import java.util.List;

import com.bolsadeideas.springboot.app.models.entity.Cliente;

public interface IClienteDao {

	public List<Cliente> buscarTodo();
	
	public void guardar(Cliente cliente);
	
	public Cliente buscarCliente(long id);
}
