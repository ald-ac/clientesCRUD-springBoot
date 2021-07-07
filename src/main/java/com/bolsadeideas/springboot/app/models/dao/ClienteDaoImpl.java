package com.bolsadeideas.springboot.app.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bolsadeideas.springboot.app.models.entity.Cliente;

@Repository
public class ClienteDaoImpl implements IClienteDao {

	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	@Override
	public List<Cliente> buscarTodo() {
		
		return em.createQuery("from Cliente").getResultList();
	}

	@Transactional
	@Override
	public void agregar(Cliente cliente) {
		em.persist(cliente);
	}

	@Transactional
	@Override
	public Cliente buscarCliente(long id) {
		return em.find(Cliente.class, id);	
	}
}
