package com.bolsadeideas.springboot.app.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bolsadeideas.springboot.app.models.dao.IClienteDao;
import com.bolsadeideas.springboot.app.models.entity.Cliente;

@Service
public class ClienteServiceImpl implements IClienteService {

	@Autowired
	private IClienteDao clienteDao;
	
	@Transactional(readOnly = true)
	@Override
	public List<Cliente> buscarTodo() {
		return clienteDao.buscarTodo();
	}

	@Transactional(readOnly = true)
	@Override
	public Cliente buscarCliente(long id) {
		return clienteDao.buscarCliente(id);
	}
	
	@Transactional
	@Override
	public void guardar(Cliente cliente) {
		clienteDao.guardar(cliente);
	}

	@Transactional
	@Override
	public void eliminar(long id) {
		clienteDao.eliminar(id);
	}

}
