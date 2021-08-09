package com.bolsadeideas.springboot.app.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bolsadeideas.springboot.app.models.entity.Cliente;
import com.bolsadeideas.springboot.app.models.services.IClienteService;

@Controller
@SessionAttributes({"cliente", "titulo", "textoBoton"})
public class ClienteController {

	//Inyectar clase DAO para obtener clientes
	@Autowired
	private IClienteService clienteService;
	
	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listar(@RequestParam(name = "page",defaultValue = "0") int page, Model model) {
		model.addAttribute("titulo", "Listado de clientes");
		//Implementar metodo para obtener listado
		//Pagina a mostrar y numero de elementos
		Pageable pageRequest = PageRequest.of(page, 4);
		
		Page<Cliente> clientes = clienteService.buscarTodo(pageRequest);
		model.addAttribute("clientes", clientes);
		
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
	public String editar(@PathVariable(name = "id") long id, Model model, RedirectAttributes flash) {
		//Validar id
		if(id > 0) {
			Cliente cliente = clienteService.buscarCliente(id);
			if(cliente == null) {
				flash.addFlashAttribute("error", "El cliente no existe");
				return "redirect:/listar";
			} else {
				model.addAttribute("cliente", cliente);
				model.addAttribute("titulo", "Editar cliente");
				model.addAttribute("textoBoton", "Editar cliente");
				return "form";
			}
		} else {
			flash.addFlashAttribute("error", "Id del cliente no puede ser cero");
			return "redirect:/listar";
		}
	}
	
	@RequestMapping(value = "/guardar", method = RequestMethod.POST)
	public String guardar(@Valid Cliente cliente, BindingResult result, Model model, RedirectAttributes flash, SessionStatus status) {
		if(result.hasErrors()) {
			return "form";
		}
		String mensajeFlash = (cliente.getId() != null)? "Cliente editado con éxito" : "Cliente agregado con exito";
		
		clienteService.guardar(cliente);
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:/listar";
	}
	
	@RequestMapping(value = "/eliminar/{id}")
	public String eliminar(@PathVariable(name = "id") long id, RedirectAttributes flash) {
		if(id > 0) {
			clienteService.eliminar(id);
			flash.addFlashAttribute("success", "Cliente eliminado con éxito");
		}
		return "redirect:/listar";
	}
}
