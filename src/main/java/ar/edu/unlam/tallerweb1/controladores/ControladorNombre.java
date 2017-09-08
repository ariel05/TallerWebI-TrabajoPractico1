package ar.edu.unlam.tallerweb1.controladores;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Usuario;
import java.util.*;

@Controller
public class ControladorNombre {

	@RequestMapping(path = "/mi-nombre", method = RequestMethod.POST)
	public ModelAndView irAlNombre(){
		
		ModelMap miNombre = new ModelMap();
		
		String nombre = "Richard";
		
		List<String> miLista = new ArrayList<String>();
		
		miLista.add("Raul");
		miLista.add("Pedro");
		miLista.add("Jorge");
		
		miNombre.put("nombre", miLista);
		
		return new ModelAndView("mi-nombre",miNombre);
	}
	
	

	

}
