package com.bolsadeideas.springboot.app.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.bolsadeideas.springboot.app.models.dao.IClienteDao;
import com.bolsadeideas.springboot.app.models.entity.Cliente;

@Controller
@SessionAttributes({"cliente", "titulo", "textoBoton"})
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
		model.addAttribute("textoBoton", "Agregar cliente");
		
		return "form";
	}
	
	@RequestMapping(value = "/editar/{id}", method = RequestMethod.GET)
	public String editar(@PathVariable(name = "id") long id, Model model) {
		//Validar id
		if(id > 0) {
			Cliente cliente = clienteDao.buscarCliente(id);
			model.addAttribute("cliente", cliente);
			model.addAttribute("titulo", "Editar cliente");
			model.addAttribute("textoBoton", "Editar cliente");
			return "form";
		} else {
			return "redirect:listar";
		}
	}
	
	@RequestMapping(value = "/guardar", method = RequestMethod.POST)
	public String guardar(@Valid Cliente cliente, BindingResult result, Model model, SessionStatus status) {
		if(result.hasErrors()) {
			return "form";
		}
		
		clienteDao.guardar(cliente);
		status.setComplete();
		return "redirect:listar";
	}
}
