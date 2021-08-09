package com.bolsadeideas.springboot.app.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bolsadeideas.springboot.app.crudRepo.IClienteDaoCrud;
import com.bolsadeideas.springboot.app.models.entity.Cliente;

@Service
public class ClienteServiceImpl implements IClienteService {

	@Autowired
	private IClienteDaoCrud clienteDao;
	
	@Transactional(readOnly = true)
	@Override
	public List<Cliente> buscarTodo() {
		return (List<Cliente>) clienteDao.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public Cliente buscarCliente(long id) {
		return clienteDao.findById(id).orElse(null);
	}
	
	@Transactional
	@Override
	public void guardar(Cliente cliente) {
		clienteDao.save(cliente);
	}

	@Transactional
	@Override
	public void eliminar(long id) {
		clienteDao.deleteById(id);
	}

	@Transactional(readOnly = true)
	@Override
	public Page<Cliente> buscarTodo(Pageable pageable) {
		return clienteDao.findAll(pageable);
	}

}
