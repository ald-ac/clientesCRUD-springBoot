package com.bolsadeideas.springboot.app.models.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bolsadeideas.springboot.app.models.entity.Cliente;

public interface IClienteService {

	public List<Cliente> buscarTodo();
	
	public void guardar(Cliente cliente);
	
	public Cliente buscarCliente(long id);
	
	public void eliminar(long id);
}
