package com.bolsadeideas.springboot.app.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bolsadeideas.springboot.app.models.dao.IClienteDao;
import com.bolsadeideas.springboot.app.models.entity.Cliente;

@Controller
public class ClienteController {

	//Inyectar clase DAO para obtener clientes
	@Autowired
	private IClienteDao clienteDao;
	
	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("titulo", "Listado de clientes");
		//Implementar metodo para obtener listado
		model.addAttribute("clientes", clienteDao.buscarTodo());
		
		return "listar";
	}
	
	@RequestMapping(value = "/agregar", method = RequestMethod.GET)
	public String agregar(Model model) {
		
		//Objeto mapeado a la BD y al formulario *bidireccional*
		Cliente cliente = new Cliente();
		model.addAttribute("cliente", cliente);
		model.addAttribute("titulo", "Agregar nuevo cliente");
		
		return "form";
	}
	
	@RequestMapping(value = "/agregar", method = RequestMethod.POST)
	public String guardar(@Valid Cliente cliente, BindingResult result, Model model) {
		if(result.hasErrors()) {
			model.addAttribute("titulo", "Agregar nuevo cliente");
			return "form";
		}
		
		clienteDao.agregar(cliente);
		return "redirect:listar";
	}
}
