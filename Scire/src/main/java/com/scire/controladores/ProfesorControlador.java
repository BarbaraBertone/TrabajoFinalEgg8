package com.scire.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.scire.entidades.Profesor;
import com.scire.errores.ErrorException;
import com.scire.servicios.ProfesorServicio;


@Controller
@RequestMapping("/")
public class ProfesorControlador {
	
	
	
	@Autowired
	private ProfesorServicio profesorServicio;
	
	
	
	@GetMapping("/profesores")
	public String profesores(ModelMap modeloDeProfesores) throws ErrorException {
		List<Profesor> misProfesores = profesorServicio.mostrarTodos();
		modeloDeProfesores.addAttribute("misProfesores", misProfesores);
		return "lista-profesores";
	}
	
	
	
	@GetMapping("/profesores/registro")
	public String registro() {
		
		return "registro-profesor";
		
	}
	
	
	
	@PostMapping("/profesores/registrar")
	public String registrar(ModelMap model, @RequestParam String nombre) throws ErrorException {
		String res = "redirect:/profesores";
		
		
		try {
			
			profesorServicio.guardar(nombre);
			
		} catch (Exception e) {
			model.put("Error", e.getMessage());
			return "registro-profesor";
		}
		return res;
	}
	

}