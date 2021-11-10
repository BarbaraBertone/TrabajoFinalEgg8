package com.scire.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.scire.entidades.Categoria;
import com.scire.entidades.Profesor;
import com.scire.entidades.Curso;
import com.scire.errores.ErrorException;
import com.scire.servicios.CursoServicio;



@Controller
@RequestMapping("/cursos")
public class CursoControlador {
	
//	
//	@Autowired
//	private CursoServicio cursoServicio;
//	
//	@GetMapping("/programacion")
//	public String programacion() {
//		return "cursos/cursos.html";

@Autowired
 private CursoServicio cursoServicio;

	@PreAuthorize("hasAnyRole('ROLE_USER')")
	@GetMapping("/ver/{id}")
	public String vistaCurso(ModelMap model,@RequestParam String idCurso) throws ErrorException {
     Curso curso =cursoServicio.encontrarPorID(idCurso);//trae activos e inactivos
		model.addAttribute("curso",curso);
		return "ver-cursos.html";
	}
	
	@GetMapping("/registro")
	public String registro() {
		return "registro-curso.html";
	}

	@PostMapping("/registrar")
	public String registrar(ModelMap model,@RequestParam String nombre,@RequestParam String descripcion,@RequestParam String url,@RequestParam Categoria categoriaID,@RequestParam Profesor profesorID) throws ErrorException{
		try {
			
			cursoServicio.guardar(nombre, descripcion, url, categoriaID, profesorID);
			model.put("exito", "Se ha registrado con éxito");
			
		} catch (Exception e) {
			
			model.put("error", e.getMessage());
			return "registro-curso.html";
			
		}
		
		return "index.html";

	}
	
	
	
	
}
