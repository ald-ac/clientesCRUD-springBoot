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
	@Override
	public List<Cliente> buscarTodo() {
		
		return em.createQuery("from Cliente").getResultList();
	}

	@Override
	public Cliente buscarCliente(long id) {
		return em.find(Cliente.class, id);	
	}
	
	@Override
	public void guardar(Cliente cliente) {
		//Si ya existe un ID es una actualizacion, si no insercion
		if(cliente.getId() != null && cliente.getId() > 0) { 
			em.merge(cliente);
		} else {
			em.persist(cliente);
		}
	}

	@Override
	public void eliminar(long id) {
		em.remove(buscarCliente(id));
	}
}
